package com.example.ashish.alumini.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ashish.alumini.Job.JobListInstance;
import com.example.ashish.alumini.R;
import com.example.ashish.alumini.activities.PostLogin.ActivityMember;
import com.squareup.otto.Bus;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentJobDetails.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentJobDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentJobDetails extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private JobListInstance mJobListInstance;

    /*
    * Butterknife
    * */
    @Bind(R.id.imageView_companyImage) ImageView imageView_companyImage;
    @Bind(R.id.textView_companyName_jobDetails) TextView mTextViewName;
    @Bind(R.id.textView_jobType_jobDetails) TextView mTextViewJobType;
    @Bind(R.id.textView_location_jobDetails) TextView mTextViewLocation;
    @Bind(R.id.textView_designation_jobDetails) TextView mTextViewJobDesignation;
    @Bind(R.id.textView_jobDescription) TextView mTextViewJobDescription;
    @Bind(R.id.textView_email_jobDetails) TextView mTextViewemail;
    @Bind(R.id.textView_website) TextView mTextViewWebsite;

    Bus mBus = new Bus();

    ActivityMember mActivity = (ActivityMember) getActivity();

    public FragmentJobDetails() {
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
    public static FragmentJobDetails newInstance(Object param1, String param2) {
        FragmentJobDetails fragment = new FragmentJobDetails();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1.toString());
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
        View view = inflater.inflate(R.layout.fragment_job_details, container, false);

        ButterKnife.bind(this,view);
        //Bus Registering
        mBus.register(getActivity());

        showData();
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

    public void setData(JobListInstance item){
        mJobListInstance = item;
    }

    public void showData(){
        mTextViewName.setText(mJobListInstance.getCompanyName());
        mTextViewLocation.setText(mJobListInstance.getJobLocation());
        mTextViewJobType.setText(mJobListInstance.getJobType());
        mTextViewJobDesignation.setText(mJobListInstance.getJobPost());
        mTextViewWebsite.setText("www.temp.com");
        mTextViewemail.setText("www@temp.com");
        mTextViewJobDescription.setText("Job Description - kahani");
    }
}
