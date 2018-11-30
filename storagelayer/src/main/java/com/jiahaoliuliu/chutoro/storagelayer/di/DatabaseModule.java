package com.jiahaoliuliu.chutoro.storagelayer.di;

import android.content.Context;

import com.jiahaoliuliu.chutoro.storagelayer.TransactionsDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    TransactionsDatabase provideTransactionDatabase(Context context) {
        return TransactionsDatabase.getInstance(context);
    }
}
