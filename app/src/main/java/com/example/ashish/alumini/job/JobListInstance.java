package com.example.ashish.alumini.job;

import android.graphics.Bitmap;

/**
 * Created by ashish on 11/3/16.
 */
public class JobListInstance {
    Bitmap logo;
    String companyName;
    String jobLocation;
    String jobPost;
    String numberOfPersons;
    String lastDate;
    String jobType;

    public JobListInstance(Bitmap logo,
                           String companyName,
                           String jobLocation,
                           String jobPost,
                           String numberOfPersons,
                           String lastDate,
                           String jobType) {
        this.logo = logo;
        this.companyName = companyName;
        this.jobLocation = jobLocation;
        this.jobPost = jobPost;
        this.numberOfPersons = numberOfPersons;
        this.lastDate = lastDate;
        this.jobType = jobType;
    }

    public Bitmap getLogo() {
        return logo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public String getJobPost() {
        return jobPost;
    }

    public String getNumberOfPersons() {
        return numberOfPersons;
    }

    public String getLastDate() {
        return lastDate;
    }

    public String getJobType() {
        return jobType;
    }
}
