package com.example.ashish.alumini.supporting_classes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;

import com.example.ashish.alumini.fragments.FragmentJobs;
import com.example.ashish.alumini.fragments.viewpager.FragmentViewPager0;
import com.example.ashish.alumini.fragments.viewpager.FragmentViewPager1;
import com.example.ashish.alumini.fragments.viewpager.FragmentViewPager2;

/**
 * Created by ashish on 29/7/16.
 */
public class CommonData {
    public static FragmentJobs fragmentJobs ;

    public static Fragment mCurrent;

    public CommonData(){
        fragmentJobs = new FragmentJobs();
    }
    public static Fragment CommonData(){
        return mCurrent;
    }

}
