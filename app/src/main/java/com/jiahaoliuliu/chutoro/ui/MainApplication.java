package com.jiahaoliuliu.chutoro.ui;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.jiahaoliuliu.chutoro.BuildConfig;

import timber.log.Timber;

public class MainApplication extends Application {

    private static MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mainComponent = DaggerMainComponent.builder()
            .applicationModule(new ApplicationModule(this))
            .build();

        // Timber
        Timber.plant(new Timber.DebugTree());

        // Stetho
        if (BuildConfig.ENABLE_STETHO) {
            Stetho.initializeWithDefaults(this);
        }
    }

    public static MainComponent getMainComponent() {
        return mainComponent;
    }
}
