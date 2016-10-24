package com.example.ashish.alumini.fragments;



import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.ashish.alumini.members.expandable_list.ExpandableListAdapter;
import com.example.ashish.alumini.R;
import com.example.ashish.alumini.activities.post_login.PostLoginActivity;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 * to handle interaction events.
 * Use the {@link FragmentFilter#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentFilter extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String TAG = getClass().getSimpleName();



    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    Bus mBus = new Bus();

    PostLoginActivity mActivity ;

    public FragmentFilter() {
        // Required empty public constructor
    }


    public static FragmentFilter newInstance(String param1, String param2) {
        FragmentFilter fragment = new FragmentFilter();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_filter, container, false);

        //getting activity instance
        mActivity = (PostLoginActivity) getActivity();
        // changing title of action bar
        mActivity.getSupportActionBar().setTitle("Filter");

        // butterknife injections
        ButterKnife.bind(this,view);


        // getting fragment manager for transactions in onclick
        mFragmentManager = getChildFragmentManager();




        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }
    @OnClick(R.id.button_year)
    public void yearFragmentListener() {

        mFragmentTransaction = mFragmentManager.beginTransaction();

        // getting fragment YEAR
        Fragment blankFragment1 = mFragmentManager.findFragmentById(R.id.fragment_container_year);

        if (blankFragment1.getView().getVisibility()==View.VISIBLE){

            // HIDE
            mFragmentTransaction
                    .hide(blankFragment1)
                    .commit();
        } else if (blankFragment1.getView().getVisibility()==View.GONE){

            // SHOW
            mFragmentTransaction
                    .show(blankFragment1)
                    .commit();
        }

    }

    @OnClick(R.id.button_branch)
    public void branchFragmentListener() {

        mFragmentTransaction = mFragmentManager.beginTransaction();

        // getting fragment BRANCH
        Fragment blankFragment1 = mFragmentManager.findFragmentById(R.id.fragment_container_branch);

        if (blankFragment1.getView().getVisibility()==View.VISIBLE){
            // HIDE
            mFragmentTransaction
                    .hide(blankFragment1)
                    .commit();
        } else if (blankFragment1.getView().getVisibility()==View.GONE){

            // SHOW
            mFragmentTransaction
                    .show(blankFragment1)
                    .commit();
        }

    }

    @OnClick(R.id.button_filter)
    public void buttonFilterHandler(){

        makeServerCallToFilterData();

    }

    @Override
    public void onResume() {
        super.onResume();
        mBus.register(getActivity());

        // hiding both the fragments
        branchFragmentListener();

        yearFragmentListener();
    }

    @Override
    public void onPause() {
        super.onPause();
        mBus.unregister(getActivity());

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void makeServerCallToFilterData(){



    }
}
