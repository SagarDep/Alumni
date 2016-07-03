package com.example.ashish.alumini.Fragments.viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ashish.alumini.ListMembers.ListVar;
import com.example.ashish.alumini.ListMembers.MemberAdapter;
import com.example.ashish.alumini.R;
import com.example.ashish.alumini.profile;

import java.util.ArrayList;

/**
 * Created by ashish on 14/3/16.
 */
public class PG extends ListFragment{

    public PG() {
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
        return inflater.inflate(R.layout.fragment_two, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<ListVar> list_members = new ArrayList<>();
//        for (int i = 0; i<5; i++){
//            ListVar listVar = new ListVar(" Name " + i,BitmapFactory.decodeResource(getResources(),R.drawable.image),"CS","IBM","Mumbai","CS","2017");
//            list_members.add(listVar);
//        } //------TEMP

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

