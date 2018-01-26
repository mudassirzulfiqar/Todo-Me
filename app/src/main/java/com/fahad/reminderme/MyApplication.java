package com.fahad.reminderme;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by moodi on 26/01/2018.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
        }
    }
}
