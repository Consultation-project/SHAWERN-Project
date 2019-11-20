package com.example.shawerni;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class answer extends AppCompatActivity {
    TextView msg ,sender  ;
    Button Answer ;
    final Context context = this;
    EditText userInput;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("AnsweredQ");
    DatabaseReference retreff = FirebaseDatabase.getInstance().getReference("Consultant Request");
    Module User;
    String Uid ,sn , mg ;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String userid = user.getUid();
    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer);

        //Intent intent = getIntent();
        //if (intent.getExtras() != null) {
        //  sender = intent.getExtras().getString("SENDER");
        //  msg = intent.getExtras().getString("MSG");
        // }//inner if

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("public consultation");
        toolbar.setTitleTextColor(Color.WHITE);

        sender = findViewById(R.id.sendername);
        msg = findViewById(R.id.cccc);
        Answer = findViewById(R.id.Answer);

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            sn = intent.getExtras().getString("SENDER");
            sender.setText(intent.getExtras().getString("SENDER"));
            mg = intent.getExtras().getString("MSG");
            msg.setText(intent.getExtras().getString("MSG"));
           // consul = intent.getExtras().getString("Con");
        }//inner if


        Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.answer_dialog, null);

                androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(context);
                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                userInput = (EditText) promptsView
                        .findViewById(R.id.Answerdailoag);
                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Answer",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {


                                        final String input = userInput.getText().toString();
                                        final String Sender = sender.getText().toString();

                                        if(!(input.length()<=0)){



                                        mFirebaseInstance = FirebaseDatabase.getInstance();
                                        mFirebaseDatabase = mFirebaseInstance.getReference("Consultant Request");

                                        mFirebaseDatabase.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                ConModule m = dataSnapshot.getValue(ConModule.class);
                                                String consul = m.getName();
                                                reference.push().setValue(new anQ(input , consul, sn , mg));




                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }


                                        });







                                        Intent intent1 = new Intent(answer.this , answer2.class);
                                        intent1.putExtra("SENDER", sn);
                                        intent1.putExtra("MSG", mg);
                                        intent1.putExtra("answer",input);
                                        startActivity(intent1);





                                        }else{
                                            Toast.makeText(answer.this, "Couldn't answer , please enter a valid message to answer", Toast.LENGTH_LONG).show();
                                        }




                                    }








                                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                // create alert dialog
                androidx.appcompat.app.AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();
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
