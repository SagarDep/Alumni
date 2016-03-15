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

public class Login extends Activity
{

   private EditText email,password;
    Button loginButton;

    private String emailString;
    private String passwordString;
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

        loginButton.setEnabled(false);

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
        loginButton.setEnabled(true);
        Intent move=new Intent(Login.this,MainScreen.class);
        startActivity(move);
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        loginButton.setEnabled(true);
    }


}