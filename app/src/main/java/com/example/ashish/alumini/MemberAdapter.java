package com.example.ashish.alumini;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ashish on 3/3/16.
 */
public class MemberAdapter extends ArrayAdapter<ListVar>{
    List<ListVar> List_members;
    public MemberAdapter(Context context,int rid, List<ListVar> list){
        super(context,rid,list);
        List_members=list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListVar  temp = getItem(position);

        if (convertView == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            convertView = vi.inflate(R.layout.simple_list_item, null);
        }

        Holder holder = new Holder();
        holder.tv_name = (TextView) convertView.findViewById(R.id.textView_name);
        holder.tv_designation = (TextView) convertView.findViewById(R.id.textView_designation);
        holder.tv_location = (TextView) convertView.findViewById(R.id.textView_location);
        holder.tv_passing_year = (TextView) convertView.findViewById(R.id.textView_passing_year);
        holder.imageView_profilePic= (ImageView) convertView.findViewById(R.id.profileimage);
        // -----------------------PROFILE PIC-------------------

//        holder.imageView_profilePic.setImageBitmap(ListVar.getCircleBitmap(temp.bitmap));

        holder.tv_name.setText(temp.name);
        holder.tv_designation.setText(temp.designation + " at " + temp.company);
        holder.tv_passing_year.setText(temp.year_passing + " " + temp.branch);
        holder.tv_location.setText(temp.location_work);

        return convertView;
    }
    @Override
    public ListVar getItem(int position) {
        return List_members.get(position);
    }
    public static class Holder{
        public TextView tv_name ;
        public TextView tv_designation ;
        public TextView tv_location ;
        public TextView tv_passing_year ;
        public ImageView imageView_profilePic;

    }

}
