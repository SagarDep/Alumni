package com.example.ashish.alumini.fragments.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ashish.alumini.activities.pre_login.MainActivity;
import com.example.ashish.alumini.fragments.FragmentWebView;
import com.example.ashish.alumini.R;
import com.example.ashish.alumini.activities.post_login.PostLoginActivity;
import com.example.ashish.alumini.supporting_classes.GlobalPrefs;
import com.sdsmdg.tastytoast.TastyToast;
import com.squareup.otto.Bus;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;


public class FragmentSettings extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /*
    * Butterknife
    * */


    Bus mBus = new Bus();
    PostLoginActivity mActivity;


    public FragmentSettings() {
        // Required empty public constructor
    }

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


        mActivity = (PostLoginActivity) getActivity();

        mActivity.getSupportActionBar().setTitle("Settings");


        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onResume() {
        super.onResume();
        //Bus Registering
        mBus.register(getActivity());

        // hiding progress bar if in case remained visible
        Boolean aBoolean = false;
        mBus.post(aBoolean);


    }

    @Override
    public void onPause() {
        super.onPause();

        //Bus Registering
        mBus.unregister(getActivity());


    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    /*
    * event fired to Fragment Activity PostLogin to move back to setting
      fragment on back pressed
    * */
    @OnClick(R.id.button_postjob)
    public void handlerJobPostButton(){

        mBus.post(9999);

        // then change the fragment
        mActivity.changeFragment(new FragmentJobPosting());

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

        //
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

        MaterialDialog materialDialog = new MaterialDialog(getActivity());
        materialDialog.setTitle("Share App Link")
                .setMessage("Please help us to reach you more awesome people")
                .setPositiveButton("Share", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent waIntent = new Intent(Intent.ACTION_SEND);
                        waIntent.setType("text/plain");
                        String text = "Get the alumni app through this link and be a part of awesome community";

                        waIntent.putExtra(Intent.EXTRA_TEXT, text);
                        startActivity(Intent.createChooser(waIntent, "Share with"));

                    }
                })
                .show();

        mBus.post(9999);
    }


    @OnClick(R.id.button_contact_us)
    public void handlerContactUsButton(){
        mBus.post(9999);

        MaterialDialog materialDialog = new MaterialDialog(mActivity);
        materialDialog
                .setTitle("Contact")
                .setMessage("Feel free to ping for any query" + '\n' + "ashishsurana12345@gmail.com")
                .setCanceledOnTouchOutside(true)
                .show();
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

        // update value in shared preference
        new GlobalPrefs(getContext()).putBooloean(getString(R.string.is_logged_in),false);


        TastyToast.makeText(getActivity(),"You are successfully logged Out", Toast.LENGTH_SHORT,TastyToast.DEFAULT);

        mActivity.finish();

        // back to login / signup activity
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);

    }

}
