package com.example.ashish.alumini.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ashish.alumini.Job.JobListAdapter;
import com.example.ashish.alumini.Job.JobListInstance;
import com.example.ashish.alumini.R;
import com.example.ashish.alumini.activities.PostLogin.ActivityMember;
import com.squareup.otto.Bus;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentJobs.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentJobs#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentJobs extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @Bind(R.id.listView_jobs)
    ListView mListViewJobs;

    ArrayList<JobListInstance> mArrayList = new ArrayList<>();
    JobListAdapter mListAdapter;

    ActivityMember mActivity;

    Bus mBus = new Bus();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentJobs() {
        // Required empty public constructor
        mArrayList.add(new JobListInstance(null,"Parkzap","Gurgaon","Android Dev","5","12/5/16","Technical"));
        mArrayList.add(new JobListInstance(null,"Parkzap","Gurgaon","Android Dev","5","12/5/16","Technical"));
        mArrayList.add(new JobListInstance(null,"Parkzap","Gurgaon","Android Dev","5","12/5/16","Technical"));
        mArrayList.add(new JobListInstance(null,"Parkzap","Gurgaon","Android Dev","5","12/5/16","Technical"));
        mArrayList.add(new JobListInstance(null,"Parkzap","Gurgaon","Android Dev","5","12/5/16","Technical"));

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentJobs.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentJobs newInstance(String param1, String param2) {
        FragmentJobs fragment = new FragmentJobs();
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

       mListAdapter = new JobListAdapter(getActivity(),R.layout.list_layout_job,mArrayList);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mActivity  = (ActivityMember) getActivity();

        mListViewJobs.setAdapter(mListAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jobs,container,false);

        ButterKnife.bind(this,view);
        
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @OnItemClick(R.id.listView_jobs)
    public void listClickHandler(int position){
        FragmentJobDetails fragmentJobDetails = new FragmentJobDetails();
        fragmentJobDetails.setData(mArrayList.get(position));
        mActivity.changeFragment(fragmentJobDetails);
    }

    @Override
    public void onPause() {
        super.onPause();
        mBus.unregister(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();

        //Bus Registering
        mBus.register(getActivity());
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
