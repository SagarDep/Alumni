package com.example.ashish.alumini.deepak;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ashish.alumini.deepak.events.AryaCup;
import com.example.ashish.alumini.deepak.events.AryaRatan;
import com.example.ashish.alumini.deepak.events.Auto;
import com.example.ashish.alumini.deepak.events.Confernce;
import com.example.ashish.alumini.deepak.events.Engineer;
import com.example.ashish.alumini.deepak.events.Eupo;
import com.example.ashish.alumini.deepak.events.Exergie;
import com.example.ashish.alumini.deepak.events.Shrandhan;
import com.example.ashish.alumini.deepak.events.Social;
import com.example.ashish.alumini.deepak.events.Technica;
import com.example.ashish.alumini.deepak.events.TopGun;
import com.example.ashish.alumini.deepak.events.Victory;
import com.example.ashish.alumini.deepak.events.Zephr;
import com.example.ashish.alumini.deepak.events.microsoft;
import com.example.ashish.alumini.deepak.events.sanghosti;
import com.example.ashish.alumini.R;

import java.util.ArrayList;
import java.util.List;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    List<com.example.ashish.alumini.deepak.MethodClass> mItems;

    public CardAdapter() {
        super();
        mItems = new ArrayList<>();
        com.example.ashish.alumini.deepak.MethodClass nature = new com.example.ashish.alumini.deepak.MethodClass();
        nature.setName("Tehnica Naitus");
        nature.setDes("Tehnica Naitus is a National Level Technical Project Competition which is organized every year in the campus.");
        nature.setThumbnail(R.drawable.tehnika1);
        mItems.add(nature);

        nature = new MethodClass();
        nature.setName("Shradhanjali");
        nature.setDes("It is a tribute to the founder chairman Late Shri T.K. Agarwal Ji and it's include various events.");
        nature.setThumbnail(R.drawable.shradhn);
        mItems.add(nature);

        nature = new MethodClass();
        nature.setName("Arya Ratan");
        nature.setThumbnail(R.drawable.arya_ratan);
        mItems.add(nature);

        nature = new MethodClass();
        nature.setName("Euphonious");
        nature.setThumbnail(R.drawable.eupo);
        mItems.add(nature);


        nature = new MethodClass();
        nature.setName("Arya Cup");
       nature.setThumbnail(R.drawable.aryacup_main);
        mItems.add(nature);


        nature = new MethodClass();
        nature.setName("Victory");
        nature.setThumbnail(R.drawable.victory);
        mItems.add(nature);


        nature = new MethodClass();
        nature.setName("Exergie");
       nature.setThumbnail(R.drawable.exergie);
        mItems.add(nature);


        nature = new MethodClass();
        nature.setName("Social Event");
        nature.setThumbnail(R.drawable.social_event);
        mItems.add(nature);


        nature = new MethodClass();
        nature.setName("Top Gun");
        nature.setThumbnail(R.drawable.top_gun);
        mItems.add(nature);

        nature = new MethodClass();
        nature.setName("Autoignition");
       nature.setThumbnail(R.drawable.autoignition1);
        mItems.add(nature);

        nature = new MethodClass();
        nature.setName("Zephyr");
       nature.setThumbnail(R.drawable.zep1);
        mItems.add(nature);

        nature = new MethodClass();
        nature.setName("Engineer Day");
        nature.setThumbnail(R.drawable.engineer);
        mItems.add(nature);

        nature = new MethodClass();
        nature.setName("2ND International Conference");
        nature.setThumbnail(R.drawable.confer);
        mItems.add(nature);

        nature = new MethodClass();
        nature.setName("National Sanghosti");
        nature.setThumbnail(R.drawable.national_sanghosti);
        mItems.add(nature);

        nature = new MethodClass();
        nature.setName("Microsoft Innvation Center");
        nature.setThumbnail(R.drawable.micro_eduvantage);
        mItems.add(nature);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.activity_card_adapter, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(context,contactView);
        return viewHolder;
    }


    /*
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_card_adapter, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    */

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        MethodClass nature = mItems.get(i);
        viewHolder.title.setText(nature.getName());
        viewHolder.imgThumbnail.setImageResource(nature.getThumbnail());


        //***************8
        viewHolder.setClickListener(new ViewHolder.ClickListener() {
            @Override
            public void onClick(View v, int pos, boolean isLongClick) {


                if (isLongClick) {



                } else {
                    // Toast.makeText(this,"", Toast.LENGTH_SHORT).show(); // View v at position pos is clicked.

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public de.hdodenhof.circleimageview.CircleImageView imgThumbnail;
        public TextView title;
        private Context context;


        private ClickListener clickListener;

        public ViewHolder(Context context,View itemView) {
            super(itemView);
            imgThumbnail = (de.hdodenhof.circleimageview.CircleImageView)itemView.findViewById(R.id.img_thumbnail);
            title = (TextView) itemView.findViewById(R.id.event_name);

            this.context = context;

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }


        public interface ClickListener {


            public void onClick(View v, int position, boolean isLongClick);

        }

        /* Setter for listener. */
        public void setClickListener(ClickListener clickListener) {
            this.clickListener = clickListener;
        }

        @Override
        public void onClick(View v) {

            // If not long clicked, pass last variable as false.
            clickListener.onClick(v, getPosition(), false);

            int position = getLayoutPosition(); // gets item position
            //User user = users.get(position);
            // We can access the data within the views
            //Toast.makeText(context, title.getText(), Toast.LENGTH_SHORT).show();


            if(position==0)
            {
                Intent event=new Intent(context,Technica.class);
                context.startActivity(event);

            }

            if(position==1)
            {
                Intent event1=new Intent(context,Shrandhan.class);
                context.startActivity(event1);

            }

            if(position==2)
            {
                Intent event2=new Intent(context,AryaRatan.class);
                context.startActivity(event2);
            }

            if(position==3)
            {
                Intent event3=new Intent(context,Eupo.class);
                context.startActivity(event3);
            }

            if(position==4)
            {
                Intent event4=new Intent(context,AryaCup.class);
                context.startActivity(event4);
            }


            if(position==5)
            {
                Intent event5=new Intent(context,Victory.class);
                context.startActivity(event5);
            }


            if(position==6)
            {
                Intent event6=new Intent(context,Exergie.class);
                context.startActivity(event6);
            }


            if(position==7)
            {
                Intent event7=new Intent(context,Social.class);
                context.startActivity(event7);
            }

            if(position==8)
            {
                Intent event8=new Intent(context,TopGun.class);
                context.startActivity(event8);
            }


            if(position==9)
            {
                Intent event9=new Intent(context,Auto.class);
                context.startActivity(event9);
            }


            if(position==10)
            {
                Intent event10=new Intent(context,Zephr.class);
                context.startActivity(event10);
            }

            if(position==11)
            {
                Intent event11=new Intent(context,Engineer.class);
                context.startActivity(event11);
            }

            if(position==12)
            {
                Intent event12=new Intent(context,Confernce.class);
                context.startActivity(event12);
            }

            if(position==13)
            {
                Intent event13=new Intent(context,sanghosti.class);
                context.startActivity(event13);
            }

            if(position==14)
            {
                Intent event14=new Intent(context,microsoft.class);
                context.startActivity(event14);
            }



        }

        @Override
        public boolean onLongClick(View v) {

            // If long clicked, passed last variable as true.
            clickListener.onClick(v, getPosition(), true);
            return true;
        }
    }


}
