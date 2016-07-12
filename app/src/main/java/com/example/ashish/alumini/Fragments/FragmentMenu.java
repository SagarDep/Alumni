package com.example.ashish.alumini.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.ashish.alumini.activities.PostLogin.ActivityMember;
import com.example.ashish.alumini.activities.PostLogin.MainScreen;
import com.example.ashish.alumini.R;
import com.mikepenz.iconics.view.IconicsImageView;
import com.squareup.otto.Bus;

import butterknife.Bind;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentMenu.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentMenu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMenu extends Fragment {

    /*
    * GLOBAL DECLARATIONS & BUTTERKNIFE INJECTIONS
    *
    * */
    @Bind(R.id.linearLayout_home) LinearLayout mLinearLayoutHome;
    @Bind(R.id.linearLayout_filter) LinearLayout mLinearLayoutFilter;
    @Bind(R.id.linearLayout_jobs) LinearLayout mLinearLayoutJobs;
    @Bind(R.id.linearLayout_settings) LinearLayout mLinearLayoutSettings;

    View mViewPrevious;
    LinearLayout mLinearLayoutPrevious;
    View mViewCurrent;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Bus mBus = new Bus();
    private OnFragmentInteractionListener mListener;

    ActivityMember mActivity;





    public FragmentMenu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMenu.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMenu newInstance(String param1, String param2) {
        FragmentMenu fragment = new FragmentMenu();
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
        View view = inflater.inflate(R.layout.fragment_menu_bottom, container, false);
        //Butterknife Binding
        ButterKnife.bind(this,view);
        //event bus registering
        mBus.register(getActivity());

        mActivity = (ActivityMember) getActivity();

        mViewPrevious = view.findViewById(R.id.view_home);
        mLinearLayoutPrevious = (LinearLayout) view.findViewById(R.id.linearLayout_home);
        mLinearLayoutHome.setBackgroundColor(getResources().getColor(R.color.grey));

        return view;
    }

    @OnClick(R.id.linearLayout_home)
    public void changeToHomeFragment(){
        setVisibleView(getView().findViewById(R.id.view_home),mLinearLayoutHome);
    }
    @OnClick(R.id.linearLayout_filter)
    public void changeToFilterFragment(){
        setVisibleView(getView().findViewById(R.id.view_filter),mLinearLayoutFilter);
        mActivity.changeFragment(new com.example.ashish.alumini.Fragments.Fragment().newInstance(null,null));
    }
    @OnClick(R.id.linearLayout_jobs)
    public void changeToJobsFragment(){
        setVisibleView(getView().findViewById(R.id.view_jobs),mLinearLayoutJobs);
        mActivity.changeFragment(mActivity.mFragmentJob);
    }
    @OnClick(R.id.linearLayout_settings)
    public void changeFragment(){
        setVisibleView(getView().findViewById(R.id.view_settings),mLinearLayoutSettings);
        mActivity.changeFragment(new FragmentSettings().newInstance(null,null));
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void setVisibleView(View viewUnderLine,LinearLayout layout){
        //setting the previously clicked view to visibility=gone
        mViewPrevious.setVisibility(View.GONE);
        //setting the previously clicked Button to default
        mLinearLayoutPrevious.setBackgroundColor(getResources().getColor(R.color.card_background));

        //changing color and visibility
        layout.setBackgroundColor(getResources().getColor(R.color.grey));
        viewUnderLine.setVisibility(View.VISIBLE);

        //updating the previous elements for changing the visibility in next click
        mViewPrevious=viewUnderLine;
        mLinearLayoutPrevious = layout;

        mBus.post(layout.getId()); // Posting the clicked layout to the Fragment activity (ActivityMember)
    }
}
