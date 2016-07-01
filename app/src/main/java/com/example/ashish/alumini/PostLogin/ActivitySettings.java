package com.example.ashish.alumini.PostLogin;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.ashish.alumini.About_app;
import com.example.ashish.alumini.Contact_us;
import com.example.ashish.alumini.Faq;
//import com.example.ashish.alumini.Job.OnJobClick;
import com.example.ashish.alumini.Job.jobPost;
import com.example.ashish.alumini.R;
import com.example.ashish.alumini.onFilterClick;
import com.example.ashish.alumini.profile;

/**
 * Created by ashish on 9/3/16.
 */
public class ActivitySettings extends AppCompatActivity implements  View.OnClickListener{
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings);

//        ImageButton   button_postJob , button_filter,  button_home;
//
//        button_filter = (ImageButton) findViewById(R.id.button_filter);
//        button_filter.setOnClickListener(this);
//        button_postJob = (ImageButton) findViewById(R.id.button_jobs);
//        button_postJob.setOnClickListener(this);
//        button_home = (ImageButton) findViewById(R.id.button_home);
//        button_home.setOnClickListener(this);



//        Button button_faq , button_contact_us , button_postjob , button_myprofile , button_fbpage
//                , button_aboutapp , button_aboutcollege , button_support;
//        button_faq = (Button) findViewById(R.id.button_faq);
//        button_faq.setOnClickListener(this);
//        button_contact_us = (Button) findViewById(R.id.button_contact_us);
//        button_contact_us.setOnClickListener(this);
//        button_postjob = (Button) findViewById(R.id.button_postjob);
//        button_postjob.setOnClickListener(this);
//        button_myprofile = (Button) findViewById(R.id.button_myprofile);
//        button_myprofile.setOnClickListener(this);
//        button_fbpage = (Button) findViewById(R.id.button_fbPage);
//        button_fbpage.setOnClickListener(this);
//        button_aboutapp= (Button) findViewById(R.id.button_about_app);
//        button_aboutapp.setOnClickListener(this);
//        button_aboutcollege= (Button) findViewById(R.id.button_about_college);
//        button_aboutcollege.setOnClickListener(this);
//        button_support= (Button) findViewById(R.id.button_support);
//        button_support.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.button_faq:
//                Intent intent = new Intent(ActivitySettings.this, Faq.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
//                break;
//            case R.id.button_contact_us:
//                Intent intent2 = new Intent(ActivitySettings.this,Contact_us.class);
//                startActivity(intent2);
//                overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
//                break;
//            case R.id.button_about_app:
//                Intent intent3 = new Intent(ActivitySettings.this,About_app.class);
//                startActivity(intent3);
//                overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
//                break;
//            case R.id.button_about_college:
//                Uri uri = Uri.parse("http://www.google.com");
//                Intent intent4 = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent4);
//                overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
//                break;
//            case R.id.button_postjob:
//                Intent intent5 = new Intent(ActivitySettings.this,jobPost.class);
//                startActivity(intent5);
//                overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
//                break;
//            case R.id.button_fbPage:
//                Uri uri2 = Uri.parse("http://www.google.com");
//                Intent intent6 = new Intent(Intent.ACTION_VIEW, uri2);
//                startActivity(intent6);
//                overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
//                break;
//            case R.id.button_myprofile:
//                Intent intent7 = new Intent(ActivitySettings.this, profile.class);
//                startActivity(intent7);
//                overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
//                break;
//            case R.id.button_filter:
//                Intent intent8 = new Intent(ActivitySettings.this, onFilterClick.class);
//                startActivity(intent8);
//                overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
//                finish();
//                break;
//            case R.id.button_jobs:
//                Intent intent9 = new Intent(ActivitySettings.this, OnJobClick.class);
//                startActivity(intent9);
//                overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
//                finish();
//                break;
//            case R.id.button_home:
//                Intent intent10 = new Intent(ActivitySettings.this, MainScreen.class);
//                startActivity(intent10);
//                overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
//                finish();
//                break;
//        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            this.finish();
        return true;
    }
}
