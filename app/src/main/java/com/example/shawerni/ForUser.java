package com.example.shawerni;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ForUser extends AppCompatActivity {
    TextView e1;
    TextView e2;
    View view;
    String name1 ;
    String mejore;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference df ;
    String i;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profiletouser);
        e1 = findViewById(R.id.Des);
        e2 = findViewById(R.id.meger);
        ConModule conModule= new ConModule();
        FirebaseDatabase database;
        DatabaseReference retreff ;


        name1 = getIntent().getStringExtra("name");

        e1.setText(name1);
        database= FirebaseDatabase.getInstance();
        retreff=database.getReference("Consultant Request");

        retreff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ConModule Nm = snapshot.getValue(ConModule.class);
                    String name = Nm.getName();

                    if(name1.equals(name)){
                        e2.setText(Nm.getMajor());

                    }


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });}}