package com.example.ashish.alumini.fragments.viewpager;

import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ashish.alumini.application.MyApplication;
import com.example.ashish.alumini.members.MemberAdapter;
import com.example.ashish.alumini.members.RecyclerItemClickListener;
import com.example.ashish.alumini.R;
import com.example.ashish.alumini.activities.PostLogin.PostLoginActivity;
import com.example.ashish.alumini.fragments.settings.FragmentProfile;
import com.example.ashish.alumini.network.pojo.MemberInstance;
import com.example.ashish.alumini.supporting_classes.MemberLists;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ashish on 14/3/16.
 */
public class FragmentViewPager0 extends android.support.v4.app.Fragment {

    private String TAG = getClass().getSimpleName();

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    // List and adapter
    private List<MemberInstance> mArrayList = new ArrayList<>();
    private MemberAdapter mAdapter;

    // activity
    PostLoginActivity mActivity;
    // application
    MyApplication mApplication;
    //event bus
    Bus mBus = new Bus();

    public FragmentViewPager0() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // getting instance of application
        mApplication = (MyApplication) getActivity().getApplication();

        //getting instance of activity
        mActivity = (PostLoginActivity) getActivity();


        MemberLists memberLists = mApplication.getmMemberListsInstance();
        mArrayList = memberLists.list;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        //butterknife injections
        ButterKnife.bind(this,view);



        // to reduce nullification
        if (mArrayList!=null){
            //initialization of adapter
            mAdapter = new MemberAdapter(mArrayList);
        }


        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        // onlick listener of recycler view
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), recyclerView ,
                        new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // creaing a new fragment
                        FragmentProfile fragmentProfile = new FragmentProfile();

                        // setting the data of clicked item
                        fragmentProfile.setData(mArrayList.get(position));

                        // change the fragment
                        mActivity.changeFragment(fragmentProfile);

                        // notify to the activity -> to handle back pressed events
                        mBus.post(R.id.recycler_view);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        Log.d(TAG, "Size " + mArrayList.size());

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

}
