package com.jiahaoliuliu.chutoro.storagelayer;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Transaction.class}, version = 1, exportSchema = false)
public abstract class TransactionsDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "items_database";

    public abstract TransactionsDao transactionsDao();
}
