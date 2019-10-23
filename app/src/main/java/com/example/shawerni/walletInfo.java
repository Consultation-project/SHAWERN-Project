package com.example.shawerni;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class walletInfo extends AppCompatActivity{

    private static final String TAG = "Activity";

    private ArrayList<String> nName = new ArrayList<>();
    private ArrayList<ConModule> con1 = new ArrayList<>();
    ConModule n =new ConModule ();
    FirebaseDatabase database;
    DatabaseReference retreff ;
    String i;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payconsultationlist);
        Log.d(TAG, "onCreate");
        Ename();


    }
    private void Ename() {

        int counter = 1 ;

        for (int i = 1  ; i < 3 ; i++){

            nName.add ("Consultation " + counter );
            counter++;
            inRecycle ();
                   
        }


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

    }

    private void inRecycle (){
        RecyclerView recyclerView= findViewById(R.id.recycler_view);
        MyReclyecon myr = new MyReclyecon (nName,this);
        recyclerView.setAdapter(myr);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));







    }

}
