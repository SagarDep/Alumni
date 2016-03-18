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


        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url ="http://192.168.150.3:5000/aryaalumni/api/v1.0/membersprofile?start=0&end=5";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("WTF RESPONSE",response);
                        try {
                            JSONObject json= (JSONObject) new JSONTokener(response).nextValue();
                            JSONArray jsonArray = json.getJSONArray("data");
                            Log.d("WTF",jsonArray.get(0).toString());
                        }
                        catch (Exception e){

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("WTF ERROR",error.toString());
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);


        ArrayList<ListVar> list_members = new ArrayList<>();
        for (int i = 0; i<5; i++){
            ListVar listVar = new ListVar(" Name " + i,BitmapFactory.decodeResource(getResources(),R.drawable.image),"CS","IBM","Mumbai","CS","2017");
            list_members.add(listVar);
        } //------TEMP

        MemberAdapter memberAdapter= new MemberAdapter(getActivity(),R.layout.simple_list_item,list_members);

        setListAdapter(memberAdapter);


    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(getContext(),profile.class);
        startActivity(intent);
    }
}
