package com.example.shawerni;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class payment_information extends AppCompatActivity {

    TextView User;
    TextView Email;
    TextView ConsultantName;
    TextView Date ;
    TextView Time ;
    TextView ReservationType;
    ImageView paymentImage ;
    TextView paymentImagelable ;
    static Button payNow;
    View view;

    String UserName;
    String UserEmail;
    String COnsultantName;
    String DATE ;
    String TIME ;
    String Type ;
    Uri pickedImageUri ;
    String url;

    static boolean waiting = false;
    private FirebaseAuth f1 = FirebaseAuth.getInstance();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String userid = user.getUid();

    StorageReference storage = FirebaseStorage.getInstance().getReference ();
    private final DatabaseReference myRef = database.getReference("Confirm payments");
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_information);


        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Booking Information");
        toolbar.setTitleTextColor(Color.WHITE);


        User = findViewById (R.id.user);
        Email = findViewById (R.id.eEmail);
        ConsultantName = findViewById (R.id.consultantName);
        Date = findViewById (R.id.dateConsultation);
        Time = findViewById (R.id.timeConsultation);
        ReservationType = findViewById (R.id.reservationType);
        payNow = findViewById(R.id.button3);
        paymentImagelable = findViewById (R.id.paymentImage);
        paymentImage = findViewById (R.id.cv_image);


        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                User.setText(dataSnapshot.child("name").getValue().toString());
                Email.setText(dataSnapshot.child("email").getValue().toString());


                Intent intent = getIntent();

                if (intent.getExtras() != null) {

                    ConsultantName.setText (intent.getStringExtra ("name"));
                    Date.setText (intent.getStringExtra ("date"));
                    Time.setText (intent.getStringExtra ("time"));
                    ReservationType.setText (intent.getStringExtra ("method"));



                }

                UserName = User.getText().toString();
                UserEmail = Email.getText().toString();
                COnsultantName = ConsultantName.getText().toString();
                DATE = Date.getText().toString();
                TIME = Time.getText().toString();
                Type = ReservationType.getText().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        paymentImage.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(payment_information.this);
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

        payNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkDataEntered()){

                }
                waiting = true;


            }

        });
        if(waiting == true){
            payNow.setBackgroundColor(Color.GRAY);
            payNow.setEnabled(false);
            Toast.makeText(payment_information.this, "wait until get approval from admin", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkDataEntered() {

        if ( pickedImageUri != null){



            final StorageReference storageRef = storage.child ("Confirm Payments").child (  System.currentTimeMillis () + "."+ getFileExtension (pickedImageUri));

            storageRef.putFile (pickedImageUri).addOnSuccessListener (new OnSuccessListener<UploadTask.TaskSnapshot> () {

                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                    storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Uri downloadUrl = uri;
                            //Do what you want with the url

                            String uid = f1.getCurrentUser().getUid();
                            String id = myRef.push().getKey();

                            PayConfirm  m = new PayConfirm(id,UserName,UserEmail,COnsultantName,DATE,
                                    TIME,Type, downloadUrl.toString ());

                            myRef.child(uid).setValue(m);
                        }




                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(payment_information.this,"the image upload process failed , try again!",Toast.LENGTH_LONG).show();
                }
            });

        }

        else {

            Toast.makeText(payment_information.this,"Please Choose Image!",Toast.LENGTH_LONG).show();
            return false;
        }




        Toast.makeText(payment_information.this, "wait until get approval from admin", Toast.LENGTH_SHORT).show();


        Intent intent = new Intent(payment_information.this,MainActivity.class);
        startActivity(intent);

        finish();
        return true;

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 ||  requestCode ==  100 && resultCode == RESULT_OK && data != null  && data.getData ()!= null){

            pickedImageUri=data.getData();
            paymentImage.setImageURI(pickedImageUri);
        }

    }

    private String getFileExtension (Uri uri){

        ContentResolver cR = getContentResolver ();
        MimeTypeMap mime = MimeTypeMap.getSingleton ();
        return mime.getExtensionFromMimeType (cR.getType (uri));


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