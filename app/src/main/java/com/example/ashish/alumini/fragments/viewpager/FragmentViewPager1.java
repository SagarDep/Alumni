package com.example.ashish.alumini.fragments.viewpager;

import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ashish.alumini.application.MyApplication;
import com.example.ashish.alumini.members.MemberListInstance;
import com.example.ashish.alumini.members.MemberAdapter;
import com.example.ashish.alumini.members.RecyclerItemClickListener;
import com.example.ashish.alumini.R;
import com.example.ashish.alumini.activities.PostLogin.PostLoginActivity;
import com.example.ashish.alumini.fragments.settings.FragmentProfile;
import com.example.ashish.alumini.supporting_classes.MemberLists;
import com.squareup.otto.Bus;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ashish on 14/3/16.
 */
public class FragmentViewPager1 extends android.support.v4.app.Fragment {

    private String TAG = getClass().getSimpleName();

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    private ArrayList<MemberListInstance> mArrayList = new ArrayList<>();
    private MemberAdapter mAdapter;

    PostLoginActivity mActivity;

    Bus mBus = new Bus();

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

        //butterknife injections
        ButterKnife.bind(this,view);

        //initialization of adapter
        mAdapter = new MemberAdapter(mArrayList);

        //getting instance of activity
        mActivity = (PostLoginActivity) getActivity();


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), recyclerView ,
                        new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        FragmentProfile fragmentProfile = new FragmentProfile().newInstance("","");
                        fragmentProfile.setData(mArrayList.get(position));
                        mActivity.changeFragment(fragmentProfile);
                        mBus.post(R.id.recycler_view);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


//        makeServerCallToGetMemberList();
        MyApplication myApplication = (MyApplication) getActivity().getApplication();
        MemberLists memberLists = myApplication.getmMemberListsInstance();
        Log.d(TAG,String.valueOf(memberLists.list.size()));
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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

    /*
        * Function to create the list
        * */
    private void makeServerCallToGetMemberList() {
        mArrayList.add(new MemberListInstance("o1","Ashish","Android Dev","Parkzap","Gurgaon","CS","2017"));
        mArrayList.add(new MemberListInstance("02","Priyank Jain","Devops","Yatra.com","NOIDA","CS","2013"));
        mArrayList.add(new MemberListInstance("03","Lavish Aggarwal","Full Stack Developer","HackerEarth","Bangalore","CS","2012"));
        mArrayList.add(new MemberListInstance("04","Hari Om","Web Developer","zillion","Gurgaon","IT","2011"));
        mArrayList.add(new MemberListInstance("05","Ayush Sharma","Full Stack Developer","Innovaccer","NOIDA","CS","2013"));
        mArrayList.add(new MemberListInstance("06","Ashwin Devarajan","Data Scientist","Moksha Tech","Jaipur","CS","2013"));
        mArrayList.add(new MemberListInstance("a","b","c","a","b","c","d"));
        mAdapter.notifyDataSetChanged();
    }

}
