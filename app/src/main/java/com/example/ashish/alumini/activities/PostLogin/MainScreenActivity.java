package com.example.ashish.alumini.activities.PostLogin;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ashish.alumini.R;
import com.example.ashish.alumini.fragments.BlankFragment;
import com.example.ashish.alumini.fragments.FragmentEvents;
import com.example.ashish.alumini.fragments.FragmentMainScreen;
import com.example.ashish.alumini.fragments.common_fragments.FragmentGetProfileData;
import com.example.ashish.alumini.fragments.common_fragments.FragmentWebView;
import com.sdsmdg.tastytoast.TastyToast;
import com.squareup.otto.Bus;


public class MainScreenActivity extends AppCompatActivity
{


    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    // for backpressed actions
    Fragment mCurrentFragment;
    Fragment mPreviousFragment;

    // event bus
    Bus mBus = new Bus();

    int mBackCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main_screen);


        mBus.register(this);
        mFragmentManager = getSupportFragmentManager();

        // checking if the activity is started from signup activity or splash activity
        Boolean isSignup = false;
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            // this means activity is started from signup activity
            isSignup = (Boolean) bundle.get("SIGNUP");
        }


        mFragmentTransaction = mFragmentManager.beginTransaction();
        if (isSignup==true){
            // show the getData fragment
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
            TastyToast.makeText(getApplicationContext(),"Press back again",Toast.LENGTH_SHORT,TastyToast.INFO);
            mBackCounter++;
            if (mBackCounter==2){


                System.exit(0);
            }
        }

        if (mPreviousFragment instanceof FragmentEvents && mCurrentFragment instanceof FragmentWebView){
            changeFragment(new FragmentEvents().newInstance("",""));
        }
        else if (!(mCurrentFragment instanceof FragmentMainScreen)){
            changeFragment(new FragmentMainScreen().newInstance("",""));

        }


    }
}

