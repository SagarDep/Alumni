package com.example.ashish.alumini;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by ashish on 4/3/16.
 */
public class onClickMember extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.afterlistclick);

        Bitmap bm = BitmapFactory.decodeResource(getResources(),R.drawable.image);
        ImageView mImage = (ImageView) findViewById(R.id.imageView_profilepic);
        mImage.setImageBitmap(ListVar.getCircleBitmap(bm));
    }


}
