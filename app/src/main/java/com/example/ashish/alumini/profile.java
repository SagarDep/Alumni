package com.example.ashish.alumini;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ashish on 4/3/16.
 */
public class profile extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.afterlistclick);
        Bitmap bm = BitmapFactory.decodeResource(getResources(),R.drawable.image);
        ImageView mImage = (ImageView) findViewById(R.id.imageView_profilepic);
        mImage.setImageBitmap(ListVar.getCircleBitmap(bm));
        TextView textView = (TextView) findViewById(R.id.textView_year);
        textView.setVisibility(View.INVISIBLE);
        ImageButton edit_image = (ImageButton) findViewById(R.id.edit_image);
        edit_image.setMinimumWidth(20);
        edit_image.setImageResource(R.drawable.edit);
        edit_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, Edit_Profile.class);
                startActivity(intent);
            }
        });
    }
}
