package com.example.ashish.alumini;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ashish on 8/3/16.
 */
public class Edit_Profile extends Activity {
    private  EditText name , description , location_home , location_work , location , designation , company , course , institute , twitter , email , phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        Spinner spinner_branch =  (Spinner) findViewById(R.id.spinner_branch);
        ArrayAdapter <CharSequence>  adapter_branch = ArrayAdapter.createFromResource(this,R.array.branch_array,R.layout.support_simple_spinner_dropdown_item);
        adapter_branch.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_branch.setAdapter(adapter_branch);

        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.image);
        ImageView mImage = (ImageView) findViewById(R.id.imageView_com_logo);
        mImage.setImageBitmap(ListVar.getCircleBitmap(bm));

        Spinner spinner_year  = (Spinner) findViewById(R.id.spinner_year);
        ArrayAdapter <CharSequence> adapter_year = ArrayAdapter.createFromResource(this,R.array.year_array,R.layout.support_simple_spinner_dropdown_item);
        spinner_year.setAdapter(adapter_year);

        final EditText name , description , location_home , location_work  , designation , company , course , institute , twitter , email , phone;

        name= (EditText) findViewById(R.id.editText_name);
        description = (EditText) findViewById(R.id.Edittext_desc);
        location_home = (EditText) findViewById(R.id.ediText_home);
        location_work = (EditText) findViewById(R.id.editText_location);
        designation = (EditText) findViewById(R.id.editText_designation);
        company = (EditText) findViewById(R.id.editText_company);
        course = (EditText) findViewById(R.id.editText_course);
        institute = (EditText) findViewById(R.id.editText_univ);
        twitter = (EditText) findViewById(R.id.editText_twlink);
        email = (EditText) findViewById(R.id.editText_mail);
        phone = (EditText) findViewById(R.id.editText_contact);




        Button  button = (Button) findViewById(R.id.button_save);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Log.d("SAVING","Inside saving");

                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.150.3:5000/aryaalumni/api/v1.0/signup?",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.contains("1062")){
                                    Log.d("SAVING","Inside saving e");
                                    Snackbar.make(view, "Sorry! Database already contains this email", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("SAVING",error.toString());
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name",name.getText().toString());
                        params.put("email",email.getText().toString());
                        params.put("password", "pawd");
                        params.put("description", "description");
                        params.put("branch", "CS");
                        params.put("graduate_year", "2017");
                        params.put("work_designation", "2017");
                        params.put("work_company", "2017");
                        params.put("pg_course", "2017");
                        params.put("pg_college", "2017");
                        params.put("location_city", "2017");
                        params.put("location_country", "2017");
                        params.put("contact_twitter", "2017");
                        params.put("contact_phone", "2017");
                        return params;
                    }

                };

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);

            }
        });
    }

}
