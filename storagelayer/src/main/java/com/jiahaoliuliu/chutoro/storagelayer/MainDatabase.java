package com.jiahaoliuliu.chutoro.storagelayer;

import android.content.Context;

import com.jiahaoliuliu.chutoro.storagelayer.destination.CategoriesProvider;
import com.jiahaoliuliu.chutoro.storagelayer.destination.CategoryDao;
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
    private static CategoriesProvider categoriesProvider;

    public abstract TransactionsDao transactionsDao();
    public abstract DestinationDao destinationDao();
    public abstract DestinationGroupDao destinationGroupDao();
    public abstract CategoryDao categoryDao();

    private static Preferences preferences;

    public static synchronized MainDatabase getInstance(Context context, Preferences newPreferences) {
        preferences = newPreferences;
        if (instance == null) {
            destinationsProvider = new DestinationsProvider(context);
            categoriesProvider = new CategoriesProvider(context);
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
            updateDestinationsIfNeeded();
            updateCategoriesIfNeeded();
        }
    };

    private static void updateCategoriesIfNeeded() {
        Disposable disposable = Single.fromCallable(() -> categoriesProvider.provideNewCategoriesUpdateTime())
                .subscribeOn(Schedulers.io())
                .subscribe(newCategoriesUpdateTime -> {
                    if (newCategoriesUpdateTime >
                            preferences.get(PreferencesKey.KEY_DATABASE_CATEGORIES_LAST_UPDATE_TIME, 0)) {
                        Timber.v("The new database categories update time is newer than the " +
                                "old one. Updating everything");
                        instance.categoryDao().deleteAllCategories();
                        // Initialize the database with the new values
                        initializeCategories();
                    }
                }, throwable -> Timber.e(throwable, "Error trying to update the categories in the database"));
    }

    private static void initializeCategories() {
        // Initialize the category table
        CategoryDao categoryDao = instance.categoryDao();
        Disposable disposable = Observable.fromIterable(categoriesProvider.providePersistentCategories())
                .map(persistentCategory -> categoryDao.insert(persistentCategory))
                .subscribeOn(Schedulers.io())
                .subscribe(aBoolean -> {
                            Timber.v("Data inserted into the database " + aBoolean);
                        }, throwable -> Timber.e(throwable, "Error inserting the data into the database"),
                        () -> {
                            Timber.i("Database completely initialized");
                            preferences.set(PreferencesKey.KEY_DATABASE_CATEGORIES_LAST_UPDATE_TIME,
                                    categoriesProvider.provideNewCategoriesUpdateTime());
                        });
    }

    private static void updateDestinationsIfNeeded() {
        Disposable disposable = Single.fromCallable(() -> destinationsProvider.provideNewDestinationsUpdateTime())
            .subscribeOn(Schedulers.io())
            .subscribe(newDestinationsUpdateTime -> {
                if (newDestinationsUpdateTime >
                        preferences.get(PreferencesKey.KEY_DATABASE_DESTINATIONS_LAST_UPDATE_TIME, 0)) {
                    Timber.v("The new database destinations update time is newer than the " +
                            "old one. Updating everything");
                    instance.destinationGroupDao().deleteAllDestinationGroups();
                    instance.destinationDao().deleteAllDestinations();
                    initializeDestinations();
                } else {
                    Timber.v("The new database update time is not newer than the old time");
                }
            }, throwable -> {
                Timber.e(throwable, "Error trying to update the destinations in the database");
            });
    }

    private static void initializeDestinations() {
        // Initialize the destination and destination group table
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
                        preferences.set(PreferencesKey.KEY_DATABASE_DESTINATIONS_LAST_UPDATE_TIME,
                                destinationsProvider.provideNewDestinationsUpdateTime());
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
