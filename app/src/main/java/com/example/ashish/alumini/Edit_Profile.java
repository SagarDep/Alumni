package com.example.ashish.alumini;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

/**
 * Created by ashish on 8/3/16.
 */
public class Edit_Profile extends Activity {
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


    }

}
