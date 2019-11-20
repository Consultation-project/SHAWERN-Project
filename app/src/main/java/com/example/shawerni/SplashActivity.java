package com.example.shawerni;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

String F;
    String name ;
    FirebaseAuth firebaseAuth;
    LinearLayout splashLayout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashh_layout);



        splashLayout = (LinearLayout) findViewById(R.id.splashLL);
        splashLayout.setOnClickListener(this);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),Registeration_sections.class));
            }
        },4000);


    }//end onCreate



    @Override
    public void onClick(View view) {
        Intent intent =new Intent(SplashActivity.this , Registeration_sections.class );
        startActivity(intent);
        finish();
    }//close onClick










}//end class

