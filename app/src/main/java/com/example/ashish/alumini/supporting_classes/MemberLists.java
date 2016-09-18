package com.example.ashish.alumini.supporting_classes;

import android.util.Log;

import com.example.ashish.alumini.network.ApiClient;
import com.example.ashish.alumini.network.pojo.MemberInstance;
import com.example.ashish.alumini.network.pojo.MemberListResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashish on 3/9/16.
 */
public class MemberLists {

    String TAG = getClass().getSimpleName();

    public List<MemberInstance> list = new ArrayList<>();

    public MemberLists() {
//    makeServerCallToGetAllMemberData();
        makeServerCallToGetAllMemberDataPost("0");
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

    public void makeServerCallToGetAllMemberDataPost(String time){
        Log.d(TAG, "Class/API created");
        Call<MemberListResponse> call = ApiClient.getServerApi().getMemberListinChunks(time);

        call.enqueue(new Callback<MemberListResponse>() {
            @Override
            public void onResponse(Call<MemberListResponse> call, Response<MemberListResponse> response) {
                Log.d(TAG,"API call successful");
                MemberListResponse response1 = response.body();
                if (response1!=null){
                    List<MemberInstance> instanceList = response1.getList();
                    for (MemberInstance memberInstance: instanceList) {
                        list.add(memberInstance);
                    }
                }

                if (response1.getTime().contentEquals("99")){
                    return;
                }

                makeServerCallToGetAllMemberDataPost(response1.getTime());
            }

            @Override
            public void onFailure(Call<MemberListResponse> call, Throwable t) {
                Log.d(TAG,"API call failed");
            }
        });
    }

}
