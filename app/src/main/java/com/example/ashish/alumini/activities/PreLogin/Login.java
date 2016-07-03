package com.example.ashish.alumini.activities.PreLogin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ashish.alumini.activities.PostLogin.MainScreen;
import com.example.ashish.alumini.R;

//import com.example.ashish.alumini.R;

public class Login extends Activity
{

    EditText email,password;
    Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        loginButton=(Button)findViewById(R.id.login);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });
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
                }, 100);

    }

    public void onLoginSuccess() {
        loginButton.setEnabled(true);
        Intent move=new Intent(Login.this,MainScreen.class);
        startActivity(move);
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();

        if (emailString.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailString).matches()) {
            email.setError("enter a valid email address");
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




}