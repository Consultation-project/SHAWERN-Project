package com.example.shawerni;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckedTextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class language extends AppCompatActivity {

    CheckedTextView arabic, english;

    @SuppressLint("ResourceAsColor")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lanuage);


        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Language");

        toolbar.setTitleTextColor(Color.WHITE);

        arabic = findViewById(R.id.Arabic);
        english = findViewById(R.id.English);


        arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arabic.toggle();
                if (arabic.isChecked()) {
                    arabic.setCheckMarkDrawable(R.drawable.ic_check_black_24dp);
                    english.setCheckMarkDrawable(null);
                    setLanquage("ar");
                }
            }
        });

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                english.toggle();
                if (english.isChecked()) {
                    english.setCheckMarkDrawable(R.drawable.ic_check_black_24dp);
                    arabic.setCheckMarkDrawable(null);
                    setLanquage("en");
                }
            }
        });
    }

    private void setLanquage(String Lanquage) {
        MySharedPreference.putString(this, "APP_LANGUAGE", Lanquage);
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {

            onBackPressed();

            // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);}

}
