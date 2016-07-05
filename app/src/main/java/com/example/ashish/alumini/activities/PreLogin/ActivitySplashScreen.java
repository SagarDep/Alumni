package com.example.ashish.alumini.activities.PreLogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ashish.alumini.MainActivity;
import com.example.ashish.alumini.R;
import com.example.ashish.alumini.activities.PostLogin.MainScreen;

public class ActivitySplashScreen extends AppCompatActivity {

    SharedPreferences mSharedPreferences;
    Intent mIntent;
    String mLoginKey = "IS_LOGGED_IN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mSharedPreferences = getPreferences(MODE_PRIVATE);
        Boolean aBoolean = mSharedPreferences.getBoolean(mLoginKey,true);

        //hide the actionBar
        getSupportActionBar().hide();
        if (aBoolean==false){
            mIntent = new Intent(ActivitySplashScreen.this,MainActivity.class);
        }
        else mIntent = new Intent(this, MainScreen.class);


        //creating thread to hold screen for splash
        Thread Splashtimer = new Thread(){
            public void run(){
                try{
                    sleep(1);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{

                    startActivity(mIntent);
                }
            }
        };
        Splashtimer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
         finish();
    }


}
