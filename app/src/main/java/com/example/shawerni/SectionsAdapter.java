package com.example.shawerni;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class SectionsAdapter extends RecyclerView.Adapter<SectionsAdapter.MyViewHolder>{

    private Context mContext;
    private List<section> sectionList;




    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView image;
        public RelativeLayout Single_card;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            image = (ImageView) view.findViewById(R.id.thumbnail);
            Single_card = view.findViewById(R.id.single_card);


        }
    }

    public SectionsAdapter (Context mContext, List<section> albumList ) {
        this.mContext = mContext;
        this.sectionList = albumList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final  section dep = sectionList.get(position);
        holder.title.setText(dep.getName());
        holder.image.setImageResource(dep.getImg());

        // loading album cover using Glide library
        Glide.with(mContext).load(dep.getImg()).into(holder.image);

        holder.Single_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //move to survey pages
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */



    @Override
    public int getItemCount() {
        return sectionList.size();
    }



}



