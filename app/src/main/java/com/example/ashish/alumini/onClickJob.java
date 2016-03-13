package com.example.ashish.alumini;

import android.app.ActionBar;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

/**
 * Created by ashish on 11/3/16.
 */
public class onClickJob extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_job_click);
        ActionBar actionBar = getActionBar();
        actionBar.show();
        actionBar.setTitle("More");

        ListView listJob = (ListView) findViewById(R.id.listView_jobpost);
        ArrayList <JobListVar> list = null;
        for (int i = 0; i<10; i++){
            JobListVar listVar = new JobListVar(" Name " + i, BitmapFactory.decodeResource(getResources(), R.drawable.image));
            list.add(listVar);
        }
        JobListAdapter memberAdapter= new JobListAdapter(this,R.layout.simple_list_item,list);
        listJob.setAdapter(memberAdapter);


        ImageButton button = (ImageButton) findViewById(R.id.button_jobs);
        button.setBackgroundColor(Color.parseColor("#f5f5f5"));
        button.setClickable(false);
    }
}
