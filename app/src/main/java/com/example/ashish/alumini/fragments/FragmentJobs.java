package com.example.ashish.alumini.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ashish.alumini.Job.JobListAdapter;
import com.example.ashish.alumini.Job.JobListInstance;
import com.example.ashish.alumini.R;
import com.example.ashish.alumini.activities.PostLogin.PostLoginActivity;
import com.squareup.otto.Bus;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;


public class FragmentJobs extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @Bind(R.id.listView_jobs)
    ListView mListViewJobs;

    ArrayList<JobListInstance> mArrayList = new ArrayList<>();
    JobListAdapter mListAdapter;

    PostLoginActivity mActivity;

    Bus mBus = new Bus();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public FragmentJobs() {
        // Required empty public constructor
        mArrayList.add(new JobListInstance(null,"Zillion","Gurgaon","Android Dev","5","12/5/16","Technical"));
        mArrayList.add(new JobListInstance(null,"GenPact","Jaipur","Team Leader","3","12/5/16","Non Technical"));
        mArrayList.add(new JobListInstance(null,"Parkzap","Gurgaon","Web Dev","5","25/8/16","Technical"));
        mArrayList.add(new JobListInstance(null,"Innovaccer","NOIDA","Python Dev","5","12/5/16","Technical"));
        mArrayList.add(new JobListInstance(null,"zillion","Delhi","Analytics","5","18/5/16","Technical"));

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


        mActivity  = (PostLoginActivity) getActivity();

        mListViewJobs.setAdapter(mListAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jobs,container,false);

        ButterKnife.bind(this,view);
        
        return view;
    }



    @OnItemClick(R.id.listView_jobs)
    public void listClickHandler(int position){
        FragmentJobDetails fragmentJobDetails = new FragmentJobDetails();
        fragmentJobDetails.setData(mArrayList.get(position));
        mActivity.changeFragment(fragmentJobDetails);
        mBus.post(8888);

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

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
