package com.example.shawerni;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;





public class forConfirm extends AppCompatActivity {


    static boolean changeStatus = false;
    static boolean deleteFromList = false;
    TextView userName;
    TextView useremail;
    TextView consultantName;

    ImageView transferImage;
    View view;
    String USER;
    String uId;

    Button confirm;
    Button cancle;
    String ch="send";
    NotificationManagerCompat nn;
    private UserInfo userInfo;
    String id = "nn";


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference retreff = database.getReference("Confirm payments");


    StorageReference storage = FirebaseStorage.getInstance().getReference();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmpayment_admin);


        Toolbar toolbar = findViewById(R.id.toolbar2);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Booking Information");
        toolbar.setTitleTextColor(Color.WHITE);
        nn=NotificationManagerCompat.from(this);

        userName = findViewById(R.id.user1);
        useremail = findViewById(R.id.eEmail);
        consultantName = findViewById(R.id.consultantName1);
        transferImage = findViewById(R.id.transfer_image);
        confirm = findViewById(R.id.button4);
        cancle = findViewById(R.id.button3);

        PayConfirm PayConfirm = new PayConfirm();


        USER = getIntent().getStringExtra("name");
        uId = getIntent().getStringExtra("userId");
        userInfo = new UserInfo(this);


        retreff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    PayConfirm Nm = snapshot.getValue(PayConfirm.class);
                    String name = Nm.getName();

                    if (USER.equals(name)) {

                        userName.setText(USER);
                        userInfo.setKeyConName(USER);
                        useremail.setText(Nm.getEmail());
                        consultantName.setText(Nm.getConsultentName());

                        Picasso.get().load(Nm.getUrl()).into(transferImage);

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                noty();
                //payment_information referenceName = new payment_information();

                //  referenceName.payNow.setEnabled(false);

                //Intent intent2;
                //intent2 = new Intent(forConfirm.this,wallet.class);
                //PendingIntent pendingIntent = PendingIntent.getActivity(forConfirm.this, 0, intent2, PendingIntent.FLAG_ONE_SHOT);

                changeStatus = true;
                deleteFromList = true;

                Intent intent = new Intent(forConfirm.this, listOfConfirm_admin.class);
                startActivity(intent);

                finish();
            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                deleteFromList = true;
                Intent intent = new Intent(forConfirm.this, listOfConfirm_admin.class);
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

    public void chmetod(View V){


    }

    public void createNot(){
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O ) {

            NotificationChannel CHANNEL_ID = new NotificationChannel(
                    ch,
                    "chan1",
                    NotificationManager.IMPORTANCE_DEFAULT



            );
            CHANNEL_ID.enableLights(false);
            CHANNEL_ID.enableVibration(true);
            CHANNEL_ID.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            CHANNEL_ID.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            CHANNEL_ID.setDescription("vh1");

            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.createNotificationChannel(CHANNEL_ID);
        }}
    public void noty(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, ch)
                .setSmallIcon(R.drawable.consultant_app_icon)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Set the intent that will fire when the user taps the notification
        //.setContentIntent(pendingIntent)

        NotificationManagerCompat notificationManagerCompat =NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1, builder.build());
    }

}