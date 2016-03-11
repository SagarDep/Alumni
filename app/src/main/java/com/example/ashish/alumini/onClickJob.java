package com.example.ashish.alumini;

import android.app.ActionBar;
import android.graphics.BitmapFactory;
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
        ImageButton imageButton_searchjob = (ImageButton) findViewById(R.id.search_jobs);
        imageButton_searchjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.job_search);
                SearchView searchView_job = (SearchView) findViewById(R.id.searchView_job);
                searchView_job.setIconified(false);
            }
        });
        ListView listJob = (ListView) findViewById(R.id.listView_jobpost);
        ArrayList <JobListVar> list ;
        for (int i = 0; i<10; i++){
            ListVar listVar = new ListVar(" Name " + i, BitmapFactory.decodeResource(getResources(), R.drawable.image));
        }

    }
}
