package com.example.ashish.alumini.network;

import com.example.ashish.alumini.network.pojo.Job;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

/**
 * Created by ashish on 8/8/16.
 */
public interface ServerApi {

    /*
    * to get the list of job posted
    * */
    @GET("/jobs")
    Call<List<Job>> GetJobList();
}
