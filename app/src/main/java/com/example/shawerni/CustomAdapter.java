package com.example.shawerni;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<Recieved_request> requestList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView sender, msg;

        public MyViewHolder(View view) {
            super(view);
            sender = (TextView) view.findViewById(R.id.sender);
            msg = (TextView) view.findViewById(R.id.text);

        }
    }


    public CustomAdapter(ArrayList<Recieved_request> request , Context context) {

        this.requestList = request;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recived_req, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Recieved_request request = requestList.get(position);
        holder.sender.setText(request.getSender());
        holder.msg.setText(request.getMsg());

    }

    @Override
    public int getItemCount() {
        return requestList.size();

    }

}