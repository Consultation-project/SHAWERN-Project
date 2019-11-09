package com.example.shawerni;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class looginadmin extends AppCompatActivity implements View.OnClickListener {

    Button Login;
    EditText Email;
    EditText Password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looginadmin);


        Login = findViewById(R.id.b1ad);
        Email = findViewById(R.id.E1ad);
        Password = findViewById(R.id.p3ad);
        checkDataEntered();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Email.getText().toString().equals("admin@gmail.com") && Password.getText().toString().equals("1234ADMInh")) {
                    Intent i = new Intent(looginadmin.this, request.class);
                    startActivity(i);
                }

                //vaal(Email.getText().toString(),Password.getText().toString());
            }

        });

    }


    @Override
    public void onClick(View v) {
        boolean validate = checkDataEntered();

    }

    public void onPause() {
        super.onPause();
        finish();
    }


    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean checkDataEntered() {


        if (isEmpty(Password) && isEmpty(Email)) {
            Password.setError(" required!");
            return false;

        }

        if (isEmpty(Email)) {
            Email.setError("Email is required!");
            return false;

        }

        if (!isEmail(Email)) {
            Email.setError("Enter valid email!");
            return false;

        }


        return true;
    }


}
