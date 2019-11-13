package com.example.shawerni;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class addfree extends AppCompatActivity {
    TextView tvcon, tv_date, tv_text;
    String con, date, text;
    int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfree);
        tvcon = findViewById(R.id.sender1);
        tv_date = findViewById(R.id.date);
        tv_text = findViewById(R.id.text);
        Intent intent = getIntent();
        if (intent != null) {
            con = intent.getStringExtra("name");
            date = intent.getStringExtra("date");
            text = intent.getStringExtra("text");
            tvcon.setText(con);
            tv_date.setText(date);
            tv_text.setText(text);
        }

        Toolbar toolbar = findViewById(R.id.toolbar20);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Free Consultaion");

        toolbar.setTitleTextColor(Color.WHITE);
    }

}
