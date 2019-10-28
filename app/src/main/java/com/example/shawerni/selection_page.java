package com.example.shawerni;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

//import android.support.v7.app.AppCompatActivity;

public class selection_page extends AppCompatActivity {
    Button Nextbtn;
    private UserInfo userInfo;
private RadioGroup rg;
private int tmp;
    //Button radioGroupCom = (RadioGroup) findViewById(R.id.radioComm);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_page);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        userInfo        = new UserInfo(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Communication Type");
        toolbar.setTitleTextColor(Color.WHITE);
        rg= findViewById(R.id.radioComm);
        tmp=rg.getCheckedRadioButtonId();
        userInfo.setMethod("call");

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.radioCall) {
                    userInfo.setMethod("call");
                    //do work when radioButton1 is active
                } else  if (checkedId == R.id.radioVisit) {
                    userInfo.setMethod("visit");

                    //do work when radioButton2 is active
                }

            }
        });
        Nextbtn=findViewById(R.id.Nextbtn);
        Nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(selection_page.this, MainActivity2.class);
                startActivity(i);
               // getSupportFragmentManager().beginTransaction().replace(R.id.container,new MainActivity2()).commit();
              //  setTitle("Consultant_appo");

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
