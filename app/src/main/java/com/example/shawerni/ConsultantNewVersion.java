package com.example.shawerni;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ConsultantNewVersion extends AppCompatActivity {

    private static final String TAG = "Activity";
    private ArrayList<String> nId = new ArrayList<>();
    private ArrayList<String> nName = new ArrayList<>();
    private ArrayList<ConModule> con = new ArrayList<>();
    private ArrayList<String>list = new ArrayList<>();
    MyReclyecon myr ;
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

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Consultant");
        toolbar.setTitleTextColor(Color.WHITE);


    }

    private void Ename() {
        database= FirebaseDatabase.getInstance();
        retreff=database.getReference("Consultant Request");

        retreff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot :dataSnapshot.getChildren()){
                    ConModule Nm = snapshot.getValue(ConModule.class) ;

                    String userId=snapshot.getKey().toString();
                    String name = Nm.getName();
                    nName.add(name);
                    nId.add(userId);
                    inRecycle ();
                }
                setnName(nName);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void inRecycle (){
        RecyclerView recyclerView= findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myr = new MyReclyecon(nName,nId,this);
        recyclerView.setAdapter(myr);


    }


    private void setnName(ArrayList<String> nName){

       this.nName = nName;

    }

    private ArrayList<String> getnName(){

        return nName;

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search_bar,menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText){
                String userinput =newText.toLowerCase();
                ArrayList<String> s2 = new ArrayList<String>();

                for (String s : getnName()){

                    if ((s.toLowerCase().startsWith(userinput))){
                        s2.add(s);
                    }
                }
               // myr.getFilter().filter(newText);
                myr.updat(s2, userinput);
                return false;
            }
        });

        return true;
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