package com.example.shawerni;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyReclyecon extends RecyclerView.Adapter<MyReclyecon.ViewHolder> {

    private static final String TAG= "RecycleView";
    private ArrayList<String>  Name = new ArrayList<>();
    private ArrayList<String>  uID = new ArrayList<>();
    private UserInfo userInfo;

    private Context con;
    String x ;
    //private ArrayList<ConModule> cons =new ArrayList<>();

    public MyReclyecon(ArrayList<String> name,ArrayList<String> uid, Context con) {
        Name = name;
        uID = uid;
        this.con = con;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.conce_cy,parent,false);
        ViewHolder holder= new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "OnBinViewHolder:called.");
        //final ConModule cM = cons.get(position);
        final String userName = Name.get(position);
        final String userId = uID.get(position);
        userInfo        = new UserInfo(con);
        holder.NameOfConsultatnt.setText(userName);
        holder.paerntlyout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent (con,ForUser.class);
                intent.putExtra("name",userName);
                intent.putExtra("userId",userId);
                userInfo.setKeyConid(userId);
                con.startActivity(intent);

            }
            });

    }

    @Override
    public int getItemCount() {
        return Name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       TextView NameOfConsultatnt ;
       RelativeLayout paerntlyout ;
        String i;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            NameOfConsultatnt = itemView.findViewById(R.id.Name2);
            paerntlyout = itemView.findViewById(R.id.paernt);
        }
    }

}
