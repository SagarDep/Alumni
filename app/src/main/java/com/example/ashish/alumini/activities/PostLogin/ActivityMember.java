package com.example.ashish.alumini.activities.PostLogin;

import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.ashish.alumini.Fragments.BlankFragment;
import com.example.ashish.alumini.Fragments.common_fragments.FragmentGetProfileData;
import com.example.ashish.alumini.Fragments.FragmentJobDetails;
import com.example.ashish.alumini.Fragments.FragmentJobs;
import com.example.ashish.alumini.Fragments.FragmentMembers;
import com.example.ashish.alumini.Fragments.FragmentMenu;
import com.example.ashish.alumini.Fragments.FragmentSettings;
import com.example.ashish.alumini.Fragments.common_fragments.FragmentWebView;
import com.example.ashish.alumini.Fragments.settings.FragmentAboutApp;
import com.example.ashish.alumini.Fragments.settings.FragmentFaq;
import com.example.ashish.alumini.Fragments.settings.FragmentJobPosting;
import com.example.ashish.alumini.Fragments.settings.FragmentProfile;
import com.example.ashish.alumini.R;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

public class ActivityMember extends AppCompatActivity implements
        FragmentMenu.OnFragmentInteractionListener,
        FragmentSettings.OnFragmentInteractionListener,
        FragmentJobs.OnFragmentInteractionListener,
        BlankFragment.OnFragmentInteractionListener,
        FragmentMembers.OnFragmentInteractionListener,
        FragmentFaq.OnFragmentInteractionListener,
        FragmentJobPosting.OnFragmentInteractionListener,
        FragmentAboutApp.OnFragmentInteractionListener,
        FragmentProfile.OnFragmentInteractionListener,
        FragmentJobDetails.OnFragmentInteractionListener,
        FragmentGetProfileData.OnFragmentInteractionListener,
        FragmentWebView.OnFragmentInteractionListener{

    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    public FragmentJobs mFragmentJob = new FragmentJobs().newInstance("","");

    BlankFragment mFragment;

    Bus mBus = new Bus();

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
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setElevation(0);




        mBus.register(this);

        mFragmentManager = getSupportFragmentManager();

        //setting
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.add(R.id.fragment_container,new FragmentMembers().newInstance(null,null));
        mFragmentTransaction.commit();
    
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            this.finish();
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

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
            case R.id.button_home :
                mBackToMainScreen=true;
                changeFragment(new FragmentMembers().newInstance(null,null));
                break;

            case R.id.button_filter :
                mBackToMainScreen=true;
                break;

            case R.id.button_jobs :
                mBackToMainScreen=true;
                break;

            case R.id.button_settings :
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
            default:
                Log.d("j0","");
        }
    }

    @Override
    public void onBackPressed() {
        if (mBackToMainScreen==false){
            mBackToMainScreen=true;
            if (mBackToSettings==true){
                changeFragment(new FragmentSettings().newInstance(null,null));
            }
            else if (mBackToJobList==true){
                changeFragment(new FragmentJobs().newInstance("",""));
            }
        }
        else
            super.onBackPressed();
    }
}
