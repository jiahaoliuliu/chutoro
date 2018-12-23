package com.jiahaoliuliu.chutoro.storagelayer.di;

import android.content.Context;

import com.jiahaoliuliu.chutoro.storagelayer.MainDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    MainDatabase provideTransactionDatabase(Context context) {
        return MainDatabase.getInstance(context);
    }
}
