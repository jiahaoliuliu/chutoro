package com.jiahaoliuliu.chutoro.storagelayer.di;

import android.content.Context;

import com.jiahaoliuliu.chutoro.storagelayer.MainDatabase;
import com.jiahaoliuliu.chutoro.storagelayer.preferences.Preferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {

    @Provides
    @Singleton
    Preferences providePreferences(Context context) {
        return new Preferences(context);
    }

    @Provides
    @Singleton
    MainDatabase provideMainDatabase(Context context, Preferences preferences) {
        return MainDatabase.getInstance(context, preferences);
    }

}
