package com.example.ashish.alumini.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ashish on 8/8/16.
 */
public class ApiClient {

   public static final String BASE_URL = "http://"+ "139.59.13.112" + ":3000/";    // server

    private static Retrofit retrofit = null;

    // copied from some toutorials
    public static Retrofit getClientOld() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    // socket time out exception in image uploading
    //http://stackoverflow.com/questions/26750650/retrofit-sockettimeoutexception-in-sending-multiparty-or-json-data-in-android
    private static OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build();
        return client;
    }

    // modified function
    public static ServerApi getServerApi() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(ServerApi.class);
    }

}