package com.example.ashish.alumini.fragments.common_fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ashish.alumini.R;
import com.example.ashish.alumini.activities.PostLogin.PostLoginActivity;
import com.example.ashish.alumini.network.ApiClient;
import com.example.ashish.alumini.supporting_classes.GlobalPrefs;
import com.github.lguipeng.library.animcheckbox.AnimCheckBox;
import com.squareup.otto.Bus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentGetProfileData extends android.support.v4.app.Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String TAG = getClass().getSimpleName();

    /*
    * Butterknife
    * */
    @Bind(R.id.button_save_profile_data)
    Button mButtonSave;

    //contacts
    @Bind(R.id.editText_member_email)
    EditText mEditTextEmail;
    @Bind(R.id.editText_memberPhone)
    EditText mEditTextPhone;
    @Bind(R.id.editText_memberWebLink)
    EditText mEditTextWebLink;

    //location
    @Bind(R.id.editText_locationHome)
            EditText mEditTextLocationHome;
    @Bind(R.id.editText_locationWork)
           EditText mEditTextLocationWork;

    //Spinners
    @Bind(R.id.spinner_branch)
    Spinner mSpinnerBranch;
    @Bind(R.id.spinner_year)
    Spinner mSpinnerYear;


    //checkBox
    @Bind(R.id.checkBox_isNerd)
    AnimCheckBox checkbox;

    Bus mBus = new Bus();



    public FragmentGetProfileData() {
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
    public static FragmentGetProfileData newInstance(String param1, String param2) {
        FragmentGetProfileData fragment = new FragmentGetProfileData();
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
        View view = inflater.inflate(R.layout.fragment_getprofiledata, container, false);

        ButterKnife.bind(this,view);
        //Bus Registering
        mBus.register(getActivity());


        checkbox.setChecked(false);
        boolean animation = true;
        checkbox.setChecked(false, animation);


        return view;
    }

    @OnClick(R.id.button_save_profile_data)
    public void saveDataHandler(){
        if (validate()){
            makeServerCalltoPostCompleteData();
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public void makeServerCalltoPostCompleteData(){
        // getting the id from shared preffernece which was stored during partial signup
        String id = GlobalPrefs.getString("Userid");


        Call<String> call = ApiClient.getServerApi().signupComplete(id,
                mEditTextPhone.getText().toString(),
                mEditTextWebLink.getText().toString(),
                mSpinnerBranch.getSelectedItem().toString(),
                mSpinnerYear.getSelectedItem().toString());

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(TAG, "API call successful");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG, "API call failed");
            }
        });

    }

    public boolean validate(){
        return true;
    }
}
