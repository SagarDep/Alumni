package com.example.ashish.alumini.activities.PreLogin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.ashish.alumini.activities.PostLogin.MainScreenActivity;
import com.example.ashish.alumini.R;
import com.example.ashish.alumini.application.MyApplication;
import com.example.ashish.alumini.network.ApiClient;
import com.example.ashish.alumini.network.pojo.LoginResponse;
import com.example.ashish.alumini.supporting_classes.GlobalPrefs;
import com.example.ashish.alumini.supporting_classes.RetrofitErrorHandler;
import com.sdsmdg.tastytoast.TastyToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

//import com.example.ashish.alumini.R;

public class Login extends Activity {

    String TAG = getClass().getSimpleName();
    EditText email,password;
    Button loginButton;

    RelativeLayout mRelativeLayout;
    int mCounter=0;

    MyApplication mApplication;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        email=(EditText)findViewById(R.id.editText_login_email);
        password=(EditText)findViewById(R.id.editText_login_password);
        loginButton=(Button)findViewById(R.id.login);

        loginButton.setOnClickListener(
                new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout_login);

        mApplication  = (MyApplication) getApplication();


    }

    //body of login method
    public void login() {

        if (!validate()) {
            onLoginFailed();
            return;
        }


//        loginButton.setEnabled(false);



        String emailString = email.getText().toString().trim();
        String password = this.password.getText().toString().trim();


      makeServerCallToLogin(emailString, password);

    }

    public void onLoginSuccess() {


        loginButton.setEnabled(true);
        Intent move=new Intent(Login.this,MainScreenActivity.class);
        startActivity(move);

    }

    public void onLoginFailed() {
//        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        TastyToast.makeText(getApplicationContext(),"Login Failed",TastyToast.LENGTH_LONG,TastyToast.ERROR);

        loginButton.setEnabled(true);
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
            password.setError("Enter between 6 and 255 alphanumeric characters");
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
            finish();
        }

    }
    public void makeServerCallToLogin(String email, String password){

        Call<LoginResponse> call = ApiClient.getServerApi().login(email, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d(TAG, "API successful");
                RetrofitErrorHandler errorHandler = new RetrofitErrorHandler();
                errorHandler.statusCodeHandler(getBaseContext(),response.code());
                if (response.code()==200){
                    TastyToast.makeText(getBaseContext(),"Successful",TastyToast.LENGTH_SHORT,TastyToast.SUCCESS);
                    LoginResponse response1 = response.body();

                    GlobalPrefs globalPrefs = new GlobalPrefs(getApplicationContext());
                    if (response1!=null){
                        // storing id and name in shared pref
                        globalPrefs.putString("Userid",response1.get_id());
                        globalPrefs.putString("Username",response1.getName());
                    }
                    // for maintaining session
                    globalPrefs.putBooloean(getString(R.string.is_logged_in),true);

                    // function for creating list class to make server class and fetch data
                    mApplication.createListCLass();
                    // function to change activity and
                    onLoginSuccess();

                }
//                else if (response.code()==600){
//                    TastyToast.makeText(getBaseContext(),"User not found",TastyToast.LENGTH_SHORT,TastyToast.ERROR);
//                }
                else if (response.code()==700){
                    TastyToast.makeText(getBaseContext(),"Password ot Matched",TastyToast.LENGTH_SHORT,TastyToast.ERROR);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d(TAG, "API failed");
            }
        });

    }


}