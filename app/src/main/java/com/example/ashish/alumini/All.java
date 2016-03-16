package com.example.ashish.alumini;

import android.app.ListFragment;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.telephony.CellIdentityGsm;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

        ArrayList<ListVar> list_members = new ArrayList<>();
        for (int i = 0; i<5; i++){
            ListVar listVar = new ListVar(" Name " + i,BitmapFactory.decodeResource(getResources(),R.drawable.image));
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
