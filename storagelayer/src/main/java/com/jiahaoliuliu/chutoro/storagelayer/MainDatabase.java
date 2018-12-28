package com.jiahaoliuliu.chutoro.storagelayer;

import android.content.Context;

import com.jiahaoliuliu.chutoro.storagelayer.destination.DestinationDao;
import com.jiahaoliuliu.chutoro.storagelayer.destination.DestinationGroupDao;
import com.jiahaoliuliu.chutoro.storagelayer.destination.DestinationsProvider;
import com.jiahaoliuliu.chutoro.storagelayer.destination.PersistentDestination;
import com.jiahaoliuliu.chutoro.storagelayer.destination.PersistentDestinationGroup;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import io.reactivex.Observable;
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

    public static synchronized MainDatabase getInstance(Context context) {
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
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            initializeDatabase();
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // TODO: Update the content of the database properly
            initializeDatabase();
        }
    };

    private static void initializeDatabase() {
        // Initialize the database
        DestinationGroupDao destinationGroupDao = instance.destinationGroupDao();
        DestinationDao destinationDao = instance.destinationDao();
        Disposable disposable = Observable.fromIterable(destinationsProvider.providePersistentDestinationGroups())
                .flatMap(persistentDestinationGroup -> insertPersistentDestinationGroup(
                        destinationGroupDao, destinationDao, persistentDestinationGroup))
                .subscribeOn(Schedulers.io())
                .subscribe(aBoolean -> Timber.v("Data inserted into the database " + aBoolean),
                        throwable -> Timber.e(throwable, "Error inserting the data into the database"));
    }

    private static Observable<Boolean> insertPersistentDestinationGroup(
            DestinationGroupDao destinationGroupDao, DestinationDao destinationDao,
            PersistentDestinationGroup persistentDestinationGroup) {
        return Observable.fromCallable(() -> destinationGroupDao.insertIfDoesNotExist(persistentDestinationGroup))
                .flatMap(__ -> insertPersistentDestinations(destinationDao, persistentDestinationGroup));
    }

    private static Observable<Boolean> insertPersistentDestinations(
            DestinationDao destinationDao, PersistentDestinationGroup persistentDestinationGroup) {
            return Observable.fromIterable(persistentDestinationGroup.getPersistentDestinations())
                .map(destinationDao::insertIfDoesNotExist);
    }
}
