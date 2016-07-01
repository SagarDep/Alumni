package com.example.ashish.alumini;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ashish.alumini.ListMembers.ListVar;
import com.example.ashish.alumini.ListMembers.MemberAdapter;

import java.util.ArrayList;

/**
 * Created by ashish on 14/3/16.
 */
public class Proffesional extends ListFragment{

    public Proffesional() {
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

//            list_members.add(listVar);

//        MemberAdapter memberAdapter= new MemberAdapter(getActivity(),R.layout.lis,list_members);

//        setListAdapter(memberAdapter);


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(getContext(),profile.class);
        startActivity(intent);
    }
}

