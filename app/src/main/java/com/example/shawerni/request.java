package com.example.shawerni;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class request extends AppCompatActivity {
    Button req, log,py;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        req = findViewById(R.id.requestlist);
        log = findViewById(R.id.Logoutad);
        py=findViewById(R.id.paymentad);
        req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(request.this, reqest2.class);
                startActivity(i);
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(request.this, Registeration_sections.class);
                startActivity(i);

            }
        });
        py.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(request.this, listOfConfirm_admin.class);
                startActivity(i);
            }
        });
    }
}
