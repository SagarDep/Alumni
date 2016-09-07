package com.example.ashish.alumini.fragments.common_fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ashish.alumini.R;
import com.example.ashish.alumini.activities.PostLogin.MainScreenActivity;
import com.example.ashish.alumini.activities.PostLogin.PostLoginActivity;
import com.example.ashish.alumini.fragments.FragmentMainScreen;
import com.example.ashish.alumini.network.ApiClient;
import com.example.ashish.alumini.supporting_classes.GlobalPrefs;
import com.github.lguipeng.library.animcheckbox.AnimCheckBox;
import com.sdsmdg.tastytoast.TastyToast;
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

    // name and bio
    @Bind(R.id.editText_memberName)
            EditText mEditTextName;
    @Bind(R.id.editText_memberBio)
    EditText mEditTextBio;

    // designation and company
    @Bind(R.id.editText_Designation)
    EditText mEditTextDesignation;
    @Bind(R.id.editText_company)
    EditText mEditTextCompany;


    // event bus registering
    Bus mBus = new Bus();

    // activities
    MainScreenActivity mMainScreenActivity;
    PostLoginActivity mPostLoginActivity;



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

        if (getActivity() instanceof MainScreenActivity){
            mMainScreenActivity = (MainScreenActivity) getActivity();
        }
        else {
            mPostLoginActivity = (PostLoginActivity) getActivity();
        }

        // displaying the data which is strored in shared preference from previous page
        mEditTextName.setText(new GlobalPrefs(getContext()).getString("Username"));
        mEditTextEmail.setText(new GlobalPrefs(getContext()).getString("Useremail"));

        // check box functions
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

    @OnClick(R.id.linearLayout_checkbox)
    public void textChangingOfEditText(){
        if (checkbox.isChecked()){
            mEditTextDesignation.setHint("Course");
            mEditTextCompany.setHint("University / College");
        }
        else if (!checkbox.isChecked()){
            mEditTextDesignation.setHint("Designation");
            mEditTextCompany.setHint("Organization");
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
        String id = new GlobalPrefs(getContext()).getString("Userid");


        Call<String> call = ApiClient.getServerApi().signupComplete(id, //id
                checkbox.isChecked(),                                          // isNerd
                mEditTextBio.getText().toString().trim(),                      // bio
                mEditTextPhone.getText().toString().trim(),                    // phone
                mEditTextWebLink.getText().toString().trim(),                  // web
                mSpinnerBranch.getSelectedItem().toString().trim(),            // spinner
                mSpinnerYear.getSelectedItem().toString().trim(),              // year
                mEditTextLocationHome.getText().toString().trim(),             // home locstion
                mEditTextLocationWork.getText().toString().trim(),             // work location
                mEditTextDesignation.getText().toString().trim(),              // designation
                mEditTextCompany.getText().toString().trim()                   // company
                );

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                mMainScreenActivity.changeFragment(new FragmentMainScreen().newInstance("",""));
                TastyToast.makeText(getContext(),"Details Updated",TastyToast.LENGTH_SHORT,TastyToast.SUCCESS);

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
