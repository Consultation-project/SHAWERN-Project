package com.example.shawerni;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class payment_information extends AppCompatActivity {



    TextView User;
    TextView Email;
    TextView ConsultantName;
    TextView Date ;
    TextView Time ;
    TextView ReservationType;
    ImageView paymentImage ;
    TextView paymentImagelable ;
    Button payNow;
    View view;


    String UserName;
    String UserEmail;
    String COnsultantName;
    String DATE ;
    String TIME ;
    String Type ;

    static int PReqCode = 100 ;
    static int REQUESCODE = 1;
    TextView payLable ;
    Uri pickedImageUri ;
    String url;


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference df ;
    String i;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_information);

        User = findViewById (R.id.user);
        Email = findViewById (R.id.eEmail);
        ConsultantName = findViewById (R.id.consultantName);
        Date = findViewById (R.id.dateConsultation);
        Time = findViewById (R.id.timeConsultation);
        ReservationType = findViewById (R.id.reservationType);
        payNow = findViewById(R.id.button3);
        paymentImage = findViewById (R.id.cv_image);
        paymentImage.setOnClickListener (new View.OnClickListener () {
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

                Toast.makeText(payment_information.this , "your payment was successfully",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(payment_information.this,MainActivity.class);
                startActivity(intent);

                finish();

            /* if ( pickedImageUri != null){

                    Calendar calendar = Calendar.getInstance();

                    //progressDialog.setMessage("wait for upload the image ...");
                    //progressDialog.show();

                    StorageReference storage = FirebaseStorage.getInstance().getReference ();
                    final StorageReference storageRef = storage.child ("paymentImage").child ("img_"+calendar.getTimeInMillis());
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
                            Toast.makeText(payment_information.this , "your payment was successfully",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(payment_information.this,MainActivity.class);
                            startActivity(intent);

                            finish();

                        }
                    }).addOnFailureListener(new OnFailureListener () {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(payment_information.this,"the image upload process failed , try again!",Toast.LENGTH_LONG).show();
                        }
                    });
                }

                else {
                    paymentImagelable.setError (" Please Choose Image  ");
                    paymentImagelable.findFocus ();
                }
*/
            }
        });










        PayConfirm payConfirm= new PayConfirm ();
        FirebaseDatabase database;
        DatabaseReference retreff ;


        // name1 = getIntent().getStringExtra("name");

       //  e1.setText(name1);
        database= FirebaseDatabase.getInstance();
        retreff=database.getReference("Pay Confirm ");

        retreff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    PayConfirm Nm = snapshot.getValue(PayConfirm.class);

                    UserName = Nm.getName ();
                    UserEmail = Nm.getEmail ();
                    COnsultantName = Nm.getConsultentName ();
                    DATE = Nm.getDate ();
                    TIME = Nm.getTime ();
                    Type = Nm.getType ();

                    User.setText(UserName);
                    Email.setText(UserEmail );
                    ConsultantName.setText(COnsultantName);
                    Date.setText(DATE);
                    Time.setText(TIME);
                    ReservationType.setText(Type);

                    }


                }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });}



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 ||  requestCode ==  100 && resultCode == RESULT_OK && data != null  && data.getData ()!= null){

            pickedImageUri=data.getData();
            paymentImage.setImageURI(pickedImageUri);
        }

    }
}
