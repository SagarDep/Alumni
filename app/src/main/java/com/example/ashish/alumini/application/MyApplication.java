package com.example.ashish.alumini.application;

import android.app.Application;

import com.example.ashish.alumini.fragments.FragmentJobs;
import com.example.ashish.alumini.supporting_classes.CommonData;
import com.example.ashish.alumini.supporting_classes.GlobalPrefs;
import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ashish on 1/7/16.
 */
public class MyApplication extends Application {
    public FragmentJobs fragmentJobs;
    @Override
    public void onCreate() {
        super.onCreate();

        /*
        * Stetho init
        * */
        Stetho.initializeWithDefaults(this);

//        fragmentJobs = new FragmentJobs().newInstance("","");


        new GlobalPrefs(getApplicationContext()).mContext = getApplicationContext();

        CommonData  commonData = new CommonData();

    }


}
