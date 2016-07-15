package com.example.ashish.alumini.Fragments.settings;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.example.ashish.alumini.ListMembers.ExpandableList.ExpandableListAdapter;
import com.example.ashish.alumini.R;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentFaq.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentFaq#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentFaq extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

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
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
