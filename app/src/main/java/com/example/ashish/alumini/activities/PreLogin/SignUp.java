package com.example.ashish.alumini.activities.PreLogin;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.ashish.alumini.R;
import com.example.ashish.alumini.activities.PostLogin.ActivityMainScreen;
import com.example.ashish.alumini.network.ApiClient;
import com.example.ashish.alumini.network.pojo.SignupPart;
import com.example.ashish.alumini.supporting_classes.GlobalPrefs;
import com.sdsmdg.tastytoast.TastyToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUp extends Activity {

    String TAG = getClass().getSimpleName();

    EditText mEditTextemail, mEditTextName, mEditTextPassword, mEditTextConfirmPassword;
    Button mButtonSignup;

    LinearLayout mRelativeLayout;
    int mCounter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        mEditTextName =(EditText)findViewById(R.id.editText_signup_name);
        mEditTextemail =(EditText)findViewById(R.id.editText_signup_email);
        mEditTextPassword =(EditText)findViewById(R.id.editText_signup_password);
        mEditTextConfirmPassword =(EditText)findViewById(R.id.editText_signup_passwordConfirm);
        mButtonSignup =(Button)findViewById(R.id.button_signup);
        mRelativeLayout = (LinearLayout) findViewById(R.id.linearLayout_signup);


        mButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

    }

    //body of signUp method
    public void signUp() {

        if (!validate()) {
            onSignupFailed();
            return;
        }
        onSignUpSuccess();

        String emailString = mEditTextemail.getText().toString().trim();
        String passwordString = mEditTextPassword.getText().toString().trim();
        String nameString= mEditTextName.getText().toString().trim();
        String confirmPasswordString= mEditTextConfirmPassword.getText().toString().trim();


        startMainScreenActivity(null);
    }

    public void onSignUpSuccess() {
        mButtonSignup.setEnabled(true);

        makeserverCallToPostSignupPartialData();


    }

    public void onSignupFailed() {
        TastyToast.makeText(getBaseContext(), "Signup failed", Toast.LENGTH_LONG,TastyToast.ERROR);

        mButtonSignup.setEnabled(true);
    }

    //valide method
    public boolean validate() {
        boolean valid = true;

        String nameString = mEditTextName.getText().toString();
        String emailString = mEditTextemail.getText().toString();
        String passwordString = mEditTextPassword.getText().toString();
        String confirmPasswordString = mEditTextConfirmPassword.getText().toString();

        if (nameString.isEmpty() || nameString.length() < 3) {
            mEditTextName.setError("at least 3 characters");
            valid = false;
        }

        if (emailString.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailString).matches()) {
            mEditTextemail.setError("enter a valid mEditTextemail address");
            valid = false;
        }

        if ( passwordString.length() < 6 ) {
            mEditTextPassword.setError("must be more than 6 characters");
            valid = false;
        }
        if(confirmPasswordString.isEmpty() || !confirmPasswordString.equals(passwordString))
        {
            mEditTextConfirmPassword.setError("Password do not match");
            valid=false;
        }


        return valid;

    }


    public void makeserverCallToPostSignupPartialData(){
        Call<SignupPart> call = ApiClient.getServerApi().signupPartial(
                mEditTextName.getText().toString().trim(),
                mEditTextemail.getText().toString().trim(),
                mEditTextPassword.getText().toString().trim());

        call.enqueue(new Callback<SignupPart>() {
            @Override
            public void onResponse(Call<SignupPart> call, Response<SignupPart> response) {
                Log.d(TAG,"API successful");
                SignupPart signupPart = response.body();
                GlobalPrefs.putString("Userid",signupPart.get_id());
                GlobalPrefs.putString("Username",signupPart.getName());

                /*
                * Start Activity main screen in which the fragments will be displayed
                * */
                startMainScreenActivity(signupPart);
            }

            @Override
            public void onFailure(Call<SignupPart> call, Throwable t) {
                Log.d(TAG,"API failed");
            }
        });
    }
    public void startMainScreenActivity(SignupPart  signupPart){
        Intent move=new Intent(SignUp.this,ActivityMainScreen.class);
                /*
                * SIGNUP is sent beacuse when the login is successful,
                 * then from another session the login/signup will be skipped
                 * to change the fragment of signup, "SIGNUP" is used
                * */
        move.putExtra("SIGNUP",true);
        startActivity(move);
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
}