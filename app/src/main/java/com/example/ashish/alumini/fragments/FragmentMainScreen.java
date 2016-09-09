package com.example.ashish.alumini.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ashish.alumini.R;
import com.example.ashish.alumini.activities.PostLogin.MainScreenActivity;
import com.example.ashish.alumini.activities.PostLogin.PostLoginActivity;
import com.example.ashish.alumini.fragments.common_fragments.FragmentWebView;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.squareup.otto.Bus;

import at.markushi.ui.CircleButton;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FragmentMainScreen extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private String TAG = getClass().getSimpleName();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /*
    * Butterknife
    * */
    @Bind(R.id.circleButton_about)
    CircleButton mCirecularButtonAbout;

    @Bind(R.id.circleButton_event)
    CircleButton mCirecularButtonEvents;

    @Bind(R.id.circleButton_member)
    CircleButton mCirecularButtonMembers;

    Bus mBus = new Bus();

    MainScreenActivity mActivity ;

    public FragmentMainScreen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMainScreen newInstance(String param1, String param2) {
        FragmentMainScreen fragment = new FragmentMainScreen();
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
        View view = inflater.inflate(R.layout.fragment_main_screen, container, false);

        mActivity = (MainScreenActivity) getActivity();

        ButterKnife.bind(this,view);
        //Bus Registering
        mBus.register(getActivity());

        // setting icons to the circular buttons
        mCirecularButtonMembers.setImageDrawable(new IconicsDrawable(getActivity())
                .icon(FontAwesome.Icon.faw_users)
                .color(Color.WHITE)
                .sizeDp(25));

        mCirecularButtonAbout.setImageDrawable(new IconicsDrawable(getActivity())
                .icon(FontAwesome.Icon.faw_info)
                .color(Color.WHITE)
                .sizeDp(25));

        mCirecularButtonEvents.setImageDrawable(new IconicsDrawable(getActivity())
                .icon(FontAwesome.Icon.faw_calendar)
                .color(Color.WHITE)
                .sizeDp(25));


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onResume() {
        super.onResume();
        mBus.unregister(getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnClick(R.id.circleButton_member)
    public void startMemberActivity(){
        Intent moveToMember=new Intent(getActivity(),PostLoginActivity.class);
        startActivity(moveToMember);
    }

    @OnClick(R.id.circleButton_event)
    public void changeEventFragment(){
        mActivity.changeFragment(new FragmentEvents());
    }

    @OnClick(R.id.circleButton_about)
    public void showWebView(){
        mActivity.changeFragment(new FragmentWebView().newInstance("http://aryacollege.in",""));
    }

}
