package com.example.ashish.alumini;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ashish on 4/3/16.
 */
public class profile extends Activity {
    private TextView tw_twitter, mail , phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.afterlistclick);
        Bitmap bm = BitmapFactory.decodeResource(getResources(),R.drawable.image);
        ImageView mImage = (ImageView) findViewById(R.id.imageView_profilepic);
        mImage.setImageBitmap(ListVar.getCircleBitmap(bm));

        TextView textView = (TextView) findViewById(R.id.textView_year);
        ImageButton edit_image = (ImageButton) findViewById(R.id.edit_image);

        edit_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, Edit_Profile.class);
                startActivity(intent);
            }
        });

        phone = (TextView) findViewById(R.id.editText_contact);
        tw_twitter = (TextView) findViewById(R.id.editText_twlink);
        mail = (TextView) findViewById(R.id.editText_mail);
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, mail.getText());
                startActivity(Intent.createChooser(intent, ""));

            }
        });
        tw_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(tw_twitter.getText().toString());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + phone.getText()));
                startActivity(callIntent);
            }
        });
    }


}
