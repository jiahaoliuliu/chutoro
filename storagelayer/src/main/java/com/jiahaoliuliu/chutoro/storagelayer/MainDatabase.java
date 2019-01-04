package com.jiahaoliuliu.chutoro.storagelayer;

import android.content.Context;

import com.jiahaoliuliu.chutoro.storagelayer.destination.DestinationDao;
import com.jiahaoliuliu.chutoro.storagelayer.destination.DestinationGroupDao;
import com.jiahaoliuliu.chutoro.storagelayer.destination.DestinationsProvider;
import com.jiahaoliuliu.chutoro.storagelayer.destination.PersistentDestination;
import com.jiahaoliuliu.chutoro.storagelayer.destination.PersistentDestinationGroup;
import com.jiahaoliuliu.chutoro.storagelayer.preferences.Preferences;
import com.jiahaoliuliu.chutoro.storagelayer.preferences.PreferencesKey;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@Database(entities = {
        PersistentTransaction.class,
        PersistentDestination.class,
        PersistentDestinationGroup.class}, version = 1, exportSchema = false)
public abstract class MainDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "Chutoro.sqlite";

    private static MainDatabase instance;
    private static DestinationsProvider destinationsProvider;

    public abstract TransactionsDao transactionsDao();
    public abstract DestinationDao destinationDao();
    public abstract DestinationGroupDao destinationGroupDao();

    private static Preferences preferences;

    public static synchronized MainDatabase getInstance(Context context, Preferences newPreferences) {
        preferences = newPreferences;
        if (instance == null) {
            destinationsProvider = new DestinationsProvider(context);
            instance = Room.databaseBuilder(context.getApplicationContext(),
                MainDatabase.class, MainDatabase.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .addCallback(roomCallback)
                .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            updateDatabaseIfNeeded();
        }
    };

    private static void updateDatabaseIfNeeded() {
        DestinationGroupDao destinationGroupDao = instance.destinationGroupDao();
        DestinationDao destinationDao = instance.destinationDao();
        Disposable disposable = Single.fromCallable(() -> destinationsProvider.provideNewDatabaseUpdateTime())
            .subscribeOn(Schedulers.io())
            .subscribe(newDatabaseUpdateTime -> {
                if (newDatabaseUpdateTime > preferences.get(PreferencesKey.KEY_DATABASE_LAST_UPDATE_TIME, 0)) {
                    Timber.v("The new database update time is newer than the old one. Updating everything");
                    destinationGroupDao.deleteAllDestinationGroups();
                    destinationDao.deleteAllDestinations();
                    initializeDatabase();
                } else {
                    Timber.v("The new database update time is not newer than the old time");
                }
            }, throwable -> {
                Timber.e(throwable, "Error trying to update the database");
            });
    }

    private static void initializeDatabase() {
        // Initialize the database
        DestinationGroupDao destinationGroupDao = instance.destinationGroupDao();
        DestinationDao destinationDao = instance.destinationDao();
        Disposable disposable = Observable.fromIterable(destinationsProvider.providePersistentDestinationGroups())
                .flatMap(persistentDestinationGroup -> insertPersistentDestinationGroup(
                        destinationGroupDao, destinationDao, persistentDestinationGroup))
                .subscribeOn(Schedulers.io())
                .subscribe(aBoolean -> {
                    Timber.v("Data inserted into the database " + aBoolean);

                    }, throwable -> Timber.e(throwable, "Error inserting the data into the database"),
                    () -> {
                        Timber.i("Database completely initialized");
                        preferences.set(PreferencesKey.KEY_DATABASE_LAST_UPDATE_TIME,
                                destinationsProvider.provideNewDatabaseUpdateTime());
                    });
    }

    private static Observable<Boolean> insertPersistentDestinationGroup(
            DestinationGroupDao destinationGroupDao, DestinationDao destinationDao,
            PersistentDestinationGroup persistentDestinationGroup) {
        return Observable.fromCallable(() -> destinationGroupDao.insertOrUpdate(persistentDestinationGroup))
                .flatMap(__ -> insertPersistentDestinations(destinationDao, persistentDestinationGroup));
    }

    private static Observable<Boolean> insertPersistentDestinations(
            DestinationDao destinationDao, PersistentDestinationGroup persistentDestinationGroup) {
            return Observable.fromIterable(persistentDestinationGroup.getPersistentDestinations())
                .map(destinationDao::insertOrUpdate);
    }
}
