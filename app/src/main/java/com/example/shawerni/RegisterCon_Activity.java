package com.example.shawerni;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;


public class RegisterCon_Activity extends AppCompatActivity implements View.OnClickListener {

    public static String NAME,EMAIL, PASS, PHONE ,MAJOR;
    EditText Name;
    EditText password;
    EditText confirmPassword;
    EditText email;
    EditText PhoneNum;
    EditText Major;
    Button register;
    ImageView CVImage ;

    static int PReqCode = 100 ;
    static int REQUESCODE = 1;
    TextView CVLable ;
    Uri pickedImageUri ;



    String url;
    static String id;

    private ProgressDialog progressDialog ;
    private FirebaseAuth f1 = FirebaseAuth.getInstance();

    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    private final DatabaseReference myRef = database.getReference("Consultant Request");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_con_);




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
        Major = findViewById (R.id.major);
        CVImage = findViewById (R.id.cv_image);
        CVLable = (TextView)findViewById(R.id.cv) ;
        register = findViewById(R.id.button3);
        CVImage.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(RegisterCon_Activity.this);
                myAlertDialog.setTitle("choose image ");
                myAlertDialog.setMessage("choose image from Gallery or Camera");

                myAlertDialog.setPositiveButton(getResources().getString(R.string.gallery),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {

                                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(pickPhoto , 1);

                            }
                        });

                myAlertDialog.setNegativeButton (getResources().getString(R.string.camera),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {

                                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(takePicture, 100);//zero can be replaced with any action code

                            }
                        });
                myAlertDialog.setNeutralButton ("cancel", null);

                myAlertDialog.show();


            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkDataEntered()){
                    Calendar calendar = Calendar.getInstance();

                    //progressDialog.setMessage("wait for upload the image ...");
                    //progressDialog.show();

                    StorageReference storage = FirebaseStorage.getInstance().getReference ();
                    final StorageReference storageRef = storage.child ("consultantCV").child ("img_"+calendar.getTimeInMillis());
                    storageRef.putFile (pickedImageUri).addOnSuccessListener (new OnSuccessListener<UploadTask.TaskSnapshot> () {

                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            storageRef.getDownloadUrl ().addOnSuccessListener (new OnSuccessListener<Uri> () {
                                @Override
                                public void onSuccess(Uri uri) {

                                    url=String.valueOf(pickedImageUri);
                                }
                            });

                            //progressDialog.dismiss();
                         //   Toast.makeText(RegisterCon_Activity.this , "image uploaded successfully",Toast.LENGTH_LONG).show();

                        }
                        }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(RegisterCon_Activity.this,"the image upload process failed , try again!",Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });

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
        NAME  = Name.getText().toString().trim();
        EMAIL  = email.getText().toString().trim();
        PASS = password.getText().toString().trim();
        PHONE  = PhoneNum.getText().toString().trim();
        MAJOR  = Major.getText().toString().trim();

        if (isEmpty(Name)&&isEmpty(password)&&isEmpty(email)&&isEmpty(confirmPassword)&&isEmpty(PhoneNum)&&(isEmpty(Major)&& pickedImageUri == null)){

            Name.setError("You must enter  name to register!");
            password.setError("password is required!");
            password.setError("Please Your Password Need to Contain 6 Charecters or More ");
            email.setError("email is required!");
            confirmPassword.setError("confirmPassword is required!");
            PhoneNum.setError("PhoneNum is required!");
            Major.setError("major is required!");
            CVLable.setError(" Please Choose Image  ");
            return false;
        }
        if (isEmpty(Name)) {
           Name.setError("You must enter  name to register!");

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

        if (!isEmail(email)) {
            email.setError("Enter valid email!");
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


        if (isEmpty(Major)) {
            Major.setError("major is required!");
            return false;

        }

        if ( pickedImageUri == null){

            CVLable.setError(" Please Choose Image  ");
            CVLable.findFocus();
            return false;
        }



        //progressDialog.setMessage("waiting please...");
        //progressDialog.show();

        f1.createUserWithEmailAndPassword(EMAIL,PASS)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //progressDialog.dismiss();
                        if(task.isSuccessful()){

                            String uid = f1.getCurrentUser().getUid();
                            String id = myRef.push().getKey();
                            ConModule  m =new ConModule(id,Major.getText().toString(),email.getText().toString(),Name.getText().toString()
                                    ,password.getText().toString(),PhoneNum.getText().toString(),url);

                            myRef.child(uid).setValue(m);



                            Toast.makeText(RegisterCon_Activity.this, "Register Successfully", Toast.LENGTH_SHORT).show();


                            new AlertDialog.Builder(RegisterCon_Activity.this).setTitle("waiting please...")
                                    .setMessage("Register Successfully, Waiting please until get acceptance from admin !")
                                    .setPositiveButton("yes",null);
                            Intent intent = new Intent(RegisterCon_Activity.this,MainActivity_Con.class);
                            startActivity(intent);

                            finish();
                        }
                        else {
                            Toast.makeText(RegisterCon_Activity.this, "Couldnt register , please try again", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
        // if(ag)
        return true;
    }//data checked

    @Override
    public void onClick(View view) {

    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 ||  requestCode ==  100 && resultCode == RESULT_OK && data != null  && data.getData ()!= null){

            pickedImageUri=data.getData();
            CVImage.setImageURI(pickedImageUri);
        }

    }

}

