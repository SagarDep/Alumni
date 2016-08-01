package com.example.ashish.alumini.application;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by ashish on 1/7/16.
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
