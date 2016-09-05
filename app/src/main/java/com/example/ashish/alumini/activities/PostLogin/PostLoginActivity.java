package com.example.ashish.alumini.activities.PostLogin;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


import com.example.ashish.alumini.fragments.FragmentMenu;
import com.example.ashish.alumini.R;
import com.example.ashish.alumini.fragments.FragmentJobDetails;
import com.example.ashish.alumini.fragments.FragmentJobs;
import com.example.ashish.alumini.fragments.FragmentMembers;
import com.example.ashish.alumini.fragments.settings.FragmentSettings;
import com.example.ashish.alumini.fragments.common_fragments.FragmentGetProfileData;
import com.example.ashish.alumini.fragments.common_fragments.FragmentWebView;
import com.example.ashish.alumini.fragments.settings.FragmentAboutApp;
import com.example.ashish.alumini.fragments.settings.FragmentFaq;
import com.example.ashish.alumini.fragments.settings.FragmentJobPosting;
import com.example.ashish.alumini.fragments.settings.FragmentProfile;
import com.example.ashish.alumini.supporting_classes.CommonData;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

public class PostLoginActivity extends AppCompatActivity {

    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    // creating instance so that one instance can be used over the whole app
//    public FragmentJobs mFragmentJob = new FragmentJobs();

    Bus mBus = new Bus();

    /*
    * To manage the back press button
    * */
    Boolean mBackToMainScreen = true;
    Boolean mBackToSettings = true;
    Boolean mBackToJobList = true;

    ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_member);
        //Setting the Action Bar
        mActionBar = getSupportActionBar();

        //setting up the bar
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setElevation(0);
        mActionBar.setTitle("Members");

        //event bus registering
        mBus.register(this);

        mFragmentManager = getSupportFragmentManager();



        //setting first fragement
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.add(R.id.fragment_container,new FragmentMembers().newInstance(null,null));
        mFragmentTransaction.commit();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            onBackPressed();
        return true;
    }


    public void changeFragment(android.support.v4.app.Fragment fragment){
        if (findViewById(R.id.fragment_container)!=null){
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.fragment_container,fragment);
            mFragmentTransaction.commit();
        }

    }

    @Subscribe
    public void changingFragment(Integer id){
        switch (id){
            case R.id.linearLayout_home :
                mBackToMainScreen=true;

                break;

            case R.id.linearLayout_filter :
                mBackToMainScreen=true;
                break;

            case R.id.linearLayout_jobs :
                mBackToMainScreen=true;
                break;

            case R.id.linearLayout_settings :
                mBackToMainScreen=true;
                break;

            case 9999 :
                mBackToSettings=true;
                mBackToJobList=false;
                mBackToMainScreen=false;
                break;

            case 8888 :
                mBackToJobList = true;
                mBackToSettings=false;
                mBackToMainScreen=false;
                break;
            case R.id.recycler_view :
                mBackToJobList = null;
                mBackToSettings=false;
                mBackToMainScreen=false;
                break;

        }
    }

    @Override
    public void onBackPressed() {
        if (!mBackToMainScreen){
            mBackToMainScreen=true;
            if (mBackToSettings){
                changeFragment(new FragmentSettings().newInstance(null,null));
            }
            else if (mBackToJobList==null){
                changeFragment(new FragmentMembers().newInstance("",""));
            }
            else if (mBackToJobList){
                changeFragment(CommonData.fragmentJobs);
            }

        }
        else
            super.onBackPressed();
    }
}
