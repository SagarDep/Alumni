package com.example.ashish.alumini.activities.PostLogin;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.ashish.alumini.R;
import com.example.ashish.alumini.fragments.BlankFragment;
import com.example.ashish.alumini.fragments.FragmentEvents;
import com.example.ashish.alumini.fragments.FragmentMainScreen;
import com.example.ashish.alumini.fragments.common_fragments.FragmentGetProfileData;
import com.example.ashish.alumini.fragments.common_fragments.FragmentWebView;
import com.squareup.otto.Bus;


public class ActivityMainScreen extends AppCompatActivity
{


    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    Fragment mCurrentFragment;
    Fragment mPreviousFragment;

    Bus mBus = new Bus();

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main_screen);


        mBus.register(this);
        mFragmentManager = getSupportFragmentManager();

        Boolean isSignup = false;
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
             isSignup = (Boolean) bundle.get("SIGNUP");
        }


        mFragmentTransaction = mFragmentManager.beginTransaction();
        if (isSignup==true){
            // show the get Data fragment
            mFragmentTransaction.add(R.id.container_main_screen, new FragmentGetProfileData().newInstance("",""));
        }else {
            //else show the min screen fragment
            mFragmentTransaction.add(R.id.container_main_screen, new FragmentMainScreen().newInstance("",""));
        }
        mFragmentTransaction.commit();
        mCurrentFragment = new FragmentMainScreen();


        getSupportActionBar().hide();
    }

    public void changeFragment(Fragment fragment){
        //updating the current fragment
       mPreviousFragment = mCurrentFragment;
        mCurrentFragment = fragment;

        //checking if actionbar needs to be hidden or not
        if (fragment instanceof FragmentMainScreen){
            getSupportActionBar().hide();
        }

        //changing the fragment
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.container_main_screen, fragment);
        mFragmentTransaction.commit();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            onBackPressed();
        return true;
            }


    @Override
    public void onBackPressed() {
        if (mCurrentFragment instanceof FragmentMainScreen){
            super.onBackPressed();

        }

        if (mPreviousFragment instanceof FragmentEvents && mCurrentFragment instanceof FragmentWebView){
            changeFragment(new FragmentEvents().newInstance("",""));
        }
        else if (!(mCurrentFragment instanceof FragmentMainScreen)){
            changeFragment(new FragmentMainScreen().newInstance("",""));

        }


    }
}

