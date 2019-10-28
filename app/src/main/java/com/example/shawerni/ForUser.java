package com.example.shawerni;

import android.content.Intent;
import android.os.Bundle;
import android.view.FocusFinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
    String uId ;
    private UserInfo userInfo;

    String mejore;
    Button reserbtn;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference df ;
    String i;
    String X ;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profiletouser);
        e1 = findViewById(R.id.Des);
        e2 = findViewById(R.id.meger);
        ConModule conModule= new ConModule();
        FirebaseDatabase database;
        DatabaseReference retreff ;
        name1 = getIntent().getStringExtra("name");
        uId=getIntent().getStringExtra("userId");
        userInfo        = new UserInfo(this);

        reserbtn=findViewById(R.id.reservebtn);
        reserbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ForUser.this, selection_page.class);
                startActivity(i);
                Intent intent = new Intent (ForUser.this,consultant_appo.class);

                intent.putExtra("userId",uId);

              //  getSupportFragmentManager().beginTransaction().replace(R.id.container,new selection_page()).commit();
              //  setTitle("Consultant_appo");

            }
        });


        database= FirebaseDatabase.getInstance();
        retreff=database.getReference("Consultant Request");

        retreff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ConModule Nm = snapshot.getValue(ConModule.class);
                    String name = Nm.getName();

                    if (name1.equals(name)) {
                        e1.setText("Consultant Name : " + name1);
                        userInfo.setKeyConName(name1);
                        e2.setText("Mejore : " + Nm.getMajor());

                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }}