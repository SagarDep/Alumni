package com.example.ashish.alumini;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by ashish on 9/3/16.
 */
public class onSettingClicked extends Activity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_setting_pressed);
        TextView textView_viewprofile = (TextView) findViewById(R.id.textView_myprofile);
        textView_viewprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.afterlistclick);
                TextView textView = (TextView) findViewById(R.id.textView_year);
                textView.setVisibility(View.INVISIBLE);
                ImageButton edit_image = (ImageButton) findViewById(R.id.edit_image);
                edit_image.setMinimumWidth(20);
                edit_image.setImageResource(R.drawable.edit);
                edit_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(onSettingClicked.this, Edit_Profile.class);
                        startActivity(intent);
                    }
                });
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setContentView(R.layout.on_setting_pressed);
    }
}
