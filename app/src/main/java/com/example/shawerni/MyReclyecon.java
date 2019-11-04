package com.example.shawerni;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyReclyecon extends RecyclerView.Adapter<MyReclyecon.ViewHolder> implements Filterable {

    private static final String TAG = "RecycleView";
    private ArrayList<String> Name = new ArrayList<>();
    private ArrayList<String> uID = new ArrayList<>();
    private ArrayList<String> contactListFiltered;
    private UserInfo userInfo;
    private Context con;
    private ContactsAdapterListener listener;


    public MyReclyecon(ArrayList<String> name, ArrayList<String> uid, Context con) {
        Name = name;
        contactListFiltered = name;
        uID = uid;
        this.con = con;
       // this.listener = listener;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.conce_cy, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "OnBinViewHolder:called.");
        //final ConModule cM = cons.get(position);
        final String userName = Name.get(position);
        final String userId = uID.get(position);
        userInfo = new UserInfo(con);
        holder.NameOfConsultatnt.setText(userName);
        holder.paerntlyout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(con, ForUser.class);
                intent.putExtra("name", userName);
                intent.putExtra("userId", userId);
                userInfo.setKeyConid(userId);
                con.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return Name.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                if (charSequence == null || charSequence.length() == 0) {
                    contactListFiltered.addAll(Name);
                    contactListFiltered = Name;
                } else {
                    String filterPattern = charSequence.toString().toLowerCase().trim();
                    for (String row : Name) {

                        if (row.toLowerCase().contains(filterPattern)) {
                            contactListFiltered.add(row);
                        }//end if
                    }//end loop


                }//end else

                FilterResults filterResults = new FilterResults();
                filterResults.values = contactListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                Name.clear();
                contactListFiltered = (ArrayList<String>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ContactsAdapterListener {
        void onContactSelected(String contact);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView NameOfConsultatnt;
        RelativeLayout paerntlyout;
        String i;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            NameOfConsultatnt = itemView.findViewById(R.id.Name2);
            paerntlyout = itemView.findViewById(R.id.paernt);
        }
    }


}