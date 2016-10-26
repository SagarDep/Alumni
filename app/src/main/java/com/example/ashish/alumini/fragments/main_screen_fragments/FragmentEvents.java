package com.example.ashish.alumini.fragments.main_screen_fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ashish.alumini.adapters.EventListAdapter;
import com.example.ashish.alumini.R;
import com.example.ashish.alumini.activities.post_login.MainScreenActivity;
import com.example.ashish.alumini.fragments.FragmentWebView;
import com.example.ashish.alumini.supporting_classes.EventListInstance;
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

    MainScreenActivity mActivity ;

    ActionBar mActionBar;

    public FragmentEvents() {
        // Required empty public constructor
    }


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

        mActivity = (MainScreenActivity) getActivity();

        ButterKnife.bind(this,view);

        mActionBar = mActivity.getSupportActionBar();

        EventListInstance listInstance = new EventListInstance();
        listInstance.setName("Tehnika Naitus");
        listInstance.setUrl("http://www.aryacollege.in/tehnika-naitus.php");
        mInstanceArrayList.add(listInstance);

        listInstance = new EventListInstance();
        listInstance.setName("Shraddhanjali");
        listInstance.setUrl("http://www.aryacollege.in/shradhanjali.php");
        mInstanceArrayList.add(listInstance);

        listInstance = new EventListInstance();
        listInstance.setName("Graduation Day");
        listInstance.setUrl("http://www.aryacollege.in/graduation-day.php");
        mInstanceArrayList.add(listInstance);

        listInstance = new EventListInstance();
        listInstance.setName("Zephr");
        listInstance.setUrl("http://www.aryacollege.in/zephyr.php");
        mInstanceArrayList.add(listInstance);

        listInstance = new EventListInstance();
        listInstance.setName("Victory");
        listInstance.setUrl("http://www.aryacollege.in/the-annualday.php");
        mInstanceArrayList.add(listInstance);

        listInstance = new EventListInstance();
        listInstance.setName("Engineer Day");
        listInstance.setUrl("http://www.aryacollege.in/engineer-day.php");
        mInstanceArrayList.add(listInstance);


        listInstance = new EventListInstance();
        listInstance.setName("Auto Ignition");
        listInstance.setUrl("http://www.aryacollege.in/autoignition.php");
        mInstanceArrayList.add(listInstance);

        listInstance = new EventListInstance();
        listInstance.setName("TopGuns");
        listInstance.setUrl("http://www.aryacollege.in/the-farewellparty.php");
        mInstanceArrayList.add(listInstance);

        listInstance = new EventListInstance();
        listInstance.setName("Exergie");
        listInstance.setUrl("http://www.aryacollege.in/exergie.php");
        mInstanceArrayList.add(listInstance);



        EventListAdapter adapter = new EventListAdapter(getActivity(),
                R.layout.list_layout_events,mInstanceArrayList);
        mListView.setDivider(null);
        mListView.setAdapter(adapter);


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onResume() {
        super.onResume();

        //Bus Registering
        mBus.register(getActivity());

        mBus.post(false);

        //ActionBar operations
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle("Events");
        mActionBar.show();

    }

    @Override
    public void onPause() {
        super.onPause();

        mBus.unregister(getActivity());

        mActionBar.hide();
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @OnItemClick(R.id.listView_events)
    public void listClickListener(int position){
        // getting the web link of event
        String url = mInstanceArrayList.get(position).getUrl();
        mActivity.changeFragment(new FragmentWebView().newInstance(url,""));
    }

}
