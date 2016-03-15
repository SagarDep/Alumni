package com.example.ashish.alumini;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.hp.signupdegin.R;

public class MainScreen extends AppCompatActivity  implements View.OnClickListener{

    at.markushi.ui.CircleButton events,about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        events=(at.markushi.ui.CircleButton)findViewById(R.id.event);
        events.setOnClickListener(this);

        about=(at.markushi.ui.CircleButton)findViewById(R.id.about);
        about.setOnClickListener(this);

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


            default:
                break;
        }

    }


}

