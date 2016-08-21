package com.example.ashish.alumini.fragments.settings;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ashish.alumini.ListMembers.MemberListInstance;
import com.example.ashish.alumini.activities.PostLogin.PostLoginActivity;
import com.example.ashish.alumini.fragments.common_fragments.FragmentGetProfileData;
import com.example.ashish.alumini.R;
import com.squareup.otto.Bus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FragmentProfile extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    MemberListInstance mListInstance;

    /*
    * Butterknife
    * */
    @Bind(R.id.imageView_edit)
    ImageView mImageViewEdit;
    @Bind(R.id.textView_member_name)
    TextView mTextView_name;

    Bus mBus = new Bus();

    PostLoginActivity mActivity ;

    public FragmentProfile() {
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
    public static FragmentProfile newInstance(String param1, String param2) {
        FragmentProfile fragment = new FragmentProfile();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // butterknife binding
        ButterKnife.bind(this,view);

        //Bus Registering
        mBus.register(getActivity());

        // getting activity instance
        mActivity = (PostLoginActivity) getActivity();

        // hiding the edit button
        mImageViewEdit.setVisibility(View.GONE);

        mTextView_name.setText(mListInstance.getName());


        return view;
    }

    @OnClick(R.id.imageView_edit)
    public void editProfile(){
        mActivity.changeFragment(new FragmentGetProfileData().newInstance("edit",""));
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public void setData(MemberListInstance data){

        mListInstance = data;
        makeServerCallToGetMoreData();

    }
    public void makeServerCallToGetMoreData(){
        // TODo : retrofit server call
    }


}
