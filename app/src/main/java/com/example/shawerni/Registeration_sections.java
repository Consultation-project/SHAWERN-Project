package com.example.shawerni;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Registeration_sections extends AppCompatActivity {
    Button Consultant, User, admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeration_sections);



        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

setTitle("");



        Consultant = findViewById(R.id.consultant);
        Consultant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Registeration_sections.this, LoginActivity_Con.class);
                startActivity(i);
                finish();
            }
        });

        User = findViewById(R.id.consultation_seeker);
        User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Registeration_sections.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        admin = findViewById(R.id.admin);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Registeration_sections.this, looginadmin.class);
                startActivity(i);
                finish();
            }
        });

    }



}