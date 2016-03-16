package com.example.ashish.alumini;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ashish.alumini.R;

public class Faq extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faq);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#d60d0d")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
