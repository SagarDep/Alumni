package com.example.ashish.alumini.supporting_classes;

import com.example.ashish.alumini.fragments.FragmentJobs;
import com.example.ashish.alumini.fragments.viewpager.FragmentViewPager0;
import com.example.ashish.alumini.fragments.viewpager.FragmentViewPager1;
import com.example.ashish.alumini.fragments.viewpager.FragmentViewPager2;

/**
 * Created by ashish on 29/7/16.
 */
public class CommonData {
    public static FragmentJobs fragmentJobs ;

    public static FragmentViewPager0 fragmentViewPager0;
    public static FragmentViewPager1 fragmentViewPager1;
    public static FragmentViewPager2 fragmentViewPager2;
    public CommonData(){
        fragmentJobs = new FragmentJobs();
        fragmentViewPager0 = new FragmentViewPager0();
        fragmentViewPager1 = new FragmentViewPager1();
        fragmentViewPager2 = new FragmentViewPager2();
    }
}
