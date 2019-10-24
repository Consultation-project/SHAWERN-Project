package com.example.shawerni;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    public static String NAME,EMAIL, PASS, PHONE ,AGE;
    EditText Name;
    EditText password;
    EditText confirmPassword;
    EditText email;
    EditText PhoneNum;
    EditText age;
    Button register;
    //DatabaseRefernce R ;

    static String id;

    private ProgressDialog progressDialog ;
    private FirebaseAuth f1 = FirebaseAuth.getInstance();

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference myRef = database.getReference("User");



    //private FirebaseFirestore db = FirebaseFirestore.get
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        progressDialog = new ProgressDialog(this);


        // Write a message to the database




        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle(R.string.createACC);

        toolbar.setTitleTextColor(Color.WHITE);


        Name = findViewById(R.id.editText2);
        password = findViewById(R.id.editText4);
        confirmPassword = findViewById(R.id.editText7);
        email = findViewById(R.id.editText5);
        PhoneNum = findViewById(R.id.editText8);
        age = findViewById(R.id.editText3);

        register = findViewById(R.id.button3);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkDataEntered()){

                    id = f1.getUid();
                }}
        });




    }


   /* boolean isDigits = TextUtils.isDigitsOnly(edtDigits.getText().toString());

    public boolean isDigits(String number){
        if(!TextUtils.isEmpty(number)){
            return TextUtils.isDigitsOnly(number);
        }else{
            return false;
        }
    }*/

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }



    boolean checkDataEntered() {
        NAME  = Name.getText().toString().trim();
        EMAIL  = email.getText().toString().trim();
        PASS = password.getText().toString().trim();
        PHONE  = PhoneNum.getText().toString().trim();
        AGE  = age.getText().toString().trim();

        if (isEmpty((EditText)Name )&&(isEmpty(password))
                &&(isEmpty(PhoneNum))&& (isEmpty(age))&& (isEmpty(email))) {
            Name.setError("You must enter name!");
            password.setError("password is required!");
            PhoneNum.setError("Phone number is required!");
            age.setError("age is required!");
            email.setError("Enter valid email!");
            return false;}

        if (isEmpty(Name)) {
            Name.setError( "You must enter  name to register!");
            return false;

        }



        if (isEmpty(password)) {
            password.setError("password is required!");
            return false;

        }

        if( password.getText().toString().length()<=6){
            password.setError("Please Your Password Need to Contain 6 Charecters or More ");
            return false;
        }



        if (isEmpty(email)) {
            email.setError("email is required!");
            return false;

        }

        if (isEmpty(confirmPassword)) {
            confirmPassword.setError("confirmPassword is required!");
            return false;

        }

        if (!isEmail(email)||(!email.getText().toString().substring(email.getText().toString().indexOf(".")+1).equals("com"))){
            email.setError("Enter valid email!");
            return false;
        }

        String MobilePattern = "[0-9]{10}";
        final String num = "05";
        if((!PhoneNum.getText().toString().matches(MobilePattern))||(!PhoneNum.getText().toString().substring(0,2).matches(num))) {
            PhoneNum.setError("not corect Number , please try again");
            return false;

        }

        if (! password.getText().toString().matches(confirmPassword.getText().toString())) {
            confirmPassword.setError("password not equal , please try again");
            return false;

        }

        if (isEmpty(PhoneNum)) {
            PhoneNum.setError("PhoneNum is required!");
            return false;

        }


        if (isEmpty(age)) {
            age.setError("age is required!");
            return false;

        }

        if(age.length()<2 || age.length()>2 ){
            age.setError("Please Enter Your Age Correctly ");
            return false;
        }

        if(age.getText().toString().substring(0,1).equals("0")||age.getText().toString().substring(0,1).equals("1")){
            age.setError("The application  accepts just +20 year ");
            return false;
        }



        progressDialog.setMessage("waiting please...");
        progressDialog.show();

        f1.createUserWithEmailAndPassword(EMAIL,PASS)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressDialog.dismiss();
                        if(task.isSuccessful()){

                            String uid = f1.getCurrentUser().getUid();
                            String id = myRef.push().getKey();
                            Module m =new Module(id,age.getText().toString(),email.getText().toString(),Name.getText().toString()
                                    ,password.getText().toString(),PhoneNum.getText().toString(),"AAA");

                            myRef.child(uid).setValue(m);



                            Toast.makeText(RegisterActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Couldnt register , please try again", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
        // if(ag)
        return true;
    }//data checked

    @Override
    public void onClick(View view) {

    }

    // public void onClick(View view) {

    //}


}