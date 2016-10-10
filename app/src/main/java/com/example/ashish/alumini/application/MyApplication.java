package com.example.ashish.alumini.application;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.crashlytics.android.Crashlytics;
import com.example.ashish.alumini.supporting_classes.CommonData;
import com.example.ashish.alumini.supporting_classes.GlobalPrefs;
import com.example.ashish.alumini.supporting_classes.MemberLists;
import com.facebook.stetho.Stetho;
import io.fabric.sdk.android.Fabric;

/**
 * Created by ashish on 1/7/16.
 */
public class MyApplication extends Application {

    public MemberLists memberLists;
    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        /*
        * Stetho init
        * */
        Stetho.initializeWithDefaults(this);

        /*
        * Active android Init
        * */
        ActiveAndroid.initialize(this);


        /*
        * Global Shared Preferences
        * */
        new GlobalPrefs(getApplicationContext()).mContext = getApplicationContext();

        // class will create the fragmentjob instance and later can be accessed anywhere
        CommonData  commonData = new CommonData();

    }

    public void createListCLass(){
        memberLists = new MemberLists();

    }

    public MemberLists getMemberLists() {
        return memberLists;
    }
}
