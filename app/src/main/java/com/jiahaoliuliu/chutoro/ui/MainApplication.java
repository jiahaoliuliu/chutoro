package com.jiahaoliuliu.chutoro.ui;

import android.app.Application;

import timber.log.Timber;

public class MainApplication extends Application {

    private static MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mainComponent = DaggerMainComponent.builder()
            .applicationModule(new ApplicationModule(this))
            .build();
        Timber.plant(new Timber.DebugTree());
    }

    public static MainComponent getMainComponent() {
        return mainComponent;
    }
}
