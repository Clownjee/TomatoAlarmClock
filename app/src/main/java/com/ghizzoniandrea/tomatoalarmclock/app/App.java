package com.ghizzoniandrea.tomatoalarmclock.app;

import android.app.Application;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

/**
 * Created by ghizzoniandrea on 2017/2/7.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CustomActivityOnCrash.install(this);
    }
}
