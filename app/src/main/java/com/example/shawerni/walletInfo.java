package com.example.shawerni;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class walletInfo extends RecyclerView.Adapter<walletInfo.ViewHolder> {

    private static final String TAG = "RecycleView";
    private ArrayList<String>  Consultation = new ArrayList<>();

    private Context con;

    //private ArrayList<ConModule> cons =new ArrayList<>();

    public walletInfo(ArrayList<String> consultation , Context con) {
        Consultation = consultation;
        this.con = con;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.walletfrag,parent,false);
        walletInfo.ViewHolder holder= new walletInfo.ViewHolder (view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Log.d(TAG, "OnBinViewHolder:called.");
        //final ConModule cM = cons.get(position);
        final String userName = Consultation.get(position);
        holder.consultation.setText(userName);
        holder.paerntlyout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent (con,payment_information.class);
                //  intent.putExtra("name",userName);
                con.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return Consultation.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView consultation ;
        RelativeLayout paerntlyout ;
        String i;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            consultation = itemView.findViewById(R.id.consultation);
            paerntlyout = itemView.findViewById(R.id.paernt);
        }
    }

}
