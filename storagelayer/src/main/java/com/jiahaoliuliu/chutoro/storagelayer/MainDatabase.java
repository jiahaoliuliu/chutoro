package com.jiahaoliuliu.chutoro.storagelayer;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {
        PersistentTransaction.class,
        PersistentDestination.class,
        PersistentDestinationGroup.class}, version = 1, exportSchema = false)
public abstract class MainDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "Chutoro.sqlite";

    private static MainDatabase instance;

    public abstract TransactionsDao transactionsDao();

    public static synchronized MainDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MainDatabase.class, MainDatabase.DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}
