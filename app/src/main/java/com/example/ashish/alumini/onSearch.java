package com.example.ashish.alumini;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SearchView;

/**
 * Created by ashish on 8/3/16.
 */
public class onSearch extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_search);
        SearchView searchView = (SearchView) findViewById(R.id.searchView_member);
        searchView.setIconified(false);
    }
}
