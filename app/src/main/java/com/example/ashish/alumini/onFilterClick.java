package com.example.ashish.alumini;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;

import com.example.ashish.alumini.ListMembers.ExpandableList.MyExpandableListAdapter;
//import com.example.ashish.alumini.Job.OnJobClick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ashish on 9/3/16.
 */
public class onFilterClick extends AppCompatActivity implements View.OnClickListener{

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_filter_click);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e53935")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageButton filter = (ImageButton) findViewById(R.id.button_filter);
        filter.setBackgroundColor(Color.parseColor("#f5f5f5"));
        filter.setClickable(false);



        ImageButton imageButton_home ,  imageButton_job, imageButton_setting;
        imageButton_home = (ImageButton) findViewById(R.id.button_home);
        imageButton_job = (ImageButton) findViewById(R.id.button_jobs);
//        imageButton_setting = (ImageButton) findViewById(R.id.button_setting);
        imageButton_job.setOnClickListener(this);
        imageButton_home.setOnClickListener(this);
//        imageButton_setting.setOnClickListener(this);


        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
//        prepareListData();
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();
        listDataHeader.add("Branch");
        listDataHeader.add("Graduation Year");
        listDataChild = new HashMap<>();


        List<String> branch = new ArrayList<>();
        branch.add("CSE");
        branch.add("AE");
        branch.add("ME");
        branch.add("EICE");
        branch.add("IT");
        branch.add("EE");
        branch.add("ECE");
        List<String> year = new ArrayList<>();
        for (int i=2004; i<2017; i++){
            year.add("" + i);
        }
        listDataChild.put(listDataHeader.get(0), branch);
        listDataChild.put(listDataHeader.get(1), year);
        listAdapter = new MyExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter

        expListView.setAdapter(listAdapter);

        // Listview Group click listener
//        expListView.setOnGroupClickListener(new OnGroupClickListener() {
//
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v,
//                                        int groupPosition, long id) {
//                // Toast.makeText(getApplicationContext(),
//                // "Group Clicked " + listDataHeader.get(groupPosition),
//                // Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });

        // Listview Group expanded listener
//        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {
//
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Expanded",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

        // Listview Group collasped listener
//        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
//
//            @Override
//            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Collapsed",
//                        Toast.LENGTH_SHORT).show();
//
//            }
//        });

        // Listview on child click listener
//        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v,
//                                        int groupPosition, int childPosition, long id) {
//                // TODO Auto-generated method stub
//
//            }
//        });


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
//        switch (v.getId()){
//            case R.id.button_jobs:
//                Intent intent = new Intent(onFilterClick.this, OnJobClick.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
//                finish();
//                break;
//            case R.id.button_setting:
//                Intent intent2 = new Intent(onFilterClick.this, ActivitySettings.class);
//                startActivity(intent2);
//                overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
//                finish();
//                break;
//
//            case R.id.button_home:
//                Intent intent4 = new Intent(onFilterClick.this,MainScreen.class);
//                startActivity(intent4);
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
