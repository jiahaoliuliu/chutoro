package com.jiahaoliuliu.chutoro.storagelayer;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TransactionsDatabaseModule {

    @Provides
    @Singleton
    TransactionsDatabase provideTransactionDatabase(Context context) {
        return TransactionsDatabase.getInstance(context);
    }
}
