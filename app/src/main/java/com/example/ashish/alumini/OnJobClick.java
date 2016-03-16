package com.example.ashish.alumini;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by ashish on 11/3/16.
 */
public class OnJobClick extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_job_click);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#d60d0d")));

        ListView listJob = (ListView) findViewById(R.id.listView_jobpost);
        ArrayList <JobListVar> list = new ArrayList<>();
        for (int i = 0; i<6; i++){
            JobListVar listVar = new JobListVar(" Name " + i, BitmapFactory.decodeResource(getResources(), R.drawable.image));
            list.add(listVar);
        }
        JobListAdapter memberAdapter= new JobListAdapter(this,R.layout.simple_list_item_job,list);
        listJob.setAdapter(memberAdapter);

        listJob.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(OnJobClick.this, jobProfile.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
            }
        });



        ImageButton button_jobs = (ImageButton) findViewById(R.id.button_jobs);
        button_jobs.setClickable(false);
        button_jobs.setBackgroundColor(Color.parseColor("#f5f5f5"));
        ImageButton imageButton_home ,  imageButton_filter, imageButton_setting;
        imageButton_home = (ImageButton) findViewById(R.id.button_home);
        imageButton_filter = (ImageButton) findViewById(R.id.button_filter);
        imageButton_setting = (ImageButton) findViewById(R.id.button_setting);
        imageButton_filter.setOnClickListener(this);
        imageButton_home.setOnClickListener(this);
        imageButton_setting.setOnClickListener(this);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        // Assumes current activity is the searchable activity
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            this.finish();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_setting:
                Intent intent2 = new Intent(OnJobClick.this, onSettingClicked.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
                finish();
                break;
            case R.id.button_filter:
                Intent intent3 = new Intent(OnJobClick.this, onFilterClick.class);
                startActivity(intent3);
                overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
                finish();
                break;
            case R.id.button_home:
                Intent intent4 = new Intent(OnJobClick.this, MainScreen.class);
                startActivity(intent4);
                overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
                finish();
                break;
        }
    }
}
