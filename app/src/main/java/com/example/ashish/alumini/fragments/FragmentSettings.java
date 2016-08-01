package com.example.ashish.alumini.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ashish.alumini.fragments.common_fragments.FragmentWebView;
import com.example.ashish.alumini.fragments.settings.FragmentAboutApp;
import com.example.ashish.alumini.fragments.settings.FragmentFaq;
import com.example.ashish.alumini.fragments.settings.FragmentJobPosting;
import com.example.ashish.alumini.fragments.settings.FragmentProfile;
import com.example.ashish.alumini.R;
import com.example.ashish.alumini.activities.PostLogin.ActivityMember;
import com.squareup.otto.Bus;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentSettings.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentSettings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSettings extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /*
    * Butterknife
    * */
//    @Bind(R.id.button_postjob)
//    Button mButtonJobPost;
//    @Bind(R.id.button_myprofile)
//    Button mButtonProfile;
//    @Bind(R.id.button_fbPage)
//    Button mButtonFbPage;
//    @Bind(R.id.button_about_app)
//    Button mButtonAboutApp;
//    @Bind(R.id.button_about_college)
//    Button mButtonAboutCollege;
//    @Bind(R.id.button_support)
//    Button mButtonSupport;
//    @Bind(R.id.button_contact_us)
//    Button mButtonContactUs;
//    @Bind(R.id.button_faq)
//    Button mButtonFaqs;
//    @Bind(R.id.button_rate_us)
//    Button mButtonRateUs;
//    @Bind(R.id.button_logout)
//    Button mButtonLogOut;

    Bus mBus = new Bus();
    ActivityMember mActivity;

    SharedPreferences mSharedPreferences;

    public FragmentSettings() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSettings newInstance(String param1, String param2) {
        FragmentSettings fragment = new FragmentSettings();
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
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        ButterKnife.bind(this,view);
        //Bus Registering
        mBus.register(getActivity());

        mActivity = (ActivityMember) getActivity();

        mSharedPreferences = getActivity().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        return view;
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


    @OnClick(R.id.button_postjob)
    public void handlerJobPostButton(){
        mBus.post(9999);
        mActivity.changeFragment(new FragmentJobPosting().newInstance("",""));

    }
    @OnClick(R.id.button_myprofile)
    public void handlerMyProfileButton(){
        mBus.post(9999);
        mActivity.changeFragment(new FragmentProfile());
    }
    @OnClick(R.id.button_fbPage)
    public void handlerFbButton(){
        mBus.post(9999);
        mActivity.changeFragment(new FragmentWebView().newInstance(getString(R.string.url_facebook_page),""));
    }
    @OnClick(R.id.button_about_app)
    public void handlerAboutAppButton(){
//        mBus.post(R.id.button_postjob);
        mBus.post(9999);
        mActivity.changeFragment(new FragmentAboutApp());
    }
    @OnClick(R.id.button_about_college)
    public void handlerAboutCollegeButton(){
//        mBus.post(R.id.button_postjob);
        mBus.post(9999);
        mActivity.changeFragment(new FragmentWebView().newInstance(getString(R.string.url_college),""));
    }
    @OnClick(R.id.button_support)
    public void handlerSupportButton(){
        mBus.post(9999);
    }
    @OnClick(R.id.button_contact_us)
    public void handlerContactUsButton(){
        mBus.post(9999);
    }
    @OnClick(R.id.button_faq)
    public void handlerFaqButton(){
        mBus.post(9999);
        mActivity.changeFragment(new FragmentFaq());
    }

    @OnClick(R.id.button_rate_us)
    public void handlerRatingButton(){
        mBus.post(R.id.button_faq);
    }
    @OnClick(R.id.button_logout)
    public void handlerLogoutButton(){
      SharedPreferences.Editor editor = mSharedPreferences.edit();
      editor.putBoolean(getString(R.string.login_key),false);
      editor.commit();
    }

}
