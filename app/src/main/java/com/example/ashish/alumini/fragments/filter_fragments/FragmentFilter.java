package com.example.ashish.alumini.fragments.filter_fragments;



import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ashish.alumini.R;
import com.example.ashish.alumini.activities.post_login.PostLoginActivity;
import com.example.ashish.alumini.network.ApiClient;
import com.example.ashish.alumini.network.pojo.MemberInstance;
import com.example.ashish.alumini.supporting_classes.CommonData;
import com.sdsmdg.tastytoast.TastyToast;
import com.squareup.otto.Bus;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        mActivity.getSupportActionBar().setTitle("Filter");


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

        if (CommonData.listYear.size()==0 && CommonData.listBranch.size()==0){
            return;
        }

        // making progress bar visible
        mBus.post(true);



        Call<List<MemberInstance>> call = ApiClient.getServerApi().filterMembers(CommonData.listYear, CommonData.listBranch);

        call.enqueue(new Callback<List<MemberInstance>>() {
            @Override
            public void onResponse(Call<List<MemberInstance>> call, Response<List<MemberInstance>> response) {
                Log.d(TAG, "Api call successful");



                if (response.body()!=null && response.body().size()>0){

                    // copying the result to global static var
                    CommonData.mFilterResultList = response.body();

                    // change the fragment
                    mActivity.changeFragment(new FragmentFilterResult());

                    // notify to the activity -> to handle back pressed events
                    mBus.post(7777);

                }

                // making progress bar invisible
                mBus.post(false);
            }

            @Override
            public void onFailure(Call<List<MemberInstance>> call, Throwable t) {
                Log.d(TAG, "Api call failed");

                TastyToast.makeText(getActivity(), "Can't communicate to server", TastyToast.LENGTH_SHORT, TastyToast.ERROR);

                // making progress bar invisible
                mBus.post(false);
            }
        });


    }
}
