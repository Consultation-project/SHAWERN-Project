package com.example.shawerni;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class answer2 extends AppCompatActivity {


    TextView msg ,sender , Answer ,con;
    String consul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_two);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("public consultation");
        toolbar.setTitleTextColor(Color.WHITE);


        sender = findViewById(R.id.dialog_title);
        msg = findViewById(R.id.cccc);
        Answer = findViewById(R.id.Answer2);
        con = findViewById(R.id.con);

        Intent intent = getIntent();
        if (intent.getExtras() != null) {

            sender.setText(intent.getExtras().getString("SENDER"));
            msg.setText(intent.getExtras().getString("MSG"));
            Answer.setText(intent.getExtras().getString("answer"));
            con.setText(intent.getExtras().getString("consul"));

        }//inner if

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        startActivity(new Intent(answer2.this , MainActivity_Con.class));
        return super.onOptionsItemSelected(item);
    }
}

