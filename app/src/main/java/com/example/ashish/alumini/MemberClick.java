package com.example.ashish.alumini;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

//import com.example.ashish.alumini.Job.OnJobClick;

import com.example.ashish.alumini.Fragments.viewpager.FragmentViewPager1;
import com.example.ashish.alumini.Fragments.viewpager.FragmentViewPager3;
import com.example.ashish.alumini.Fragments.viewpager.FragmentViewPager2;

import com.example.ashish.alumini.supporting_classes.*;

/**
 * Created by ashish on 11/3/16.
 */
public class MemberClick extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e53935")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);


        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(mViewPager, null);

        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);
        ImageButton imageButton_home , imageButton_job , imageButton_filter, imageButton_setting;
        imageButton_home = (ImageButton) findViewById(R.id.button_home);
        imageButton_job = (ImageButton) findViewById(R.id.button_jobs);
        imageButton_filter = (ImageButton) findViewById(R.id.button_filter);


    }
    private void setupViewPager(ViewPager viewPager,String where) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentViewPager1(), "ALL");
        adapter.addFragment(new FragmentViewPager2(),"PROFESSIONALS");
        adapter.addFragment(new FragmentViewPager3(),  "Post Grads.");
        viewPager.setAdapter(adapter);
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
}
