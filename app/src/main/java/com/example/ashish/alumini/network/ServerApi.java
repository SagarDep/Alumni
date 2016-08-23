package com.example.ashish.alumini.network;

import com.example.ashish.alumini.network.pojo.Job;
import com.example.ashish.alumini.network.pojo.JobDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ashish on 8/8/16.
 */
public interface ServerApi {

    /*
    * to get the list of job posted
    * */
    @GET("/jobs")
    Call<List<Job>> getJobList();

    @GET("/jobs/detail/{id}")
    Call<JobDetail> getJobDetails(@Path("id") String id);



//    @POST('jobs/post')
//    Callback
//    postJob();
}
