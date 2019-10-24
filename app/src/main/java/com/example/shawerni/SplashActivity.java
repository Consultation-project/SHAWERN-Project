package com.example.shawerni;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {


    LinearLayout splashLayout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashh_layout);

        splashLayout = (LinearLayout) findViewById(R.id.splashLL);
        splashLayout.setOnClickListener(this);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),Registeration_sections.class));
            }
        },2000);


    }//end onCreate


    @Override
    public void onClick(View view) {
        Intent intent =new Intent(SplashActivity.this , Registeration_sections.class );
        startActivity(intent);
        finish();
    }//close onClick
}//end class

