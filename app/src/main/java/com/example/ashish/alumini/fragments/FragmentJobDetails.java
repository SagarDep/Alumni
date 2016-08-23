package com.example.ashish.alumini.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ashish.alumini.Job.JobListInstance;
import com.example.ashish.alumini.R;
import com.example.ashish.alumini.activities.PostLogin.PostLoginActivity;
import com.example.ashish.alumini.network.ApiClient;
import com.example.ashish.alumini.network.pojo.Job;
import com.example.ashish.alumini.network.pojo.JobDetail;
import com.squareup.otto.Bus;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentJobDetails extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private Job mJobListInstance;

    /*
    * Butterknife
    * */
    @Bind(R.id.imageView_companyImage) ImageView imageView_companyImage;
    @Bind(R.id.textView_companyName_jobDetails) TextView mTextViewName;
    @Bind(R.id.textView_location_jobDetails) TextView mTextViewLocation;
    @Bind(R.id.textView_designation_jobDetails) TextView mTextViewJobDesignation;
    @Bind(R.id.textView_jobDescription) TextView mTextViewJobDescription;
    @Bind(R.id.textView_email_jobDetails) TextView mTextViewemail;
    @Bind(R.id.textView_website) TextView mTextViewWebsite;
    @Bind(R.id.textView_postedby) TextView mTextViewPostedBy;

    Bus mBus = new Bus();

    PostLoginActivity mActivity = (PostLoginActivity) getActivity();

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
        makeServerCallToGetRemainingData();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }



    /*
    * For setting the data passed from the list
    * */
    public void setData(Job item){
        mJobListInstance = item;
    }


    public void showData(){
        /*
        * setting the text to the text fields
        * data was recieved by the list directly
        * */
        mTextViewName.setText(mJobListInstance.getName());
        mTextViewLocation.setText(mJobListInstance.getLocation());
        mTextViewJobDesignation.setText(mJobListInstance.getRole());
    }
    public void makeServerCallToGetRemainingData(){

        Call<JobDetail> call = ApiClient.getServerApi().getJobDetails(mJobListInstance.get_id());

        call.enqueue(new Callback<JobDetail>() {
            @Override
            public void onResponse(Call<JobDetail> call, Response<JobDetail> response) {
                Log.d("API cal","Successful");
                JobDetail jobDetail = response.body();
                mTextViewWebsite.setText(jobDetail.getContactweb());
                mTextViewemail.setText(jobDetail.getContactemail());
                mTextViewJobDescription.setText("Job Description - " + jobDetail.getKahani());
                mTextViewPostedBy.setText("Posted By : " + jobDetail.getPostedby());
            }

            @Override
            public void onFailure(Call<JobDetail> call, Throwable t) {
                Log.d("API cal","Failed");
            }
        });


    }
}
