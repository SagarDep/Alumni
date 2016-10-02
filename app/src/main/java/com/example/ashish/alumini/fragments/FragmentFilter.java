package com.example.ashish.alumini.fragments;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.ashish.alumini.members.expandable_list.ExpandableListAdapter;
import com.example.ashish.alumini.R;
import com.example.ashish.alumini.activities.post_login.PostLoginActivity;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 * to handle interaction events.
 * Use the {@link FragmentFilter#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentFilter extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String TAG = getClass().getSimpleName();


    /*
    * Butterknife
    * */
//    @Bind(R.id.button_settings)
//    Button j
    @Bind(R.id.expandableListView)
    ExpandableListView mExpListView;


    android.widget.ExpandableListAdapter listAdapter;

    List<String> listHeader;
    HashMap<String, List<String>> mHashMap;

    Bus mBus = new Bus();

    PostLoginActivity mActivity ;

    public FragmentFilter() {
        // Required empty public constructor
    }


    public static FragmentFilter newInstance(String param1, String param2) {
        FragmentFilter fragment = new FragmentFilter();
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
        View view = inflater.inflate(R.layout.fragment_expandable_list, container, false);

        //getting activity instance
        mActivity = (PostLoginActivity) getActivity();

        // butterknife injections
        ButterKnife.bind(this,view);
        //Bus Registering
//        mBus.register(getActivity());


        mHashMap = new HashMap<>();

        // making list of headers
        listHeader = new ArrayList<>();
        listHeader.add("Branch");
        listHeader.add("Graduation Year");

        // list of branch
        List<String> branch = new ArrayList<>();
        branch.add("CSE");
        branch.add("AE");
        branch.add("ME");
        branch.add("EICE");
        branch.add("IT");
        branch.add("EE");
        branch.add("ECE");

        // list of years
        List<String> year = new ArrayList<>();
        for (int i=2004; i<=2017; i++){
            year.add("" + i);
        }

        //adding the list + header to hashmap
        mHashMap.put(listHeader.get(0), branch);
        mHashMap.put(listHeader.get(1), year);

        listAdapter = new ExpandableListAdapter(getActivity(), listHeader, mHashMap);

        // setting list adapter
        mExpListView.setAdapter(listAdapter);

//         Listview Group click listener
        mExpListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                Log.d(TAG, "Clicked Header " + groupPosition);

                return false;
            }
        });

//         Listview Group expanded listener
        mExpListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

//         Listview Group collasped listener
        mExpListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {


            }
        });

//         Listview on child click listener
        mExpListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                v.setBackgroundColor(Color.BLACK);

                Log.d(TAG, "clicked child" + groupPosition + " " + childPosition + " " +
                        mHashMap.get(listHeader.get(groupPosition)).get(childPosition));

//                TextView textView = (TextView) v;

                mHashMap.get(listHeader.get(groupPosition)).get(childPosition);
                return true;
            }
        });


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onResume() {
        super.onResume();
        mBus.register(getActivity());

    }

    @Override
    public void onPause() {
        super.onPause();
        mBus.unregister(getActivity());

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
