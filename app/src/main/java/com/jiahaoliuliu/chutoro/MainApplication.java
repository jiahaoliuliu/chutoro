package com.jiahaoliuliu.chutoro;

import android.app.Application;

public class MainApplication extends Application {

    private static MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mainComponent = DaggerMainComponent.builder()
            .applicationModule(new ApplicationModule(this))
            .build();
    }

    public static MainComponent getMainComponent() {
        return mainComponent;
    }
}
