package com.example.ashish.alumini;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;

import java.util.ArrayList;

/**
 * Created by ashish on 9/3/16.
 */
public class onFilterClick extends AppCompatActivity implements View.OnClickListener{

    private ArrayList<String> parentItems = new ArrayList<String>();
    private ArrayList<Object> childItems = new ArrayList<Object>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_filter_click);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#d60d0d")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageButton filter  = (ImageButton) findViewById(R.id.button_filter);
        filter.setBackgroundColor(Color.parseColor("#f5f5f5"));
        filter.setClickable(false);



        ExpandableListView expandableList = (ExpandableListView) findViewById(R.id.expandableListView);





        ImageButton imageButton_home ,  imageButton_job, imageButton_setting;
        imageButton_home = (ImageButton) findViewById(R.id.button_home);
        imageButton_job = (ImageButton) findViewById(R.id.button_jobs);
        imageButton_setting = (ImageButton) findViewById(R.id.button_setting);
        imageButton_job.setOnClickListener(this);
        imageButton_home.setOnClickListener(this);
        imageButton_setting.setOnClickListener(this);







    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main2, menu);

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_jobs:
                Intent intent = new Intent(onFilterClick.this, OnJobClick.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
                finish();
                break;
            case R.id.button_setting:
                Intent intent2 = new Intent(onFilterClick.this, onSettingClicked.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
                finish();
                break;

            case R.id.button_home:
                Intent intent4 = new Intent(onFilterClick.this,MainScreen.class);
                startActivity(intent4);
                overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
                finish();
                break;
        }
    }
}
