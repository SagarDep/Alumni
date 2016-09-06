package com.example.ashish.alumini.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ashish on 8/8/16.
 */
public class ApiClient {

//    public static final String BASE_URL = "http://192.168.43.0:3000/";
    public static final String BASE_URL = "http://192.168.43.115:3000/"; // mobile hotspot
//    public static final String BASE_URL = "http://192.168.42.52:3000/"; // usb tethring
//    public static final String BASE_URL = "http://192.168.137.152:3000/";
//    public static final String BASE_URL = "http://172.16.4.107:3000/"; // bangon
    private static Retrofit retrofit = null;


    // copied from some toutorials
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    // modified function
    public static ServerApi getServerApi() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(ServerApi.class);
    }


}