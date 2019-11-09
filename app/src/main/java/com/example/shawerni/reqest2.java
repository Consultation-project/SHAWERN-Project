package com.example.shawerni;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class reqest2 extends AppCompatActivity {
    Button accept;
    // Button reject;
    Button request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reqest2);

        Toolbar toolbar = findViewById(R.id.toolbar900);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Home");

        toolbar.setTitleTextColor(Color.WHITE);

        accept = findViewById(R.id.accept);
        //reject=findViewById(R.id.request);
        request = findViewById(R.id.request);


        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(reqest2.this, reqlist.class));

            }
        });

        /*reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(request.this,reject.class));
            }
        });*/

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(reqest2.this, accept.class));
            }
        });

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