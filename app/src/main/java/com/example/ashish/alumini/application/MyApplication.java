package com.example.ashish.alumini.application;

import android.app.Application;

import com.example.ashish.alumini.supporting_classes.CommonData;
import com.example.ashish.alumini.supporting_classes.GlobalPrefs;
import com.example.ashish.alumini.supporting_classes.MemberLists;
import com.facebook.stetho.Stetho;

/**
 * Created by ashish on 1/7/16.
 */
public class MyApplication extends Application {

    public MemberLists mMemberLists;
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

    public void createListCLass(){
        mMemberLists = new MemberLists();

    }

    public MemberLists getmMemberListsInstance(){
        return mMemberLists;
    }


}
