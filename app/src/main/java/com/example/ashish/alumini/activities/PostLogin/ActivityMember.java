package com.example.ashish.alumini.activities.PostLogin;

import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.ashish.alumini.Fragments.Fragment;
import com.example.ashish.alumini.Fragments.FragmentJobDetails;
import com.example.ashish.alumini.Fragments.FragmentJobs;
import com.example.ashish.alumini.Fragments.FragmentMembers;
import com.example.ashish.alumini.Fragments.FragmentMenu;
import com.example.ashish.alumini.Fragments.FragmentSettings;
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
        Fragment.OnFragmentInteractionListener,
        FragmentMembers.OnFragmentInteractionListener,
        FragmentFaq.OnFragmentInteractionListener,
        FragmentJobPosting.OnFragmentInteractionListener,
        FragmentAboutApp.OnFragmentInteractionListener,
        FragmentProfile.OnFragmentInteractionListener,
        FragmentJobDetails.OnFragmentInteractionListener{

    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    FragmentJobs mFragmentJob =  new FragmentJobs().newInstance("","");

    Fragment mFragment;

    Bus mBus = new Bus();

    Boolean mBackToMainScreen = true;
    Boolean mBackToSettings = true;
    Boolean mBackToJobList = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_member);
        //Setting the Action Bar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.appTheme)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
            case R.id.linearLayout_home :
                mBackToMainScreen=true;
                changeFragment(new FragmentMembers().newInstance(null,null));
                break;

            case R.id.linearLayout_filter :
                mBackToMainScreen=true;
                changeFragment(new Fragment().newInstance(null,null));
                break;

            case R.id.linearLayout_jobs :
                mBackToMainScreen=true;
//                changeFragment(mFragmentJob);
                changeFragment(new FragmentJobs().newInstance("","  "));
                break;

            case R.id.linearLayout_settings :
                mBackToMainScreen=true;
                changeFragment(new FragmentSettings().newInstance(null,null));
                break;

            case 9999 :
                mBackToSettings=true;
                mBackToJobList=false;
                break;

            case 8888 :
                mBackToJobList = true;
                mBackToSettings=false;
                break;
////                changeFragment(new FragmentJobPosting().newInstance(null,null));
//
//            case R.id.button_myprofile :
//                mBackToMainScreen=false;
////                changeFragment(new FragmentFaq().newInstance(null,null));
//
//            case R.id.button_fbPage :
//                mBackToMainScreen=false;
////                changeFragment(new FragmentFaq().newInstance(null,null));
//
//            case R.id.button_about_app :
//                mBackToMainScreen=false;
////                changeFragment(new FragmentFaq().newInstance(null,null));
//
//            case R.id.button_about_college :
//                mBackToMainScreen=false;
////                changeFragment(new FragmentFaq().newInstance(null,null));
//
//            case R.id.button_support :
//                mBackToMainScreen=false;
////                changeFragment(new FragmentFaq().newInstance(null,null));
//
//            case R.id.button_contact_us :
//                mBackToMainScreen=false;
////                changeFragment(new FragmentFaq().newInstance(null,null));
//
//            case R.id.button_faq :
//                mBackToMainScreen=false;
////                changeFragment(new FragmentFaq().newInstance(null,null));
//
//            case R.id.button_rate_us :
//                mBackToMainScreen=false;
////                changeFragment(new FragmentFaq().newInstance(null,null));
        }
    }

    @Override
    public void onBackPressed() {
        if (mBackToSettings==true){
            changeFragment(new FragmentSettings().newInstance(null,null));
        }
        else if (mBackToJobList==true){
            changeFragment(new FragmentJobs().newInstance(null,null));
        }
        else
            super.onBackPressed();
    }
}
