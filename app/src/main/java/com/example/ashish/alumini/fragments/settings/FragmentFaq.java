package com.example.ashish.alumini.fragments.settings;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.example.ashish.alumini.adapters.ExpandableListAdapter;
import com.example.ashish.alumini.R;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class FragmentFaq extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    /*
    * Butterknife
    * */
    @Bind(R.id.button_filter)
    Button mButtonFilter;
    @Bind(R.id.expandableListView)
    ExpandableListView mExpListView;

    android.widget.ExpandableListAdapter mListAdapter;

    List<String> mListHeaders;
    HashMap<String, List<String>> mListChild;


    Bus mBus = new Bus();

    public FragmentFaq() {
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
    public static FragmentFaq newInstance(String param1, String param2) {
        FragmentFaq fragment = new FragmentFaq();
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

        ButterKnife.bind(this,view);
        //Bus Registering
        mBus.register(getActivity());

        mButtonFilter.setVisibility(View.INVISIBLE);

        mListHeaders = new ArrayList<>();
        mListChild = new HashMap<>();
        mListHeaders.add("Why this App?");
        mListHeaders.add("What we want?");
        mListChild = new HashMap<>();
        List<String> branch = new ArrayList<>();
        branch.add(getResources().getString(R.string.answer1));
        List<String> year = new ArrayList<>();
        year.add(getResources().getString(R.string.answer2));
        mListChild.put(mListHeaders.get(0), branch);
        mListChild.put(mListHeaders.get(1), year);
        mListAdapter = new ExpandableListAdapter(getActivity(), mListHeaders, mListChild);

        // setting list adapter

        mExpListView.setAdapter(mListAdapter);




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
    public void onDetach() {
        super.onDetach();

    }

}
