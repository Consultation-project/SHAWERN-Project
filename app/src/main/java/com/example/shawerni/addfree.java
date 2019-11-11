package com.example.shawerni;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class addfree extends AppCompatActivity {

    TextView answer , sender , tvcon;
    String con;

    private List<anQ> anQList = new ArrayList<>();

    private final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("AnsweredQ");
    FirebaseAuth firebaseAuth;
    String Uid ;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfree);

        tvcon = findViewById(R.id.sender1);
        answer = findViewById(R.id.ans);
        sender = findViewById(R.id.Name2);


        con = getIntent().getExtras().getString("Value2");
        tvcon.setText(con);
        Toolbar toolbar = findViewById(R.id.toolbar20);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Free Consultaion");

        toolbar.setTitleTextColor(Color.WHITE);

       // Uid = FirebaseAuth.getCurrentUser().getUid();



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    anQ an = ds.getValue(anQ.class);

                    String msg = an.getMsg();

                    if(msg.equals(con)) {

                        answer.setText(an.getAnswer());
                        sender.setText(an.getSender());


                        //anQList.add(new anQ(an.getAnswer(),an.getSender(),an.getReciver(),an.getMsg()));
                        //inRecycle();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void inRecycle() {

       // RecyclerView recyclerView = findViewById(R.id.recycler_view1);
        //anAdapter myr = new anAdapter(anQList, context);
        //recyclerView.setAdapter(myr);


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
