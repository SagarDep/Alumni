package com.example.ashish.alumini.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ashish.alumini.EventListAdapter;
import com.example.ashish.alumini.R;
import com.example.ashish.alumini.activities.PostLogin.ActivityMainScreen;
import com.example.ashish.alumini.deepak.events.EventListInstance;
import com.example.ashish.alumini.fragments.common_fragments.FragmentWebView;
import com.squareup.otto.Bus;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;


public class FragmentEvents extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private String TAG = getClass().getSimpleName();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /*
    * Butterknife
    * */
//    @Bind(R.id.button_settings)
//    Button j;
    @Bind(R.id.listView_events)
    ListView mListView;

    ArrayList<EventListInstance> mInstanceArrayList = new ArrayList<>();

    Bus mBus = new Bus() ;

    ActivityMainScreen mActivity ;

    ActionBar mActionBar;

    public FragmentEvents() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentEvents newInstance(String param1, String param2) {
        FragmentEvents fragment = new FragmentEvents();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events, container, false);

        mActivity = (ActivityMainScreen) getActivity();

        ButterKnife.bind(this,view);
        //Bus Registering
        mBus.register(getActivity());

        mActionBar = mActivity.getSupportActionBar();

        EventListInstance listInstance = new EventListInstance();
        listInstance.setEventName("Tehnika");
        listInstance.setWebViewLink("http://google.com");
        mInstanceArrayList.add(listInstance);

        listInstance = new EventListInstance();
        listInstance.setEventName("Microsoft Innovation Center");
        listInstance.setWebViewLink("http://google.com");
        mInstanceArrayList.add(listInstance);

        listInstance = new EventListInstance();
        listInstance.setEventName("Sanghosti");
        listInstance.setWebViewLink("http://google.com");
        mInstanceArrayList.add(listInstance);

        listInstance = new EventListInstance();
        listInstance.setEventName("International Conference");
        listInstance.setWebViewLink("http://google.com");
        mInstanceArrayList.add(listInstance);

        listInstance = new EventListInstance();
        listInstance.setEventName("Engineer Day");
        listInstance.setWebViewLink("http://google.com");
        mInstanceArrayList.add(listInstance);

        listInstance = new EventListInstance();
        listInstance.setEventName("Zephr");
        listInstance.setWebViewLink("http://google.com");
        mInstanceArrayList.add(listInstance);


        listInstance = new EventListInstance();
        listInstance.setEventName("Auto Ignition");
        listInstance.setWebViewLink("http://google.com");
        mInstanceArrayList.add(listInstance);

        listInstance = new EventListInstance();
        listInstance.setEventName("TopGun");
        listInstance.setWebViewLink("http://google.com");
        mInstanceArrayList.add(listInstance);

        listInstance = new EventListInstance();
        listInstance.setEventName("Exergie");
        listInstance.setWebViewLink("http://google.com");
        mInstanceArrayList.add(listInstance);



        EventListAdapter adapter = new EventListAdapter(getActivity(),R.layout.list_layout_events,mInstanceArrayList);
        mListView.setDivider(null);
        mListView.setAdapter(adapter);

        //ActionBar operations
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle("Events");
        mActionBar.show();

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onResume() {
        super.onResume();
        mBus.unregister(getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @OnItemClick(R.id.listView_events)
    public void listClickListener(int position){
        // getting the web link of event
        String url = mInstanceArrayList.get(position).getWebViewLink();
        mActivity.changeFragment(new FragmentWebView().newInstance(url,""));
    }

}
