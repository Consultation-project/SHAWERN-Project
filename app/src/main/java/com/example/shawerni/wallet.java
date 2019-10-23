package com.example.shawerni;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;




public class wallet extends Fragment {

    private static final String TAG = "RecycleView";
    private ArrayList<String>  Consultation = new ArrayList<>();
    private Context con;
    //private ArrayList<ConModule> cons =new ArrayList<>();

    public wallet(ArrayList<String> consultation , Context con) {
        Consultation = consultation;
        this.con = con;

    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.walletfrag,parent,false);
        ViewHolder holder= new ViewHolder (view);
        return holder;

    }


    public void onBindViewHolder(@NonNull MyReclyecon.ViewHolder holder, int position) {

        Log.d(TAG, "OnBinViewHolder:called.");
        //final ConModule cM = cons.get(position);
        final String userName = Consultation.get(position);
        holder.NameOfConsultatnt.setText(userName);
        holder.paerntlyout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent (con,payment_information.class);
                intent.putExtra("name",userName);
                con.startActivity(intent);

            }
        });

    }

    public int getItemCount() {
        return Consultation.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView NameOfConsultatnt ;
        RelativeLayout paerntlyout ;
        String i;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            NameOfConsultatnt = itemView.findViewById(R.id.consultation);
            paerntlyout = itemView.findViewById(R.id.paernt);
        }
    }
}
