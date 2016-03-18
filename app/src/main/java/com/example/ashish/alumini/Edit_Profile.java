package com.example.ashish.alumini;

import android.app.Activity;
import android.content.Intent;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ashish on 8/3/16.
 */
public class Edit_Profile extends Activity {
    private  EditText name , description , location_home , location_work , location , designation , company , course , institute , twitter , email , phone;
    public String uid = null;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        final Bundle bundle = getIntent().getExtras();
        final String foo =  bundle.get("SIGN_UP").toString();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        final Spinner spinner_branch =  (Spinner) findViewById(R.id.spinner_branch);
        ArrayAdapter <CharSequence>  adapter_branch = ArrayAdapter.createFromResource(this,R.array.branch_array,R.layout.support_simple_spinner_dropdown_item);
        adapter_branch.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_branch.setAdapter(adapter_branch);

        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.image);
        ImageView mImage = (ImageView) findViewById(R.id.imageView_com_logo);
        mImage.setImageBitmap(ListVar.getCircleBitmap(bm));

        final Spinner spinner_year  = (Spinner) findViewById(R.id.spinner_year);
        ArrayAdapter <CharSequence> adapter_year = ArrayAdapter.createFromResource(this,R.array.year_array,R.layout.support_simple_spinner_dropdown_item);
        adapter_year.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_year.setAdapter(adapter_year);

        final EditText name , description , location_home , location_work  , designation , company , course , institute , twitter , email , phone;

        name= (EditText) findViewById(R.id.editText_name);

        description = (EditText) findViewById(R.id.Edittext_desc);
        location_home = (EditText) findViewById(R.id.ediText_home);
        location_work = (EditText) findViewById(R.id.editText_location6);
        designation = (EditText) findViewById(R.id.editText_designation);
        company = (EditText) findViewById(R.id.editText_company);
        course = (EditText) findViewById(R.id.editText_course);
        institute = (EditText) findViewById(R.id.editText_univ);
        twitter = (EditText) findViewById(R.id.editText_twlink);
        email = (EditText) findViewById(R.id.editText_mail);
        email.setText(bundle.get("EMAIL").toString());
        name.setText(bundle.get("NAME").toString());
        phone = (EditText) findViewById(R.id.editText_contact);




        Button  button = (Button) findViewById(R.id.button_save);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final String sname , sdescription , slocation_home , slocation_work  , sdesignation , scompany
                        , scourse , sinstitute , stwitter , semail , sphone ,syear , sbranch;

                sname = name.getText().toString();
                sdescription = description.getText().toString();
                slocation_home = location_home.getText().toString();
                slocation_work = location_work.getText().toString();
                sdesignation = designation.getText().toString();
                scompany = company.getText().toString();
                scourse = course.getText().toString();
                sinstitute = institute.getText().toString();
                stwitter = twitter.getText().toString();
                semail  = email.getText().toString();
                sphone = phone.getText().toString();
                syear = spinner_year.getSelectedItem().toString();
                sbranch = spinner_branch.getSelectedItem().toString();

                Log.d("GET",sname + sdescription + slocation_home + slocation_work  + sdesignation + scompany
                        + scourse + sinstitute + stwitter + semail + sphone + syear + sbranch);

                if (sname.isEmpty() || sdescription.isEmpty() || slocation_home.isEmpty() ||  slocation_work.isEmpty() || sdesignation.isEmpty() || scompany.isEmpty()
                         || stwitter.isEmpty() || semail.isEmpty() || sphone.isEmpty() || syear.isEmpty() || sbranch.isEmpty() ){
                    Snackbar.make(view, "Sorry! We need Non Empty field", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }

/*
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.150.3:5000/aryaalumni/api/v1.0/signup?",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("RESPONSE", response);
                                if (response.contains("1062")){
                                    Log.d("SAVING","Inside saving e");
                                    Snackbar.make(view, "Sorry! Database already contains this email", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                }
                                if (response.contains("uid") && foo.equals("signup")) {
                                    Log.d("SAVING", "WORK");


                                    try {
                                        JSONObject json= (JSONObject) new JSONToke Intent intent = new Intent(Edit_Profile.this, MainScreen.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_out, R.anim.slide_in);ner(response).nextValue();
                                        JSONArray jsonArray = json.getJSONArray("uid");
                                        uid=jsonArray.get(0).toString();

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
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
                        params.put("name",sname);
                        params.put("email",semail);
                        params.put("password", "PASS");
                        params.put("description", sdescription);
                        params.put("branch", sbranch);
                        params.put("graduation_year", syear);
                        params.put("work_designation", sdesignation);
                        params.put("work_company", scompany);
                        params.put("pg_course", "null");
                        params.put("pg_college", "null");
                        params.put("location_city", slocation_work);
                        params.put("location_work", slocation_home);
                        params.put("contact_twitter", stwitter);
                        params.put("contact_phone", sphone);
                        return params;
                    }

                };

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);

*/

                Intent intent = new Intent(Edit_Profile.this, MainScreen.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out, R.anim.slide_in);

//                if (foo.equals("update")){
//                    Log.d("GET","Updated");
//                    Toast.makeText(getApplicationContext(),"DONE",Toast.LENGTH_SHORT).show();
//                }

                //-----Constraints

            }//end o onClick
        });
    }

}
