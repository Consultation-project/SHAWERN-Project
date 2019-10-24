package com.example.shawerni;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ConsultantNewVersion extends AppCompatActivity {
    private static final String TAG = "Activity";

    private ArrayList<String> nName = new ArrayList<>();
    private ArrayList<ConModule> con1 = new ArrayList<>();
    ConModule n =new ConModule();
    FirebaseDatabase database;
    DatabaseReference retreff ;
    String i;
    String X;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.concrecycle);
        Log.d(TAG, "onCreate");
        Ename();


    }
    private void Ename() {
        database= FirebaseDatabase.getInstance();
        retreff=database.getReference("Consultant Request");

        retreff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot :dataSnapshot.getChildren()){
                    ConModule Nm = snapshot.getValue(ConModule.class) ;
                    String name = Nm.getName();
                    nName.add(name);
                    inRecycle ();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void inRecycle (){
        RecyclerView recyclerView= findViewById(R.id.recycler_view);
        MyReclyecon myr = new MyReclyecon(nName,this);
        recyclerView.setAdapter(myr);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));







    }

}
