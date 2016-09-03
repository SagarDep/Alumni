package com.example.ashish.alumini.activities.PreLogin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.ashish.alumini.activities.PostLogin.MainScreenActivity;
import com.example.ashish.alumini.R;
import com.example.ashish.alumini.supporting_classes.GlobalPrefs;
import com.sdsmdg.tastytoast.TastyToast;

//import com.example.ashish.alumini.R;

public class Login extends Activity {

    String TAG = getClass().getSimpleName();
    EditText email,password;
    Button loginButton;

    RelativeLayout mRelativeLayout;
    int mCounter=0;

    SharedPreferences mSharedPreferences;

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

    }

    //body of login method
    public void login() {

        if (!validate()) {
            onLoginFailed();
            return;
        }


        loginButton.setEnabled(false);

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        String emailString = email.getText().toString().trim();
        String wordString = password.getText().toString().trim();


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        onLoginSuccess();
                        pDialog.dismiss();
                    }
                }, 1000);

    }

    public void onLoginSuccess() {
        loginButton.setEnabled(true);
        Intent move=new Intent(Login.this,MainScreenActivity.class);
        startActivity(move);

        new GlobalPrefs(getApplicationContext()).putBooloean(getString(R.string.is_logged_in),true);
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

}