package com.example.shawerni;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class anAdapter extends RecyclerView.Adapter<anAdapter.MyViewHolder> {

    private List<anQ> requestList;
    private Context context;

    public anAdapter(List<anQ> request , Context context) {

        this.requestList = request;
        this.context = context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout relativeLayout;
        public TextView sender, msg;


        public MyViewHolder(View view) {
            super(view);
            sender = (TextView) view.findViewById(R.id.Name2);
            msg = (TextView) view.findViewById(R.id.receivdAn);
            relativeLayout = view.findViewById(R.id.paernt);



        }


    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_answer_row, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        anQ request = requestList.get(position);
        holder.sender.setText(request.getReciver());
        holder.msg.setText(request.getAnswer());




    }

    @Override
    public int getItemCount() {
        return requestList.size();

    }

}