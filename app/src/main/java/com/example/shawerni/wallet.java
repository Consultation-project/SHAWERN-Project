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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;




public class wallet extends Fragment  {

    private static final String TAG = "Activity";

    private ArrayList<String> nName = new ArrayList<>();
    private ArrayList<ConModule> con1 = new ArrayList<>();



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.payconsultationlist);

        Log.d(TAG, "onCreate");

        //  Ename();

        int counter = 1 ;

        nName.add ("Consultation " + counter );

    }
   /* private void Ename() {



        for (int i = 1  ; i < 3 ; i++){


            counter++;
            inRecycle ();

        }
           */

        /* database= FirebaseDatabase.getInstance();
        retreff=database.getReference("Consultant Request");

        retreff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot :dataSnapshot.getChildren()){
                    PayConfirm Nm = snapshot.getValue(PayConfirm.class) ;
                    String name = Nm.getName();

                    nName.add(name);
                    inRecycle ();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

          */

    //}

    private void inRecycle (){
        RecyclerView recyclerView= findViewById(R.id.recycler_view);
        MyReclyecon myr = new MyReclyecon (nName,this);
        recyclerView.setAdapter(myr);
        recyclerView.setLayoutManager(new LinearLayoutManager (this));







    }
}
