package com.example.shawerni;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class reqlist extends AppCompatActivity {
    //private static final String TAG = "Activity";
    public static String name;
    public static String id;
    public Intent i2;
    //private ArrayList<ConModule> con1 = new ArrayList<>();
    ConModule n = new ConModule();
    FirebaseDatabase database;
    DatabaseReference retreff;
    String st;


    // ArrayList<String> listitems ;
    //ArrayAdapter<String> arrayAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<String> listitems;
    private ArrayList<ConModule> listitems2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reqlist);

        Toolbar toolbar = findViewById(R.id.toolbar90);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Consultatnt Request");

        toolbar.setTitleTextColor(Color.WHITE);


        // Log.d(TAG, "onCreate");


        //recyclerView=(RecyclerView)findViewById(R.id.v34);

        //recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listitems = new ArrayList<String>();


        database = FirebaseDatabase.getInstance();
        retreff = database.getReference("Consultant Request");
        retreff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    ConModule Nm = ds.getValue(ConModule.class);
                    name = Nm.getName();


                    listitems.add(name);
                    //String s= dp.getName();
                    inRecycle();
                    // ConModule dp2=new ConModule("name :"+s);
                    System.out.println("Name   " + name);


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


    }


    private void inRecycle() {
        RecyclerView recyclerView = findViewById(R.id.v34);
        myrec2 myr = new myrec2(listitems, this);
        recyclerView.setAdapter(myr);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {

            onBackPressed();

            // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }


}
