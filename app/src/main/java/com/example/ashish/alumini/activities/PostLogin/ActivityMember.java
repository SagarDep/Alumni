package com.example.ashish.alumini.activities.PostLogin;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.ashish.alumini.Fragments.BlankFragment;
import com.example.ashish.alumini.Fragments.FragmentJobs;
import com.example.ashish.alumini.Fragments.FragmentMembers;
import com.example.ashish.alumini.Fragments.FragmentMenu;
import com.example.ashish.alumini.Fragments.FragmentSettings;
import com.example.ashish.alumini.Fragments.settings.FragmentFaq;
import com.example.ashish.alumini.R;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

public class ActivityMember extends AppCompatActivity implements
        FragmentMenu.OnFragmentInteractionListener,
        FragmentSettings.OnFragmentInteractionListener,
        FragmentJobs.OnFragmentInteractionListener,
        BlankFragment.OnFragmentInteractionListener,
        FragmentMembers.OnFragmentInteractionListener,
        FragmentFaq.OnFragmentInteractionListener
{

    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    Bus mBus = new Bus();

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


    public void changeFragment(Fragment fragment){
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
                changeFragment(new FragmentMembers().newInstance(null,null));
                break;

            case R.id.linearLayout_filter :
                changeFragment(new BlankFragment().newInstance(null,null));
                break;

            case R.id.linearLayout_jobs :
                changeFragment(new FragmentJobs().newInstance(null,null));
                break;

            case R.id.linearLayout_settings :
                changeFragment(new FragmentSettings().newInstance(null,null));
                break;

            case R.id.button_faq :
                changeFragment(new FragmentFaq().newInstance(null,null));
        }
    }
}
