package com.example.shawerni;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Registeration_sections extends AppCompatActivity {
    Button  Consultant , User ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeration_sections);



        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

setTitle("");

        toolbar.setTitleTextColor(Color.WHITE);

        Consultant = findViewById(R.id.consultant);
        Consultant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Registeration_sections.this, LoginActivity_Con.class);
                startActivity(i);
            }
        });

        User = findViewById(R.id.consultation_seeker);
        User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Registeration_sections.this, LoginActivity.class);
                startActivity(i);
            }
        });

    }



}