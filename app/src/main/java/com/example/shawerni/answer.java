package com.example.shawerni;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class answer extends AppCompatActivity {

    TextView msg ,sender;

@Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer);

    //Intent intent = getIntent();
    //if (intent.getExtras() != null) {
      //  sender = intent.getExtras().getString("SENDER");
      //  msg = intent.getExtras().getString("MSG");
       // }//inner if

    Toolbar toolbar = findViewById(R.id.toolbar2);
    setSupportActionBar(toolbar);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);

    setTitle("public consultation");
    toolbar.setTitleTextColor(Color.WHITE);

    sender = findViewById(R.id.dialog_title);
    msg = findViewById(R.id.cccc);

    Intent intent = getIntent();
    if (intent.getExtras() != null) {
        sender.setText(intent.getExtras().getString("SENDER"));
        msg.setText(intent.getExtras().getString("MSG"));
        }//inner if


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
