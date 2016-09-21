package com.example.ashish.alumini.fragments.common_fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ashish.alumini.R;
import com.example.ashish.alumini.activities.post_login.MainScreenActivity;
import com.example.ashish.alumini.activities.post_login.PostLoginActivity;
import com.example.ashish.alumini.fragments.main_screen_fragments.FragmentMainScreen;
import com.example.ashish.alumini.network.ApiClient;
import com.example.ashish.alumini.supporting_classes.GlobalPrefs;
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
    CheckBox checkbox;

    // name and bio
    @Bind(R.id.editText_memberName)
            EditText mEditTextName;
    @Bind(R.id.editText_memberBio)
    EditText mEditTextBio;

    // designation and company EditTexts
    @Bind(R.id.editText_Designation)
        EditText mEditTextDesignation;
    @Bind(R.id.editText_company)
        EditText mEditTextCompany;

    @Bind(R.id.textView_memberDesignation)
    TextInputLayout mTextInputLayoutDesignation;
    // designation and company
    @Bind(R.id.textInputLayout_company)
        TextInputLayout mTextInputLayoutCompany;


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

        // initialization of activity
        if (getActivity() instanceof MainScreenActivity){
            mMainScreenActivity = (MainScreenActivity) getActivity();
        }
        else {
            mPostLoginActivity = (PostLoginActivity) getActivity();
        }

        // displaying the data which is strored in shared preference from previous page
        mEditTextName.setText(new GlobalPrefs(getContext()).getString(getString(R.string.username)));
        mEditTextEmail.setText(new GlobalPrefs(getContext()).getString(getString(R.string.useremail)));



        // editing
        if (mParam1=="edit"){
            String id = new GlobalPrefs(getActivity()).getString(getString(R.string.userid));
//            makeServerToGetCompleteData(id);
        }

        return view;
    }

    @OnClick(R.id.button_save_profile_data)
    public void saveDataHandler(){
        if (validate()){
            makeServerCalltoPostCompleteData();
        }
    }

    @OnClick(R.id.checkBox_isNerd)
    public void textChangingOfEditText(){
        if (checkbox.isChecked()){
            mTextInputLayoutDesignation.setHint("Course");
            mTextInputLayoutCompany.setHint("University / College");
        }
        else if (!checkbox.isChecked()){
            mTextInputLayoutDesignation.setHint("Designation");
            mTextInputLayoutCompany.setHint("Organization");
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

    /*
    * Post the edited text
    * */
    public void makeServerCalltoPostCompleteData(){
        // getting the id from shared preffernece which was stored during partial signup
        String id = new GlobalPrefs(getContext()).getString(getString(R.string.userid));


        Call<String> call = ApiClient.getServerApi().signupComplete(id,        // id
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
                // hiding the progress bar

               if (response.code()==201){
                   mMainScreenActivity.changeFragment(new FragmentMainScreen());
                   TastyToast.makeText(getContext(),"Details Updated",TastyToast.LENGTH_SHORT,TastyToast.SUCCESS);
                   // storing id and name in shared pref
//                   globalPrefs.putString(getString(R.string.userid),response1.get_id());

                   GlobalPrefs globalPrefs = new GlobalPrefs(getActivity());
                   globalPrefs.putString(getString(R.string.username),
                           mEditTextName.getText().toString().trim()                     // name
                   );

                   // for session maintaining
                   globalPrefs.putBooloean(getString(R.string.is_logged_in),true);
               }

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
        }else if (mEditTextPhone.getText().toString().trim().length()==11){
            mEditTextPhone.setError("Invalid Phone Number");
            return false;
        }
        else if (false){
            mEditTextPhone.setError("Invalid Designation");
            return false;
        }



        return true;
    }


}
