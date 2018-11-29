package com.jiahaoliuliu.chutoro.storagelayer;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {PersistentTransaction.class}, version = 1, exportSchema = false)
public abstract class TransactionsDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "items_database";

    private static TransactionsDatabase instance;

    public abstract TransactionsDao transactionsDao();

    public static synchronized TransactionsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TransactionsDatabase.class, TransactionsDatabase.DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}
