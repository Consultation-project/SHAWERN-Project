package com.example.shawerni;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Add_Consultation extends AppCompatActivity implements View.OnClickListener {


    private String departmentName;
    Button paidCon;
    Button FreeCon;
    ImageView PublicImg;
    ImageView PrivateImg;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultation_sections);


        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setTitleTextColor(Color.WHITE);

        paidCon = findViewById(R.id.paidConsultation);
        FreeCon = findViewById(R.id.freeConsultation);
        PrivateImg = findViewById(R.id.img_private);
        PublicImg = findViewById(R.id.img_public);

        paidCon.setOnClickListener(this);
        FreeCon.setOnClickListener(this);
        PrivateImg.setOnClickListener(this);
        PublicImg.setOnClickListener(this);


        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            departmentName = intent.getExtras().getString(Constance.key.TITLE);
            if (departmentName != null) {
                setTitle(getIntent().getStringExtra(Constance.key.TITLE));
            }//inner if


        }//outer if
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {

            onBackPressed();

            // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.img_private:
            case R.id.paidConsultation:

                startActivity(new Intent(Add_Consultation.this , ConsultantNewVersion.class));

                break;

            case R.id.img_public:
            case R.id.freeConsultation:

                startActivity(new Intent(Add_Consultation.this , free_Consultation.class));

break ;

        }
    }
}