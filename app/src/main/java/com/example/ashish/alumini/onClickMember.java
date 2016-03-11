package com.example.ashish.alumini;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class onClickMember extends Activity {
    private ArrayList<ListVar> list_members;
    private MemberAdapter memberAdapter;
    private ListView listView;
    private ListView listView2;
    private TextView notfound;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_click_members);
        listView = (ListView) findViewById(R.id.listView);
        listView2 = (ListView) findViewById(R.id.listView2);
        listView2.setVisibility(View.GONE);
        //-------TEMP

       list_members = new ArrayList<>();
        for (int i = 0; i<15; i++){
            ListVar listVar = new ListVar(" Name " + i,BitmapFactory.decodeResource(getResources(),R.drawable.image));
            list_members.add(listVar);
        } //------TEMP
        memberAdapter= new MemberAdapter(this,R.layout.simple_list_item,list_members);
        listView.setAdapter(memberAdapter);
        memberAdapter = new MemberAdapter(this,R.layout.simple_list_item,list_members);
        listView2.setAdapter(memberAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(onClickMember.this, profile.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
            }
        });
        ImageButton button_jobs = (ImageButton) findViewById(R.id.button_jobs);
        button_jobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(onClickMember.this,onClickJob.class);
                startActivity(intent);
            }
        });
        final ImageButton setting = (ImageButton) findViewById(R.id.button_setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(onClickMember.this,onSettingClicked.class);
                startActivity(intent);
            }
        });
        ImageButton filter  = (ImageButton) findViewById(R.id.button_filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(onClickMember.this,onFilterClick.class);
                startActivity(intent);
            }
        });
        ImageView imageView_search = (ImageView) findViewById(R.id.image_search);
        imageView_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(onClickMember.this,onSearch.class);
                startActivity(intent);
            }
        });
    }
    public void search_function(CharSequence key){
        Boolean found = false;
        for (int i=0; i<list_members.size(); i++){
            if (list_members.get(i).name.contains(key)){
                Log.d("FOUND","YES");
                found = true;
            }
        }
        if (!found){
            notfound = (TextView) findViewById(R.id.textView_bio);
            notfound.setText("Sorry!! Result Not Found");
            listView.setVisibility(View.GONE);
            listView2.setVisibility(View.GONE);

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
