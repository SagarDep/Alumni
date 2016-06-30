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

/**
 * Created by ashish on 11/3/16.
 */
public class JobListAdapter extends ArrayAdapter<JobListVar> {
    List<JobListVar> listofcoms;
    public JobListAdapter(Context context, int resource, List<JobListVar> objects) {
        super(context, resource, objects);
        listofcoms=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        JobListVar temp  = getItem(position);
        if (convertView == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            convertView = vi.inflate(R.layout.simple_list_item_job, null);
        }
        Holder holder = new Holder();
        holder.tv_name = (TextView) convertView.findViewById(R.id.textView2);
        holder.tv_location = (TextView) convertView.findViewById(R.id.textView_joblocation);
        holder.imageView_profilePic = (ImageView) convertView.findViewById(R.id.imageView_job_profile);

//        holder.imageView_profilePic.setImageBitmap(ListVar.getCircleBitmap(temp.logo));
        holder.tv_name.setText("Dummy Name");
        holder.tv_location.setText("Bharat");
        return convertView;
    }

    @Override
    public JobListVar getItem(int position) {
        return listofcoms.get(position);
    }
    private static class Holder{
        public TextView tv_name ;
        public TextView tv_designation ;
        public TextView tv_location ;
        public TextView tv_passing_year ;
        public ImageView imageView_profilePic;

    }
}
