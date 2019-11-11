package com.example.shawerni;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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

public class listOfConfirm_admin extends AppCompatActivity {

    private static final String TAG = "Activity";
    private ArrayList<String> nId = new ArrayList<>();
    private ArrayList<String> nName = new ArrayList<>();

    FirebaseDatabase database;
    DatabaseReference retreff ;
    String i;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listofconfirm_admin);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("payment requests");
        toolbar.setTitleTextColor(Color.WHITE);
        Log.d(TAG, "onCreate");
        Ename();


    }
    private void Ename() {
        database= FirebaseDatabase.getInstance();
        retreff=database.getReference("Confirm payments");

        retreff.addValueEventListener(new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot :dataSnapshot.getChildren()){
                    PayConfirm Nm = snapshot.getValue(PayConfirm.class) ;

                    String userId=snapshot.getKey().toString();
                    String name = Nm.getName();
                    nName.add(name);
                    nId.add(userId);
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
        listOfConfirm myr = new listOfConfirm(nName,nId,this);
        recyclerView.setAdapter(myr);
        recyclerView.setLayoutManager(new LinearLayoutManager (this));







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