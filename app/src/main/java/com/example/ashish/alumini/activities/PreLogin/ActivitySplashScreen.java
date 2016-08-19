package com.example.ashish.alumini.activities.PreLogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ashish.alumini.R;
import com.example.ashish.alumini.activities.PostLogin.MainScreen;

public class ActivitySplashScreen extends AppCompatActivity {

    SharedPreferences mSharedPreferences;
    Intent mIntent;
    String mLoginKey = "LOGIN_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        //hide the actionBar
        getSupportActionBar().hide();

        mSharedPreferences = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        Boolean spLogin = mSharedPreferences.getBoolean(mLoginKey,false);



        if (spLogin==true){
            mIntent = new Intent(this, MainScreen.class);
        }
        else
            mIntent = new Intent(this,MainActivity.class);


        //creating thread to hold screen for splash
        Thread Splashtimer = new Thread(){
            public void run(){
                try{
                    sleep(2000);
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
