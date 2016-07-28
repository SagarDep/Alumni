package com.example.ashish.alumini.Job;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ashish.alumini.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ashish on 11/3/16.
 */
public class JobListAdapter extends ArrayAdapter<JobListInstance> {
    List<JobListInstance> mListJobs;

    @Bind(R.id.imageView_logo)
    ImageView mImageView;
    @Bind(R.id.textView_companyName) TextView mTextViewCompanyName;
    @Bind(R.id.textView_joblocation) TextView mTextViewJobLocation;
    @Bind(R.id.textView_jobPosition) TextView mTextViewJobPosition;
    @Bind(R.id.textView_jobType) TextView mTextViewJobType;
    @Bind(R.id.textView_lastDate) TextView mTextViewLastDate;



    public JobListAdapter(Context context, int resource, List<JobListInstance> objects) {
        super(context, resource, objects);
        mListJobs =objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            convertView = vi.inflate(R.layout.list_layout_job, null);
        }
        //Butterknife Injections
        ButterKnife.bind(this,convertView);

        JobListInstance item = getItem(position);
//
//
//        Holder holder = new Holder();
//        holder.tv_name = (TextView) convertView.findViewById(R.id.textView_companyName);
//        holder.tv_location = (TextView) convertView.findViewById(R.id.textView_joblocation);
//        holder.imageView_profilePic = (ImageView) convertView.findViewById(R.id.imageView_logo);
//
////        holder.imageView_profilePic.setImageBitmap(MemberListInstance.getCircleBitmap(temp.logo));
//        holder.tv_name.setText("Dummy Name");
//        holder.tv_location.setText("Bharat");
        mTextViewCompanyName.setText(item.getCompanyName());
        mTextViewJobLocation.setText(item.getJobLocation());
        mTextViewJobPosition.setText(item.getJobPost());
        mTextViewJobType.setText(item.getJobType());
        mTextViewLastDate.setText(item.getLastDate());


        return convertView;
    }

    @Override
    public JobListInstance getItem(int position) {
        return mListJobs.get(position);
    }

}
