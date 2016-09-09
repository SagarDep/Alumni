package com.example.ashish.alumini.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.ashish.alumini.R;
import com.example.ashish.alumini.fragments.viewpager.FragmentViewPager0;
import com.example.ashish.alumini.fragments.viewpager.FragmentViewPager1;
import com.example.ashish.alumini.fragments.viewpager.FragmentViewPager2;
import com.example.ashish.alumini.network.ApiClient;
import com.example.ashish.alumini.network.pojo.MemberInstance;
import com.example.ashish.alumini.supporting_classes.ViewPagerAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentMembers extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String TAG = getClass().getSimpleName();

    @Bind(R.id.viewpager) ViewPager mViewPager;
    @Bind(R.id.tabLayout)TabLayout mTabLayout;


    // list of all members
    List<MemberInstance> memberInstanceList;


    public FragmentMembers() {
        // Required empty public constructor
        Log.d(TAG,"Fragment Member created");
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentJobs.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMembers newInstance(String param1, String param2) {
        FragmentMembers fragment = new FragmentMembers();
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
        View view = inflater.inflate(R.layout.fragment_member, container, false);

        ButterKnife.bind(this,view);


        setupViewPager(mViewPager);

        mTabLayout.setupWithViewPager(mViewPager);

//        makeServerCallToGetTheList();



        return view;
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new FragmentViewPager0(), "ALL");
        adapter.addFragment(new FragmentViewPager1(),"ACE");
        adapter.addFragment(new FragmentViewPager2(),  "NERDS");
//        adapter.addFragment(CommonData.fragmentViewPager0, "ALL");
//        adapter.addFragment(CommonData.fragmentViewPager1,"ACE");
//        adapter.addFragment(CommonData.fragmentViewPager2,  "NERDS");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public void makeServerCallToGetTheList(){
        Call<List<MemberInstance>> call = ApiClient.getServerApi().getMemberList();

        call.enqueue(new Callback<List<MemberInstance>>() {
            @Override
            public void onResponse(Call<List<MemberInstance>> call, Response<List<MemberInstance>> response) {
                Log.d(TAG,"API call successful");
            }

            @Override
            public void onFailure(Call<List<MemberInstance>> call, Throwable t) {
                Log.d(TAG,"API call failed");
            }
        });
    }
}
