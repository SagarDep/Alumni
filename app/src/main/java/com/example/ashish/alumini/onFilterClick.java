package com.example.ashish.alumini;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.app.SearchManager;
import android.content.Context;
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
public class onFilterClick extends AppCompatActivity{

    private ArrayList<String> parentItems = new ArrayList<String>();
    private ArrayList<Object> childItems = new ArrayList<Object>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_filter_click);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#d60d0d")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ImageButton settings = (ImageButton) findViewById(R.id.button_setting);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(onFilterClick.this, onSettingClicked.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
                finish();
            }
        });
        ImageButton button_jobs = (ImageButton) findViewById(R.id.button_jobs);
        button_jobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(onFilterClick.this,onClickJob.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
                finish();
            }
        });
        ImageButton filter  = (ImageButton) findViewById(R.id.button_filter);
        filter.setBackgroundColor(Color.parseColor("#f5f5f5"));



        ExpandableListView expandableList = (ExpandableListView) findViewById(R.id.expandableListView);













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
}
