package com.example.shawerni;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

    View view;


    String UserName;
    String UserEmail;
    String COnsultantName;
    String DATE ;
    String TIME ;
    String Type ;

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
        paymentImage.findViewById (R.id.cv_image);


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


}
