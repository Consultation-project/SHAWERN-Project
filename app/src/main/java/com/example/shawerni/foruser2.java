package com.example.shawerni;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class foruser2 extends AppCompatActivity {

    public static String major;
    public static String ido;
    public static String email;
    public static String ph;
    TextView e1;
    TextView e2;
    TextView e3;
    TextView e4;
    View view;
    String name3;
    String mejore;
    DatabaseReference df1;
    String i;
    String X;
    String st, st2;
    ConModule n = new ConModule();
    FirebaseDatabase database;
    //database2;
    // FirebaseDatabase auth;
    //FirebaseAuth.AuthStateListener authStateListener;
    DatabaseReference retreff;
    // DatabaseReference retreff2;
    DatabaseReference ref;
    ArrayList<ConModule> arrayList = new ArrayList<ConModule>();

    private FirebaseAuth f1 = FirebaseAuth.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foruser2);
        e1 = findViewById(R.id.n3);
        e2 = findViewById(R.id.m3);
        e3 = findViewById(R.id.e3);
        e4 = findViewById(R.id.e4);
        final ConModule conModule = new ConModule();
        FirebaseDatabase database1;
        DatabaseReference retreff1;
        Button b = findViewById(R.id.a3);
        Button b2 = findViewById(R.id.r3);
        st = getIntent().getExtras().getString("Value");
        e1.setText(" name: " + st);
        st2 = getIntent().getExtras().getString("conR");




        /*ID2 = FirebaseAuth.getInstance()
                .getCurrentUser().getUid();
         System.out.println(" ID2 "+ID2);*/

        //  final String[] id2

        // name3 = getIntent().getStringExtra("name");


        database = FirebaseDatabase.getInstance();
        //database2=FirebaseDatabase.getInstance();

        retreff = database.getReference("Consultant Request");
        retreff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    ConModule Nm = ds.getValue(ConModule.class);

                    major = Nm.getMajor();
                    email = Nm.getEmail();
                    ph = Nm.getPhoneNum();

                    ido = f1.getUid();

                    // System.out.println(" what is the url"+Nm.getUrl());
                    //ido=Nm.toString();
                    System.out.println(" pleasee  ID " + ido);


                    //String s= dp.getName();

                    // ConModule dp2=new ConModule("name :"+s);


                    // ConModule dp=new ConModule(name);
                    //listitems.add(dp);


                }
                // myrec2 adapter=new myrec2(listitems,this);
                //recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        e2.setText(" Major: " + major);
        e3.setText(" Email: " + email);
        e4.setText(" phone: " + ph);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref = FirebaseDatabase.getInstance().getReference().child("databaseAcept");
                databaseAcept ac = new databaseAcept(st, email, major, ph);

                ref.push().setValue(ac);
                Toast.makeText(foruser2.this, " accepted", Toast.LENGTH_LONG).show();


            }


        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder dailoag = new AlertDialog.Builder(foruser2.this);
                dailoag.setTitle("Are your sure ?");
                dailoag.setMessage("this account will be rejected");
                dailoag.setPositiveButton(" Delete ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int i = 0;
                        ConModule ob = arrayList.get(i);


                        // deleteC();
                        i++;
                        Toast.makeText(foruser2.this, " rejected", Toast.LENGTH_LONG).show();


                    }
                });
                dailoag.setNegativeButton("cancel ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(foruser2.this, " some thing wrong", Toast.LENGTH_LONG).show();

                        dialog.dismiss();

                    }
                });
                AlertDialog alertDialog = dailoag.create();
                alertDialog.show();


            }


        });


    }


    private void deleteC(String id) {
        DatabaseReference refConsultaion = FirebaseDatabase.getInstance().getReference("Consultant Request").child(id);
        refConsultaion.removeValue();


    }


}
