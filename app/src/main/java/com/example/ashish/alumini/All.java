package com.example.ashish.alumini;

import android.app.ListFragment;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.telephony.CellIdentityGsm;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;

/**
 * Created by ashish on 14/3/16.
 */
public class All extends android.support.v4.app.ListFragment {
    JSONArray jsonArray1 = new JSONArray();
    private String TAG = "All";

    private ArrayList<ListVar> list_members;
    private MemberAdapter memberAdapter;
    public All() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        final ListVar listVar = new ListVar();
        final ArrayList<ListVar> list_members = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url ="http://182.73.140.106/aryaalumni/api/v1.0/membersprofile?start=0&end=2";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG + "Response",response);
                        try {
                            JSONObject json= (JSONObject) new JSONTokener(response).nextValue();
                            JSONArray jsonArray = json.getJSONArray("data");

                            for (int i=0; i<response.length(); i++){
                                ListVar listVar = new ListVar();
                                final JSONArray jsonArray1 = (JSONArray) jsonArray.get(i);
                                Log.d(TAG + "Array", jsonArray.get(i).toString());
                                listVar.uid = jsonArray1.getString(0);
                                Log.d("WTF",listVar.uid);
                                listVar.name = jsonArray1.getString(1).toString();
                                Log.d("WTF",listVar.name);
                                listVar.year_passing = jsonArray1.getString(2);
                                Log.d("WTF",listVar.year_passing);
                                listVar.designation = jsonArray1.getString(3);
                                Log.d("WTF",listVar.designation);
                                listVar.location_work = jsonArray1.getString(4);
                                Log.d("WTF",listVar.location_work);
                                listVar.branch = jsonArray1.getString(5);
                                Log.d("WTF",listVar.branch);
                                listVar.company = jsonArray1.getString(6);
                                list_members.add(listVar);
                                Log.d(TAG+"SIZE",list_members.size()+"");
                            }
                            MemberAdapter memberAdapter= new MemberAdapter(getActivity(),R.layout.simple_list_item,list_members);
                            setListAdapter(memberAdapter);
                        }
                        catch (Exception e){
                            Log.d(TAG,"Exception Caught" + e.toString());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG+" ERROR",error.getMessage()+ error.toString());
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(getContext(),profile.class);
        startActivity(intent);
    }
}
