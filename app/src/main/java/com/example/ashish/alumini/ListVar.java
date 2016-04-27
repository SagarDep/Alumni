package com.example.ashish.alumini;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.Image;
import android.util.Log;

/**
 * Created by ashish on 3/3/16.
 */
public class ListVar {
    String uid;
    Image image ;
    String name ;
    String year_passing;
    String designation;
    String location_work;
    String branch;
    String company;
    Bitmap bitmap;
    public ListVar(){

    }
    public ListVar(String uid , String name2, Bitmap bitmap2, String designation2, String company, String location_work, String branch, String year_passing){
        this.uid = uid;
        this.name = name2;
       this.bitmap= Bitmap.createBitmap(bitmap2);
        this.designation=designation2;
        this.company = company;
        this.location_work = location_work;
        this.branch = branch;
        this.year_passing = year_passing;
    }
    public static Bitmap getCircleBitmap(Bitmap bitmap) {
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

//        bitmap.recycle();

        return output;
    }
}
