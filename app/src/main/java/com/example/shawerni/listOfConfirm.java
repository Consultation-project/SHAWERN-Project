package com.example.shawerni;

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

public class listOfConfirm extends RecyclerView.Adapter<listOfConfirm.ViewHolder> {

    private static final String TAG= "RecycleView";
    private ArrayList<String> NameOfUser = new ArrayList<>();
    private ArrayList<String>  uID = new ArrayList<>();
    private UserInfo userInfo;
    private Context context;


    public listOfConfirm(ArrayList<String> nameOfUser,ArrayList<String> uid, Context con) {
        NameOfUser = nameOfUser;
        uID = uid;
        this.context = con;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listofconfirm,parent,false);
        ViewHolder holder= new ViewHolder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "OnBinViewHolder:called.");

        final String userName = NameOfUser.get(position);
        final String userId = uID.get(position);
        userInfo  = new UserInfo(context);
        holder.NameOfUser.setText(userName);
        holder.paerntlyout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intent = new Intent (context, forConfirm.class);
                intent.putExtra("name",userName);
                intent.putExtra("userId",userId);
                userInfo.setKeyConid(userId);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return NameOfUser.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView NameOfUser ;
        RelativeLayout paerntlyout ;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            NameOfUser = itemView.findViewById(R.id.NameOfUSER);
            paerntlyout = itemView.findViewById(R.id.paernt);
        }
    }

}