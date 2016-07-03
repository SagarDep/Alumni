package com.example.ashish.alumini.activities.PostLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ashish.alumini.ActivityMember;

import com.example.ashish.alumini.deepak.about_college.About_college;
import com.example.ashish.alumini.deepak.events.EventPage;
import com.example.ashish.alumini.R;


public class MainScreen extends AppCompatActivity  implements View.OnClickListener{

    at.markushi.ui.CircleButton events,about,member;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        events=(at.markushi.ui.CircleButton)findViewById(R.id.event);
        events.setOnClickListener(this);

        about=(at.markushi.ui.CircleButton)findViewById(R.id.about);
        about.setOnClickListener(this);

        member=(at.markushi.ui.CircleButton)findViewById(R.id.member);
        member.setOnClickListener(this);

        getSupportActionBar().hide();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.event:
                Intent moveToEvent=new Intent(this,EventPage.class);
                startActivity(moveToEvent);
                break;

            case R.id.about:
                Intent moveToAbout=new Intent(this,About_college.class);
                startActivity(moveToAbout);
                break;

            case R.id.member:
                Intent moveToMember=new Intent(this,ActivityMember.class);
                startActivity(moveToMember);
                break;


            default:
                break;
        }

    }


}

