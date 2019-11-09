package com.example.shawerni;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapterm extends RecyclerView.Adapter<adapterm.ViewHolder> {
    public String st, u;
    private ArrayList<String> listitem;
    private Context context;


    public adapterm(ArrayList<String> listitem, free_Consultation context) {
        this.listitem = listitem;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);
        return new ViewHolder(v);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final String rq = listitem.get(position);
        st = rq;
        holder.tv.setText(rq);
        // System.out.println("name  "+rq);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), addfree.class);
                intent.putExtra("Value2", rq);
                context.startActivity(intent);


                Toast.makeText(context, "clicked", Toast.LENGTH_LONG).show();


            }
        });

    }

    @Override
    public int getItemCount() {
        return listitem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout linearLayout;
        TextView tv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.username);
            linearLayout = itemView.findViewById(R.id.line5);
        }
    }


}

