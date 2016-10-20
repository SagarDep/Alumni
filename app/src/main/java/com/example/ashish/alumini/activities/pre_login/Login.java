package com.example.ashish.alumini.activities.pre_login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashish.alumini.activities.post_login.MainScreenActivity;
import com.example.ashish.alumini.R;
import com.example.ashish.alumini.application.MyApplication;
import com.example.ashish.alumini.network.ApiClient;
import com.example.ashish.alumini.network.pojo.LoginResponse;
import com.example.ashish.alumini.supporting_classes.GlobalBus;
import com.example.ashish.alumini.supporting_classes.GlobalPrefs;
import com.example.ashish.alumini.supporting_classes.ProgressBarVisibility;
import com.example.ashish.alumini.supporting_classes.RetrofitErrorHandler;
import com.sdsmdg.tastytoast.TastyToast;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.example.ashish.alumini.R;

public class Login extends Activity {

    String TAG = getClass().getSimpleName();

    @Bind(R.id.editText_login_email)
    EditText email;

    EditText password;
    Button loginButton;

    @Bind(R.id.textView_forgetpasswd)
    TextView mTextViewForgetPassword;

    // for snack bar
    RelativeLayout mRelativeLayout;
    int mCounter=0;

    MyApplication mApplication;

    GlobalBus mGlobalBus ;


     ProgressBarVisibility barVisibility = new ProgressBarVisibility();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // butterknife binding
        ButterKnife.bind(this);

        mGlobalBus= GlobalBus.getInstance();

        password=(EditText)findViewById(R.id.editText_login_password);
        loginButton=(Button)findViewById(R.id.button_login);



        mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout_login);

        mApplication  = (MyApplication) getApplication();



    }

    // forget password
    @OnClick(R.id.textView_forgetpasswd)
    public void forgetPasswordListener(){

        Log.d(TAG, "forget clicked");

        final String emailString = email.getText().toString().trim();

        if (emailString.trim().length()<6){
            TastyToast.makeText(Login.this,"Invalid email",Toast.LENGTH_SHORT,TastyToast.INFO);
            return;
        }





        loginButton.setEnabled(false);

        final MaterialDialog materialDialog = new MaterialDialog(this);
        materialDialog.setTitle("Reset Password")
                .setMessage("Next step will give you a new password on given email id")
                .setPositiveButton("Reset", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        materialDialog.dismiss();

                        makeServerCallToResetPassword(emailString);

                    }
                })
                .setCanceledOnTouchOutside(true)
                .show();



    }

    //body of login method
    @OnClick(R.id.button_login)
    public void login() {

        if (!validate()) {
            onLoginFailed();
            return;
        }


        loginButton.setEnabled(false);



        String emailString = email.getText().toString().trim();
        String password = this.password.getText().toString().trim();


      makeServerCallToLogin(emailString, password);

    }



    public void onLoginSuccess(Intent intent) {


        this.finish();
        loginButton.setEnabled(true);
        startActivity(intent);

    }

    public void onLoginFailed() {
//        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        TastyToast.makeText(getApplicationContext(),"Login Failed",TastyToast.LENGTH_LONG,TastyToast.ERROR);


    }

    public boolean validate() {
        boolean valid = true;

        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();

        if (emailString.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailString).matches()) {
            email.setError("enter a valid mEditTextemail address");
            valid = false;
        } else {
            email.setError(null);
        }

        if (passwordString.isEmpty() || passwordString.length() < 6 || passwordString.length() > 255) {
            password.setError("Enter a strong password");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }


    @Override
    public void onBackPressed() {
        mCounter++;
        if (mCounter==1){
            Snackbar snackbar = Snackbar
                    .make(mRelativeLayout, "Press Back again to exit", Snackbar.LENGTH_LONG);
            snackbar.show();
        }

        if (mCounter==2){
            this.finish();
            System.exit(2);
        }

    }
    public void makeServerCallToLogin(String email, String password){
//        mDialog.show();

        postHideSignal(true);

        Call<LoginResponse> call = ApiClient.getServerApi().login(email, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d(TAG, "API successful");

                //hide the progress bar
                postHideSignal(false);

                //making button clickable
                loginButton.setEnabled(true);

                RetrofitErrorHandler errorHandler = new RetrofitErrorHandler();
                errorHandler.statusCodeHandler(getBaseContext(),response.code());
                if (response.code()==200 ){
                    TastyToast.makeText(getBaseContext(),"Successful",TastyToast.LENGTH_SHORT,TastyToast.SUCCESS);
                    LoginResponse loginResponse = response.body();

                    GlobalPrefs globalPrefs = new GlobalPrefs(getApplicationContext());
                    if (loginResponse!=null){
                        // storing id and name in shared pref
                        globalPrefs.putString(getString(R.string.userid),loginResponse.get_id());
                        globalPrefs.putString(getString(R.string.username),loginResponse.getName());
                    }

                    // function for creating list class to make server calls and fetch data
                    mApplication.createListCLass();

                    // intent to start new activity
                    Intent intent=new Intent(Login.this,MainScreenActivity.class);


                    if (loginResponse.getWork()!=null){
                        // for maintaining session
                        globalPrefs.putBooloean(getString(R.string.is_logged_in),true);

                    }
                    else if (loginResponse.getWork()==null){
                        // now MainScreenActivity will handle this
                        intent.putExtra("SIGNUP",true);
                    }
                    //function to change activity and
                    onLoginSuccess(intent);


                }
                else if (response.code()==600){ // 600 is generated by server side
                    TastyToast.makeText(getBaseContext(),"User not found",TastyToast.LENGTH_SHORT,TastyToast.ERROR);
                }
                else if (response.code()==700){ //700 is generated by server side
                    TastyToast.makeText(getBaseContext(),"Password not Matched",TastyToast.LENGTH_SHORT,TastyToast.ERROR);
                }
//                else if (response.code()==800){
//                    // logged in but dont' have any details
//                    TastyToast.makeText(getBaseContext(),"Successful",TastyToast.LENGTH_SHORT,TastyToast.SUCCESS);
//
//
//
//                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                postHideSignal(false);

                Log.d(TAG, "API failed");
                TastyToast.makeText(getBaseContext(),"Login Failed",TastyToast.LENGTH_SHORT,TastyToast.WARNING);

                Snackbar snackbar = Snackbar
                        .make(mRelativeLayout, "Can't connect to cloud", Snackbar.LENGTH_LONG);
                snackbar.show();

                loginButton.setEnabled(true);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        mGlobalBus.unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGlobalBus.register(this);

    }

    public void postHideSignal(Boolean state){
        // mGlobalBus posting
        barVisibility.setVisibility(state);
        mGlobalBus.post(barVisibility);
    }

    public void makeServerCallToResetPassword( String emailString){

        // progress bar visibility
        postHideSignal(true);

        Call<String> call = ApiClient.getServerApi().resetPassword(emailString);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                TastyToast.makeText(Login.this,response.body(),Toast.LENGTH_SHORT,TastyToast.INFO);

                // progress bar visibility
                postHideSignal(false);

                loginButton.setEnabled(true);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Snackbar snackbar = Snackbar
                        .make(mRelativeLayout, "Can't connect to cloud", Snackbar.LENGTH_LONG);
                snackbar.show();

                // progress bar visibility
                postHideSignal(false);

                loginButton.setEnabled(true);
            }
        });

    }


}