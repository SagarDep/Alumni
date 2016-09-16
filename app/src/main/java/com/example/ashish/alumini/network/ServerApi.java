package com.example.ashish.alumini.network;

import com.example.ashish.alumini.activities.PreLogin.Login;

import com.example.ashish.alumini.network.pojo.Job;
import com.example.ashish.alumini.network.pojo.JobDetail;
import com.example.ashish.alumini.network.pojo.LoginResponse;
import com.example.ashish.alumini.network.pojo.MemberInstance;
import com.example.ashish.alumini.network.pojo.SignupPart;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

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


    /*
    * to post a job from settings menu
    * */
    @POST("jobs/post")
    Call<String> postJob(@Query("name") String name,
                         @Query("role") String role,
                         @Query("kahani") String description,
                         @Query("location") String location,
                         @Query("contactweb") String webLink,
                         @Query("contactemail") String email,
                         @Query("postedby") String postedBy,
                         @Query("postedbyid") String postdById
                         );

    /*
    * API call for partial signup
    * */
    @POST("members/signup/partial")
    Call<SignupPart> signupPartial(@Query("name") String name,
                                   @Query("email") String email,
                                   @Query("password") String password
    );


    /*
    * complete signup api
    * */
    @POST("members/signup/complete")
    Call<String> signupComplete(@Query("_id") String id,
                                @Query("name") String name,
                                @Query("bio") String bio,
                                @Query("branch") String branch,
                                @Query("year") String year,
                                @Query("isNerd") boolean isNerd,
                                @Query("designation") String designation,
                                @Query("company") String company,
                                @Query("home") String home,
                                @Query("work") String work,
                                @Query("phone") String phone,
                                @Query("weblink") String webLink,
                                @Query("fblink") String fbLink
                                );


    // getting the list of members
    @GET("members/")
    Call<List<MemberInstance>> getMemberList();


    // login
    @POST("members/login/")
    Call<LoginResponse> login(@Query("email") String email,
                              @Query("password") String password);

    // getting complete data
    @POST("members/profile")
    Call<MemberInstance> getCompleteProfileData(@Query("_id") String id);

    // on click recyvler view (to get the remaining data of member)
    @POST("members/remaining-data")
    Call<MemberInstance> getRemainingDataForRecyclerView(@Query("_id") String id);

    /*
    * Multipart file uploading
    * */
    @Multipart
    @POST("/routes/upload")
    Call<String> upload(@Part("photo") RequestBody phot);

}
