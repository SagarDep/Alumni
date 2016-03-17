package com.example.ashish.alumini;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;

public class About_college extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    TextView CollegDescription;
    SliderLayout mDemoSlider ;
    ScrollView mainScroll;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_college);
        mainScroll=(ScrollView)findViewById(R.id.scroll);
        CollegDescription = (TextView) findViewById(R.id.college_desc);
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);

        String text = "ARYA College of Engineering & I.T (ACEIT),Kukas,Jaipur Established in Year 2000 is among the foremost of institutes of national significance in higher technical education and AICTE,New Delhi Approved and Affiliated with Rajasthan Technical University,Kota in Rajasthan.It is commonly Known as “ARYA OLD MAIN CAMPUS” and “ARYA 1st”.";
        CollegDescription.setText(text);

        /*HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");
*/

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Placed Student",R.drawable.about1);
        file_maps.put("Arya Old Main Campus",R.drawable.about2);
        file_maps.put("Inside View",R.drawable.about3);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Stack);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
    }

   public void wayToCollege(View v)
   {
       Uri uri = Uri.parse("http://www.aryacollege.in/");
       Intent intent = new Intent(Intent.ACTION_VIEW, uri);
       startActivity(intent);


   }



    @Override
    public void onSliderClick(BaseSliderView slider) {

        Log.d("Slider Demo", slider.getBundle().get("extra") + "");
        Snackbar snackbar = Snackbar
                .make(mainScroll, "Slide to view more image", Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }


    @Override
    protected void onStop() {
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
