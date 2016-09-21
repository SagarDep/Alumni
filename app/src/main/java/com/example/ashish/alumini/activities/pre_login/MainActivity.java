package com.example.ashish.alumini.activities.pre_login;


import android.graphics.Color;
import android.os.Bundle;

import android.app.TabActivity;
import android.content.Intent;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.ashish.alumini.R;
import com.example.ashish.alumini.supporting_classes.GlobalBus;
import com.squareup.otto.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.zhanghai.android.materialprogressbar.IndeterminateHorizontalProgressDrawable;

public class MainActivity extends TabActivity {
    /** Called when the activity is first created. */

    @Bind(R.id.material_progressBar_activity_main)
    ProgressBar progressBar;

    GlobalBus globalBus = GlobalBus.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // butterknife bindings
        ButterKnife.bind(this);

        globalBus.register(this);

        progressBar.setIndeterminateDrawable(new IndeterminateHorizontalProgressDrawable(this));

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
//        finish();
        Log.d("back pressed","yes");
    }

    @Subscribe
    public void hide(String a){

        progressBar.setVisibility(View.GONE);

    }

}