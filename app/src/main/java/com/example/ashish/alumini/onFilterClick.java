package com.example.ashish.alumini;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by ashish on 9/3/16.
 */
public class onFilterClick extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_filter_click);
        ImageButton settings = (ImageButton) findViewById(R.id.button_setting);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(onFilterClick.this, onSettingClicked.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
