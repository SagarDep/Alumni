package com.example.ashish.alumini;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.ashish.alumini.supporting_classes.EventListInstance;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ashish on 11/3/16.
 */
public class EventListAdapter extends ArrayAdapter<EventListInstance> {
    List<EventListInstance> mListEvents;

//    @Bind(R.id.imageView_logo)
//    ImageView mImageView;
    @Bind(R.id.TextView_eventName) TextView mTextViewEventName;



    public EventListAdapter(Context context, int resource, List<EventListInstance> objects) {
        super(context, resource, objects);
        mListEvents =objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            convertView = vi.inflate(R.layout.list_layout_events, null);
        }
        //Butterknife Injections
        ButterKnife.bind(this,convertView);

        EventListInstance item = getItem(position);

        mTextViewEventName.setText(item.getName());


        return convertView;
    }

    @Override
    public EventListInstance getItem(int position) {
        return mListEvents.get(position);
    }

}
