package com.example.ashish.alumini.fragments.settings;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.ashish.alumini.R;
import com.example.ashish.alumini.network.ApiClient;
import com.sdsmdg.tastytoast.TastyToast;
import com.squareup.otto.Bus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentJobPosting extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String TAG = getClass().getSimpleName();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /*
    * Butterknife
    * */
    @Bind(R.id.editText_companyName)
    TextInputEditText mInputEditTextCompanyName;

    @Bind(R.id.editText_designation)
    TextInputEditText mInputEditTextRole;

    @Bind(R.id.editText_jobDescription)
    TextInputEditText mInputEditTextJobDescription;

    @Bind(R.id.editText_jobLocation)
    TextInputEditText mInputEditTextJobLocation;

    @Bind(R.id.editText_companyWebLink)
    EditText mEditTextWebLink;

    @Bind(R.id.editText_company_email)
    EditText mEditTextemail;

    Bus mBus = new Bus();

    public FragmentJobPosting() {
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
    public static FragmentJobPosting newInstance(String param1, String param2) {
        FragmentJobPosting fragment = new FragmentJobPosting();
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
        View view = inflater.inflate(R.layout.fragment_post_job, container, false);

        ButterKnife.bind(this,view);
        //Bus Registering
        mBus.register(getActivity());


        return view;
    }

    @OnClick(R.id.button_post_job)
    public void buttonPostJobHandler(){
        collectData();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public void collectData(){


        makeServerCallToPostData();
    }

    public void makeServerCallToPostData(){
        Call<String> call = ApiClient.getServerApi().postJob(
                mInputEditTextCompanyName.getText().toString(),
                mInputEditTextRole.getText().toString(),
                mInputEditTextJobDescription.getText().toString(),
                mInputEditTextJobLocation.getText().toString(),
                mEditTextWebLink.getText().toString(),
                mEditTextemail.getText().toString(),
                "Ayush Sharma",
                "skajfdfbvkoljsdhbfv"
        );

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(TAG,"Successful");
                TastyToast.makeText(getActivity(),"Posted! yeah",TastyToast.LENGTH_SHORT,TastyToast.SUCCESS);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("API Call ","Failed");
            }
        });

    }

}
