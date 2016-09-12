package com.example.ashish.alumini.fragments.settings;

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
import com.example.ashish.alumini.activities.PostLogin.PostLoginActivity;
import com.example.ashish.alumini.network.ApiClient;
import com.example.ashish.alumini.network.pojo.MemberInstance;
import com.example.ashish.alumini.supporting_classes.GlobalPrefs;
import com.github.lguipeng.library.animcheckbox.AnimCheckBox;
import com.sdsmdg.tastytoast.TastyToast;
import com.squareup.otto.Bus;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentEditProfile extends android.support.v4.app.Fragment {


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

    String stringArrayBranch[];
    String stringArrayYear[];
    ArrayList<String> mArrayListBranch = new ArrayList<>();
    ArrayList<String> mArrayListYear = new ArrayList<>();


    // event bus registering
    Bus mBus = new Bus();

    // activities
    PostLoginActivity mPostLoginActivity;



    public FragmentEditProfile() {
        // Required empty public constructor
    }


    public static FragmentEditProfile newInstance(String param1, String param2) {
        FragmentEditProfile fragment = new FragmentEditProfile();
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

        mPostLoginActivity = (PostLoginActivity) getActivity();

        // getting id from shared pref and initiating ap i call
        String id = new GlobalPrefs(getActivity()).getString(getString(R.string.userid));
        makeServerToGetCompleteData(id);

//        fetcing the String array and converting to ArrayList
        stringArrayBranch =  getResources().getStringArray(R.array.branch_array);
        stringArrayYear =  getResources().getStringArray(R.array.year_array);

        for ( String a : stringArrayBranch){
            mArrayListBranch.add(a);
        }
        for ( String a : stringArrayYear){
            mArrayListYear.add(a);
        }




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
        String id = new GlobalPrefs(getContext()).getString(getString(R.string.userid));


        Call<String> call = ApiClient.getServerApi().signupComplete(id,        //id
                mEditTextName.getText().toString().trim(),                     // name
                mEditTextBio.getText().toString().trim(),                      // bio
                mSpinnerBranch.getSelectedItem().toString().trim(),            // branch
                mSpinnerYear.getSelectedItem().toString().trim(),              // year
                checkbox.isChecked(),                                          // isNerd
                mEditTextDesignation.getText().toString().trim(),              // designation
                mEditTextCompany.getText().toString().trim(),                  // company
                mEditTextLocationHome.getText().toString().trim(),             // home location
                mEditTextLocationWork.getText().toString().trim(),             // work location
                mEditTextPhone.getText().toString().trim(),                    // phone
                mEditTextWebLink.getText().toString().trim(),                  // web
                "facebook link"                                                // fb link
                );

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                mPostLoginActivity.changeFragment(new FragmentProfile());
                TastyToast.makeText(getContext(),"Details Updated",TastyToast.LENGTH_SHORT,TastyToast.SUCCESS);

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG, "API call failed");
            }
        });

    }

    public boolean validate(){


        if (mEditTextName.getText().toString().trim().length()<=6){
            mEditTextName.setError("Name must be greater than 6 characters");
            return false;
        }
        else  if (mEditTextBio.getText().toString().trim().length()==0){
            mEditTextBio.setError("Make it a little Big");
            return false;
        }
        else if (mEditTextDesignation.getText().toString().trim().length()==0){
            mEditTextDesignation.setError("Invalid Designation");
            return false;
        }
        else if (mEditTextCompany.getText().toString().trim().length()==0){
            mEditTextCompany.setError("Invalid Designation");
            return false;
        }

        if (mEditTextLocationHome.getText().toString().trim().length()==0){
            mEditTextLocationHome.setError("Invalid Location");
            return false;
        }
        else if (mEditTextLocationWork.getText().toString().trim().length()==0){
            mEditTextLocationWork.setError("Invalid Location");
            return false;
        }else if (mEditTextPhone.getText().toString().trim().length()==10){
            mEditTextPhone.setError("Invalid Phone Number");
            return false;
        }
        else if (false){
            mEditTextPhone.setError("Invalid Designation");
            return false;
        }



        return true;
    }

    /*
    * Method to fetch values from server when the user will press edit option
    * */
    public void makeServerToGetCompleteData(String id){
        Call<MemberInstance> call = ApiClient.getServerApi().getCompleteProfileData(id);

        call.enqueue(new Callback<MemberInstance>() {
            @Override
            public void onResponse(Call<MemberInstance> call, Response<MemberInstance> response) {

                if (response.code()==200 && response.body()!=null){
                    setCompleteData(response.body());
                }
                Log.d(TAG, "API call successful");
            }

            @Override
            public void onFailure(Call<MemberInstance> call, Throwable t) {
                Log.d(TAG, "API call failed");
            }
        });
    }

    public void setCompleteData(MemberInstance completeData){
                mEditTextName.setText(completeData.getName());                     // name
                mEditTextBio.setText(completeData.getBio());                      // bio
                mSpinnerBranch.setSelection(mArrayListBranch.indexOf(completeData.getBranch()));          // branch
                mSpinnerYear.setSelection(mArrayListYear.indexOf(completeData.getBranch()));            // year
                checkbox.setChecked(completeData.getIsNerd());                    // isNerd
                mEditTextDesignation.setText(completeData.getDesignation());       // designation
                mEditTextCompany.setText(completeData.getCompany());               // company
                mEditTextLocationHome.setText(completeData.getHome());             // home location
                mEditTextLocationWork.setText(completeData.getWork());             // work location
                mEditTextPhone.setText(completeData.getPhone());                    // phone
                mEditTextWebLink.setText(completeData.getWeblink());

    }
}