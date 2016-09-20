package com.example.ashish.alumini.supporting_classes;

import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.example.ashish.alumini.network.ApiClient;
import com.example.ashish.alumini.network.models.MemberInstanceModel;
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

    boolean mApiCallFlag = false;

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

        // changing the api call flag
        mApiCallFlag = true;

        Call<MemberListResponse> call = ApiClient.getServerApi().getMemberListinChunks(time);

        call.enqueue(new Callback<MemberListResponse>() {
            @Override
            public void onResponse(Call<MemberListResponse> call, Response<MemberListResponse> response) {
                Log.d(TAG,"API call successful");
                MemberListResponse response1 = response.body();

                // preventing nullification
                if (response1!=null){
                    List<MemberInstance> instanceList = response1.getList();

                    // saving the result in active android
                    ActiveAndroid.beginTransaction();

                    try {
                        // iterating all the list instances and adding to main list
                        for (MemberInstance memberInstance: instanceList) {
                            list.add(memberInstance);
                            MemberInstanceModel memberInstanceModel = new MemberInstanceModel();
                            memberInstanceModel.set_id(memberInstance.get_id());
                            memberInstanceModel.setName(memberInstance.getName());
                            memberInstanceModel.setYear(memberInstance.getYear());
                            memberInstanceModel.setIsNerd(memberInstance.getIsNerd());
                            memberInstanceModel.setWork(memberInstance.getWork());
                            memberInstanceModel.setDesignation(memberInstance.getDesignation());
                            memberInstanceModel.save();

                        }
                        ActiveAndroid.setTransactionSuccessful();
                    }
                    finally {
                        ActiveAndroid.endTransaction();

                    }

                    // iterating all the list instances and adding to main list
//                    for (MemberInstance memberInstance: instanceList) {
//                        list.add(memberInstance);
//                    }

                }

                // checking if this is the last result or not
                // 99 because it is set by server
                if (response1.getTime().contentEquals("99")){
                    // this means no more API calls are required

                    mApiCallFlag = false;
                    return;
                }

                // start call again
                makeServerCallToGetAllMemberDataPost(response1.getTime());
            }

            @Override
            public void onFailure(Call<MemberListResponse> call, Throwable t) {


                // API call failed and populate the list from local db
//                new Delete().from(MemberInstanceModel.class).executeSingle();

                      List<MemberInstanceModel> lst  = new Select()
                        .from(MemberInstanceModel.class)
                        .execute();
                for (MemberInstanceModel model: lst ) {
                    MemberInstance memberInstance = new MemberInstance();
                    memberInstance.set_id(model.get_id());
                    memberInstance.setName(model.getName());
                    memberInstance.setYear(model.getYear());
                    memberInstance.setWork(model.getWork());
                    memberInstance.setIsNerd(model.getIsNerd());
                    memberInstance.setDesignation(model.getDesignation());
                    list.add(memberInstance);
                }

                Log.d(TAG,"API call failed" + t.toString());
            }
        });
    }

}
