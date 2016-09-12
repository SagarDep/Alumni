package com.example.ashish.alumini.activities.PostLogin;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ashish.alumini.R;
import com.example.ashish.alumini.fragments.main_screen_fragments.FragmentEvents;
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

    ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_screen);

        mActionBar = getSupportActionBar();

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

        mActionBar.hide();
    }

    public void changeFragment(Fragment fragment){
        //updating the current fragment
       mPreviousFragment = mCurrentFragment;
        mCurrentFragment = fragment;

        //checking if actionbar needs to be hidden or not
        if (mCurrentFragment instanceof FragmentMainScreen){
            mActionBar.hide();
        }

        // to change the titile in case of onCLick the event listView
        else if (mPreviousFragment instanceof FragmentEvents && mCurrentFragment instanceof FragmentWebView){
            mActionBar.setTitle("About Event");
            mActionBar.show();
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
            TastyToast.makeText(getApplicationContext(),"Press back again to exit",Toast.LENGTH_SHORT,TastyToast.INFO);
            mBackCounter++;
            if (mBackCounter==2){
                // exit the application
                this.finish();
                System.exit(0);
            }
        }

        // case of list-->list clicked(i.e. web view)
        if (mPreviousFragment instanceof FragmentEvents && mCurrentFragment instanceof FragmentWebView){
            changeFragment(new FragmentEvents());
        }
        // if any fragment other than main screen
//        then return to the mainscreen fragment
        else if (!(mCurrentFragment instanceof FragmentMainScreen)){
            changeFragment(new FragmentMainScreen());

        }


    }
}

