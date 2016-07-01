package com.example.ashish.alumini;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ashish.alumini.Fragments.FragmentMenu;

public class ActivityMember extends AppCompatActivity implements FragmentMenu.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_member);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
