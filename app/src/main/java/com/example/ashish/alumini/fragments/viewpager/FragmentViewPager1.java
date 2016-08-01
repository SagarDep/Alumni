package com.example.ashish.alumini.fragments.viewpager;

import android.content.Intent;
import android.os.Bundle;

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
public class FragmentViewPager1 extends android.support.v4.app.ListFragment {

    private String TAG = getClass().getSimpleName();

    private ArrayList<ListVar> mArrayList;
    private MemberAdapter mMemberAdapter;
    public FragmentViewPager1() {
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

//        mArrayList.add(new ListVar("01","ashish",null,"a","a","a","a","a"));

//        MemberAdapter memberAdapter= new MemberAdapter(getActivity(),R.layout.simple_list_item,mArrayList);
//        setListAdapter(memberAdapter);


    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(getContext(),profile.class);
        startActivity(intent);
    }
}
