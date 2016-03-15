package com.example.ashish.alumini;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.signupdegin.R;

public class SignUp extends Activity
{

     EditText email,password,name,mobile;
    Button SignUpButton;

    private String emailString,passwordString,nameString,mobileString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        name=(EditText)findViewById(R.id.name);
        mobile=(EditText)findViewById(R.id.Mobilenumber);
        SignUpButton=(Button)findViewById(R.id.signup);


        SignUpButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                signUp();
            }
        });

    }

    //body of signUp method
    public void signUp() {

        SignUpButton.setEnabled(false);

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        emailString = email.getText().toString().trim();
        passwordString = password.getText().toString().trim();


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        onLoginSuccess();

                        pDialog.dismiss();
                    }
                }, 2000);

    }

    public void onLoginSuccess() {
        SignUpButton.setEnabled(true);
        Intent move=new Intent(SignUp.this,MainScreen.class);
        startActivity(move);
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        SignUpButton.setEnabled(true);
    }


}