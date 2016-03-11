package com.example.ashish.alumini;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ashish on 9/3/16.
 */
public class onSettingClicked extends Activity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_setting_click);
        Button button_viewprofile = (Button) findViewById(R.id.textView_myprofile);
        ImageButton imageButton = (ImageButton) findViewById(R.id.button_setting);
        imageButton.setBackgroundColor(Color.parseColor("#f5f5f5"));
        button_viewprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(onSettingClicked.this, profile.class);
                startActivity(intent);
            }
        });
        ImageButton filter = (ImageButton) findViewById(R.id.button_filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(onSettingClicked.this, onFilterClick.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setContentView(R.layout.on_setting_click);
    }
}
