package com.example.ashish.alumini.activities.PreLogin;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ashish.alumini.Edit_Profile;
import com.example.ashish.alumini.R;


public class SignUp extends Activity
{

    EditText email,name,password,confirmPassword;
    Button SignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        email=(EditText)findViewById(R.id.editText_signup_name);
        password=(EditText)findViewById(R.id.editText_signup_email);
        name=(EditText)findViewById(R.id.editText_signup_password);
        confirmPassword=(EditText)findViewById(R.id.editText_signup_passwordConfirm);
        SignUpButton=(Button)findViewById(R.id.button_signup);


        SignUpButton.setOnClickListener(new View.OnClickListener() {
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

        SignUpButton.setEnabled(false);

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        String    emailString = email.getText().toString().trim();
        String passwordString = password.getText().toString().trim();
        String nameString=name.getText().toString().trim();
        String confirmPasswordString=confirmPassword.getText().toString().trim();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        onSignUpSuccess();

                        pDialog.dismiss();
                    }
                }, 2000);

    }

    public void onSignUpSuccess() {
        SignUpButton.setEnabled(true);
        Intent move=new Intent(SignUp.this,Edit_Profile.class);
        move.putExtra("SIGN_UP","signup");
        move.putExtra("NAME",name.getText());
        move.putExtra("EMAIL",email.getText());
        move.putExtra("PASS",password.getText());

        startActivity(move);
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        SignUpButton.setEnabled(true);
    }

    //valide method
    public boolean validate() {
        boolean valid = true;

        String nameString = name.getText().toString();
        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();
        String confirmPasswordString = confirmPassword.getText().toString();

        if (nameString.isEmpty() || nameString.length() < 3) {
            name.setError("at least 3 characters");
            valid = false;
        } else {
            name.setError(null);
        }

        if (emailString.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailString).matches()) {
            email.setError("enter a valid email address");
            valid = false;
        } else {
            email.setError(null);
        }

        if (passwordString.isEmpty() || passwordString.length() < 6 || passwordString.length() > 255) {
            password.setError("between 6 and 255 alphanumeric characters");
            valid = false;
        } else {
            password.setError(null);
        }

        if(confirmPasswordString.isEmpty() || confirmPasswordString.equals(passwordString))
        {
            confirmPassword.setError("Password do not match");
            valid=true;
        }
        else
        {
            confirmPassword.setError(null);
        }

        return valid;

    }


}