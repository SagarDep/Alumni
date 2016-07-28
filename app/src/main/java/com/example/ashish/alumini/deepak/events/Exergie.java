package com.example.ashish.alumini.deepak.events;

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
import com.example.ashish.alumini.R;

import java.util.HashMap;

public class Exergie extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SliderLayout mDemoSlider ;
    ScrollView mainScroll;
    TextView Description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exergie);
       // getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("e53935")));
        mainScroll=(ScrollView)findViewById(R.id.scroll);
        Description = (TextView) findViewById(R.id.desc);
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);

        //putting text in dscrption
        String text = "EXERGIE is a national level technical carnival organized by Computer Science and I.T. Department.It is focused on the invocation of a new methodology for the development of sprit of competition at technical level and also enhances the abilities of the students to improve their concentration and speed. Various events like Blind Coding, Apps World, Enforcer, Maneuver, Golden Goal etc.";
        Description.setText(text);

        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("EXERGIE", R.drawable.exergie);
        file_maps.put("EXERGIE", R.drawable.eupo);



        file_maps.put("Technica Naitus", R.drawable.tehnika1);
        file_maps.put("Arya Old Main Campus", R.drawable.tenika_1);


        for (String name : file_maps.keySet()) {
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
                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Stack);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
    }


    public void wayToEvent(View v)
    {
        Uri uri = Uri.parse("http://www.aryacollege.in/exergie.php");
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
