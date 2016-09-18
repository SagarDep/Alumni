package com.example.ashish.alumini.supporting_classes;

import android.util.Log;

import com.example.ashish.alumini.network.ApiClient;
import com.example.ashish.alumini.network.pojo.MemberInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashish on 3/9/16.
 */
public class MemberLists {

    String TAG = getClass().getSimpleName();

    public List<MemberInstance> list;

    public MemberLists() {
    makeServerCallToGetAllMemberData();
//        makeServerCallToGetAllMemberDataPost();
    }
    public void makeServerCallToGetAllMemberData(){
        Log.d(TAG, "Class/API created");
        Call<List<MemberInstance>> call = ApiClient.getServerApi().getMemberList();

        call.enqueue(new Callback<List<MemberInstance>>() {
            @Override
            public void onResponse(Call<List<MemberInstance>> call, Response<List<MemberInstance>> response) {
                Log.d(TAG,"API call successful");
                list = response.body();
            }

            @Override
            public void onFailure(Call<List<MemberInstance>> call, Throwable t) {
                Log.d(TAG,"API call failed");
            }
        });
    }

    public void makeServerCallToGetAllMemberDataPost(){
        Log.d(TAG, "Class/API created");
        Call<List<MemberInstance>> call = ApiClient.getServerApi().getMemberList();

        call.enqueue(new Callback<List<MemberInstance>>() {
            @Override
            public void onResponse(Call<List<MemberInstance>> call, Response<List<MemberInstance>> response) {
                Log.d(TAG,"API call successful");
                list = response.body();
            }

            @Override
            public void onFailure(Call<List<MemberInstance>> call, Throwable t) {
                Log.d(TAG,"API call failed");
            }
        });
    }

}
