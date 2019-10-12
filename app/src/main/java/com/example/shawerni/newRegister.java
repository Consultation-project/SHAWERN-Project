package com.example.shawerni;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class newRegister extends AppCompatActivity {






    EditText Name;
    EditText Password;
    EditText confirmPassword;
    EditText Email;
    EditText PhoneNum;
    EditText age;
    Button register;



        private FirebaseAuth auth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

            //Get Firebase auth instance
            auth = FirebaseAuth.getInstance();


            Name = findViewById(R.id.editText2);
            Password = findViewById(R.id.editText4);
            confirmPassword = findViewById(R.id.editText7);
            Email = findViewById(R.id.editText5);
            PhoneNum = findViewById(R.id.editText8);
            age = findViewById(R.id.editText3);

            register = findViewById(R.id.button3);




            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String email = Email.getText().toString().trim();
                    String password = Password.getText().toString().trim();

                    if (TextUtils.isEmpty(email)) {
                        Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(password)) {
                        Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (password.length() < 6) {
                        Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    //create user
                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(newRegister.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(newRegister.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(newRegister.this, "Authentication failed." + task.getException(),
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        startActivity(new Intent(newRegister.this, MainActivity.class));
                                        finish();
                                    }
                                }
                            });

                }
            });
        }

        @Override
        protected void onResume() {
            super.onResume();

        }
    }


