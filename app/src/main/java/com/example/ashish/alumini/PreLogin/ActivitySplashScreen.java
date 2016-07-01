package com.example.ashish.alumini.PreLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ashish.alumini.MainActivity;
import com.example.ashish.alumini.R;

public class ActivitySplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //hide the actionBar
        getSupportActionBar().hide();

        //creating thread to hold screen for splash
        Thread Splashtimer = new Thread(){
            public void run(){
                try{
                    sleep(1);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(ActivitySplashScreen.this,MainActivity.class);
                    startActivity(intent);
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
