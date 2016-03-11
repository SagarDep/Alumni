package com.example.ashish.alumini;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by ashish on 11/3/16.
 */
public class onClickJob extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_job_click);

        ImageButton imageButton_searchjob = (ImageButton) findViewById(R.id.search_jobs);
        imageButton_searchjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.job_search);
            }
        });
        ListView listJob = (ListView) findViewById(R.id.listView_jobpost);
        ArrayList <JobListVar> list ;

    }
}
