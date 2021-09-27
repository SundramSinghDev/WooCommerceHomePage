package com.sundram.woocommercehomepage;

import android.app.Application;

import androidx.multidex.MultiDex;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
    }
}
