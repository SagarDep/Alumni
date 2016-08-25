package com.example.ashish.alumini.fragments.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ashish.alumini.members.MemberAdapter;
import com.example.ashish.alumini.members.MemberListInstance;
import com.example.ashish.alumini.R;
import com.squareup.otto.Bus;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ashish on 14/3/16.
 */
public class FragmentViewPager3 extends Fragment {
    private String TAG = getClass().getSimpleName();

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    private ArrayList<MemberListInstance> varArrayList = new ArrayList<>();
    private MemberAdapter mAdapter;

    Bus mBus = new Bus();

    public FragmentViewPager3() {
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
        //butterknife injections
        ButterKnife.bind(this,view);

        //initialization of adapter
        mAdapter = new MemberAdapter(varArrayList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        prepareList();

        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<MemberListInstance> list_members = new ArrayList<>();


    }
    @Override
    public void onPause() {
        super.onPause();
        mBus.unregister(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        mBus.register(getActivity());

    }

    private void prepareList() {
        varArrayList.add(new MemberListInstance("o1", "Ashish", "Android Dev", "Parkzap", "Gurgaon", "CS", "2017"));
        varArrayList.add(new MemberListInstance("03","Lavish Aggarwal","Full Stack Developer","HackerEarth","Bangalore","CS","2012"));
        varArrayList.add(new MemberListInstance("04","Hari Om","Web Developer","zillion","Gurgaon","IT","2011"));
        varArrayList.add(new MemberListInstance("05","Ayush Sharma","Full Stack Developer","Innovaccer","NOIDA","CS","2013"));
        varArrayList.add(new MemberListInstance("06","Ashwin Devarajan","Data Scientist","Moksha Tech","Jaipur","CS","2013"));


        mAdapter.notifyDataSetChanged();
    }

}

