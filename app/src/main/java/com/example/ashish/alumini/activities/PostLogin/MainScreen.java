package com.example.ashish.alumini.activities.PostLogin;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ashish.alumini.deepak.about_college.About_college;
import com.example.ashish.alumini.deepak.events.EventPage;
import com.example.ashish.alumini.R;
import com.example.ashish.alumini.fragments.FragmentMainScreen;
import com.squareup.otto.Bus;


public class MainScreen extends AppCompatActivity
{

    at.markushi.ui.CircleButton events,about,member;

    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    Bus mBus = new Bus();

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main_screen);


        mBus.register(this);
        mFragmentManager = getSupportFragmentManager();

        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.add(R.id.container_main_screen, new FragmentMainScreen().newInstance("",""));
        mFragmentTransaction.commit();



        getSupportActionBar().hide();
    }

    public void changeFragment(Fragment fragment){
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.container_main_screen, fragment);
        mFragmentTransaction.commit();


    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}

