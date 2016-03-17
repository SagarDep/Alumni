package com.example.ashish.alumini;

import android.support.v4.app.ListFragment;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashish on 11/3/16.
 */
public class OnMemberClick extends AppCompatActivity implements View.OnClickListener{
    private TabLayout tabLayout_top;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_click_member);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e53935")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);





        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager, null);

        tabLayout_top = (TabLayout) findViewById(R.id.tabs);
        tabLayout_top.setupWithViewPager(viewPager);
        ImageButton imageButton_home , imageButton_job , imageButton_filter, imageButton_setting;
        imageButton_home = (ImageButton) findViewById(R.id.button_home);
        imageButton_job = (ImageButton) findViewById(R.id.button_jobs);
        imageButton_filter = (ImageButton) findViewById(R.id.button_filter);
        imageButton_setting = (ImageButton) findViewById(R.id.button_setting);
        imageButton_filter.setOnClickListener(this);
        imageButton_home.setOnClickListener(this);
        imageButton_setting.setOnClickListener(this);
        imageButton_job.setOnClickListener(this);

    }
    private void setupViewPager(ViewPager viewPager,String where) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new All(), "ALL");
        adapter.addFragment(new Proffesional(),"PROFESSIONALS");
        adapter.addFragment(new PG(),  "Post Grads.");
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


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(ListFragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onClick(View v) {
        Log.d("WTF","I'm Here");
     switch (v.getId()){
         case R.id.button_jobs:
             Intent intent = new Intent(OnMemberClick.this, OnJobClick.class);
             startActivity(intent);
             overridePendingTransition(R.anim.slide_out, R.anim.slide_in);

             break;
         case R.id.button_setting:
             Intent intent2 = new Intent(OnMemberClick.this, onSettingClicked.class);
             startActivity(intent2);
             overridePendingTransition(R.anim.slide_out, R.anim.slide_in);

             break;
         case R.id.button_filter:
             Intent intent3 = new Intent(OnMemberClick.this, onFilterClick.class);
             startActivity(intent3);
             overridePendingTransition(R.anim.slide_out, R.anim.slide_in);

             break;
         case R.id.button_home:
             Intent intent4 = new Intent(OnMemberClick.this,MainScreen.class);
             startActivity(intent4);
             overridePendingTransition(R.anim.slide_out, R.anim.slide_in);

             break;
     }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            this.finish();
        return true;
    }
}
