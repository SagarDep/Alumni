package com.example.ashish.alumini.ListMembers;

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
public class MemberListInstance {
     public String uid;
    public Image image ;
    public String name ;
    public String year_passing;
    public String designation;
    public String location_work;
    public String branch;
    public String company;
    public Bitmap bitmap;

    public MemberListInstance(String uid , String name2, String designation2, String company, String location_work, String branch, String year_passing){
        this.uid = uid;
        this.name = name2;

        this.designation=designation2;
        this.company = company;
        this.location_work = location_work;
        this.branch = branch;
        this.year_passing = year_passing;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear_passing() {
        return year_passing;
    }

    public void setYear_passing(String year_passing) {
        this.year_passing = year_passing;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getLocation_work() {
        return location_work;
    }

    public void setLocation_work(String location_work) {
        this.location_work = location_work;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
