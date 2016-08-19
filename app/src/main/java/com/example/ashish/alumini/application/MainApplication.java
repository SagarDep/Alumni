package com.example.ashish.alumini.application;

import android.app.Application;

import com.example.ashish.alumini.supporting_classes.GlobalPrefs;
import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ashish on 1/7/16.
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        /*
        * Stetho init
        * */
        Stetho.initializeWithDefaults(this);

        /*
        * Retrofit declaration and init
        * */
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.stackexchange.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();



        GlobalPrefs.mContext = getApplicationContext();

    }
}
