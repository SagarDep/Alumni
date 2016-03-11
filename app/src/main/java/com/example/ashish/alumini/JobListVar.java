package com.example.ashish.alumini;

import android.graphics.Bitmap;

/**
 * Created by ashish on 11/3/16.
 */
public class JobListVar {
    Bitmap logo;
    String com_name;
    String com_loc;
    String com_post;
    String no_of_post;
    String end_date;
    String type;

    public JobListVar(String com_name, Bitmap logo) {
        this.com_name = com_name;
        this.logo = logo;
    }
}
