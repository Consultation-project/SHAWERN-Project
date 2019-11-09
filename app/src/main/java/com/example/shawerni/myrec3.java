package com.example.shawerni;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myrec3 extends RecyclerView.Adapter<myrec3.ViewHolder> {
    public String st;
    private ArrayList<String> listitem;
    private Context context;

    public myrec3(ArrayList<String> listitem, accept context) {
        this.listitem = listitem;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.conce3, parent, false);
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
                // startActivity(new Intent(myrec2.this,accept.class));


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
            tv = itemView.findViewById(R.id.n55);
            linearLayout = itemView.findViewById(R.id.lin4);
        }
    }


}

