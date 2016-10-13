package com.example.ashish.alumini.supporting_classes;

import android.support.v4.app.Fragment;

import com.example.ashish.alumini.fragments.FragmentJobs;

/**
 * Created by ashish on 29/7/16.
 */
public class CommonData {
    public static FragmentJobs fragmentJobs ;

    public static Fragment mCurrentFragmentPostLogin;
    public static Fragment mCurrentFragmentMainScreen;

    public CommonData(){
        fragmentJobs = new FragmentJobs();
    }
    public static Fragment CommonData(){
        return mCurrentFragmentPostLogin;
    }

}
