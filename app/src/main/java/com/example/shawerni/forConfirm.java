package com.example.shawerni;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;


public class forConfirm extends AppCompatActivity {


    static boolean changeStatus  = false;
    TextView userName;
    TextView useremail;
    TextView consultantName ;

    ImageView transferImage ;
    View view;
    String USER;
    String uId ;

    Button confirm ;
    Button cancle ;
    private UserInfo userInfo;

    FirebaseDatabase database =  FirebaseDatabase.getInstance();
    DatabaseReference retreff = database.getReference("Confirm payments");



    StorageReference storage = FirebaseStorage.getInstance().getReference ();




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmpayment_admin);



        Toolbar toolbar = findViewById(R.id.toolbar2);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Booking Information");
        toolbar.setTitleTextColor(Color.WHITE);

        userName = findViewById(R.id.user1);
        useremail = findViewById(R.id.eEmail);
        consultantName = findViewById(R.id.consultantName1);
        transferImage = findViewById(R.id.transfer_image);
        confirm = findViewById (R.id.button4);
        cancle = findViewById (R.id.button3);

        PayConfirm PayConfirm= new PayConfirm();





        USER = getIntent().getStringExtra("name");
        uId=getIntent().getStringExtra("userId");
        userInfo = new UserInfo(this);


        retreff.addValueEventListener(new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    PayConfirm Nm = snapshot.getValue(PayConfirm.class);
                    String name = Nm.getName();

                    if (USER.equals(name)) {

                        userName.setText(USER);
                        userInfo.setKeyConName(USER);
                        useremail.setText(Nm.getEmail ());
                        consultantName.setText (Nm.getConsultentName ());

                        Picasso.get ().load(Nm.getUrl ()).into (transferImage);

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        confirm.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {


                //payment_information referenceName = new payment_information();

                //  referenceName.payNow.setEnabled(false);


                changeStatus = true;


                Intent intent = new Intent(forConfirm.this,listOfConfirm_admin.class);
                startActivity(intent);

                finish();
            }
        });

        cancle.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(forConfirm.this,listOfConfirm_admin.class);
                startActivity(intent);

                finish();

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