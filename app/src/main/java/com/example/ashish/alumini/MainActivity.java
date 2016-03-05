package com.example.ashish.alumini;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    public   ArrayList<ListVar> list_members;
    public MemberAdapter memberAdapter;
    public ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        //-------TEMP

       list_members = new ArrayList<>();
        for (int i = 0; i<15; i++){
            ListVar listVar = new ListVar(" Name " + i,BitmapFactory.decodeResource(getResources(),R.drawable.image));
            list_members.add(listVar);
        } //------TEMP
        memberAdapter= new MemberAdapter(this,R.layout.simple_list_item,list_members);
        listView.setAdapter(memberAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, onClickMember.class);
                startActivity(intent);
            }
        });


        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search_function(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
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
        if (found==false){
            TextView notfound = (TextView) findViewById(R.id.textView_notfound);
            notfound.setText("Sorry!! Result Not Found");
            listView.setVisibility(View.GONE);

        }
    }

}
