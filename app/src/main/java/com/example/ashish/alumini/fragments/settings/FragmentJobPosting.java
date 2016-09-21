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
import com.example.ashish.alumini.activities.post_login.PostLoginActivity;
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

    @Bind(R.id.editText_Designation)
    TextInputEditText mInputEditTextJobDescription;

    @Bind(R.id.editText_jobLocation)
    TextInputEditText mInputEditTextJobLocation;

    @Bind(R.id.editText_companyWebLink)
    EditText mEditTextWebLink;

    @Bind(R.id.editText_member_email)
    EditText mEditTextemail;

    Bus mBus = new Bus();

    PostLoginActivity mActivity;

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

        mActivity = (PostLoginActivity) getActivity();


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
        /*
        * Function will validate the data and then make the server call
        * */
        boolean validDeta = false;
        if (mInputEditTextCompanyName.getText().toString().isEmpty()){
            mInputEditTextCompanyName.setError("Invalid Name");
        }
        else if (mInputEditTextRole.getText().toString().isEmpty()){
            mInputEditTextRole.setError("Empty Fields");
        }
        else if (mInputEditTextJobDescription.getText().toString().length()<6){
            mInputEditTextJobDescription.setError("Invalid details");
        }
        else if (mInputEditTextJobLocation.getText().toString().length()<3){
            mInputEditTextJobLocation.setError("Invalid details");
        }
        else if (mEditTextWebLink.getText().toString().length()<6){
            mEditTextWebLink.setError("Invalid WebLink");
        }
        else if (mEditTextemail.getText().toString().length()<6 && android.util.Patterns.EMAIL_ADDRESS.matcher(mEditTextemail.getText()).matches()){
            mEditTextemail.setError("Invalid Email address");
        }
        else {
            validDeta = true;
        }


        if (validDeta){
            makeServerCallToPostData();
//            makeServerCallToUploadData();
        }
    }

    public void makeServerCallToPostData(){
        // show progress bar
        mBus.post(true);

        Call<String> call = ApiClient.getServerApi().postJob(
                mInputEditTextCompanyName.getText().toString().trim(),
                mInputEditTextRole.getText().toString().trim(),
                mInputEditTextJobDescription.getText().toString().trim(),
                mInputEditTextJobLocation.getText().toString().trim(),
                mEditTextWebLink.getText().toString().trim(),
                mEditTextemail.getText().toString().trim(),
                new GlobalPrefs(getActivity()).getString(getString(R.string.username)),
                new GlobalPrefs(getActivity()).getString(getString(R.string.userid))
        );

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                // hiding progress bar
                mBus.post(false);

                Log.d(TAG,"Successful");
                /*
                * Show toast
                * */
                TastyToast.makeText(getActivity(),"Posted! yeah",TastyToast.LENGTH_SHORT,TastyToast.SUCCESS);
                /*
                * Clear all the textFields
                * */
                cleardata();

                mActivity.changeFragment(new FragmentSettings());

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("API Call ","Failed");
                /*
                * only show toast for now
                * */
                TastyToast.makeText(getActivity(),"Posting Failed",TastyToast.LENGTH_SHORT,TastyToast.ERROR);

                // hiding progress bar
                mBus.post(false);
            }
        });

    }

    public void cleardata(){
        mInputEditTextCompanyName.setText("");
                mInputEditTextRole.setText("");
                mInputEditTextJobDescription.setText("");
                mInputEditTextJobLocation.setText("");
                mEditTextWebLink.setText("");
                mEditTextemail.setText("");
    }
//    public void makeServerCallToUploadData(){
//
//        ProgressDialog progressBar = new ProgressDialog( getActivity());
//        progressBar.setMessage("uploading");
//        progressBar.show();
//
//
//
//        File file = new File("/storage/extSdCard/DCIM/Camera/20160901_082158.jpg");
//
////        RequestBody requestBody = new RequestBody() {
////            @Override
////            public MediaType contentType() {
////                return null;
////            }
////
////            @Override
////            public void writeTo(BufferedSink sink) throws IOException {
////
////            }
////        };
////
////        Call<String> call = ApiClient.getServerApi().upload(requestBody);
//        //Create Upload Server Client
//        ApiService service = RetroClient.getApiService();
//
//        //File creating from selected URL
//        File file = new File(imagePath);
//
//        // create RequestBody instance from file
//        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//
//        // MultipartBody.Part is used to send also the actual file name
//        MultipartBody.Part body =
//                MultipartBody.Part.createFormData("uploaded_file", file.getName(), requestFile);
//
//        Call<Result> resultCall = service.uploadImage(body);
//
//        // finally, execute the request
//        resultCall.enqueue(new Callback<Result>() {
//            @Override
//            public void onResponse(Call<Result> call, Response<Result> response) {
//
//                progressDialog.dismiss();
//
//                // Response Success or Fail
//                if (response.isSuccessful()) {
//                    if (response.body().getResult().equals("success"))
//                        Snackbar.make(parentView, R.string.string_upload_success, Snackbar.LENGTH_LONG).show();
//                    else
//                        Snackbar.make(parentView, R.string.string_upload_fail, Snackbar.LENGTH_LONG).show();
//
//                } else {
//                    Snackbar.make(parentView, R.string.string_upload_fail, Snackbar.LENGTH_LONG).show();
//                }
//
//                /**
//                 * Update Views
//                 */
//                imagePath = "";
//                textView.setVisibility(View.VISIBLE);
//                imageView.setVisibility(View.INVISIBLE);
//            }
//
//            @Override
//            public void onFailure(Call<Result> call, Throwable t) {
//                progressDialog.dismiss();
//            }
//        });
//
//
//    }

}
