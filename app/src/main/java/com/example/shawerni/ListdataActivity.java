package com.example.shawerni;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ListdataActivity extends AppCompatActivity {
    TextView listdata,txtCer;
    ImageView imageView;
    private UserInfo userInfo;
    private Button mSubmitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_listdata);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Consultation information");
        toolbar.setTitleTextColor(Color.WHITE);


        userInfo        = new UserInfo(this);
        listdata = findViewById(R.id.listdata);
        txtCer=findViewById(R.id.certificate);
        imageView = findViewById(R.id.imageView);
        Intent intent = getIntent();

        //  String receivedName =  intent.getStringExtra("name");
        //  int receivedImage = intent.getIntExtra("image",0);
        mSubmitButton  = findViewById(R.id.reserved);
        listdata.setText(userInfo.getKeyName());
        txtCer.setText(userInfo.getKeyCer());
        imageView.setImageResource(userInfo.getKeyImagename());
        //enable back Button
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getApplicationContext(),NewResivActivity.class);
                //intent.putExtra("name",personNames[i]);

                Intent intent = new Intent(ListdataActivity.this, selection_page.class);
                startActivity(intent);
                //Intent intent = new Intent(ListdataActivity.this, selection_page.class);
                //startActivity(intent);

                //   intent.putExtra("image",personImages[i]);
                //  startActivity(intent);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
