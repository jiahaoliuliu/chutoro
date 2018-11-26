package com.jiahaoliuliu.chutoro.storagelayer;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TransactionsDatabaseModule {

    private TransactionsDatabase transactionsDatabase;

    @Provides
    @Singleton
    TransactionsDatabase provideTransactionsDatabase(Context context) {
        if (transactionsDatabase == null) {
            transactionsDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    TransactionsDatabase.class, TransactionsDatabase.DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return transactionsDatabase;
    }
}
