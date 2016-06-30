package com.example.ashish.alumini.Activities;


import android.graphics.Color;
import android.os.Bundle;

import android.app.TabActivity;
import android.content.Intent;
import android.util.TypedValue;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.ashish.alumini.Login;
import com.example.ashish.alumini.R;
import com.example.ashish.alumini.SignUp;

public class MainActivity extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;

        Intent intent;

        intent = new Intent().setClass(this, Login.class);
        spec = tabHost.newTabSpec("Login").setIndicator("Login")
                .setContent(intent);
        tabHost.addTab(spec);


        intent = new Intent().setClass(this,SignUp.class);
        spec = tabHost.newTabSpec("Sign Up").setIndicator("Sign Up")
                .setContent(intent);
        tabHost.addTab(spec);


        for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
        {
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#ffffff"));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        }


    }



    @Override
    public void onBackPressed() {

        moveTaskToBack(true);
    }


}