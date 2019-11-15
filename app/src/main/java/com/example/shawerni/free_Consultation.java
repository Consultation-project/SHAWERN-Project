
package com.example.shawerni;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class free_Consultation extends AppCompatActivity {

    FloatingActionButton add;
    //EditText cons;
    RecyclerView recyclerView;

    DatabaseReference databaseReference;


    EditText userInput;

    FirebaseRecyclerAdapter<Consultation2, MyViewHolder> adapter;
    FirebaseRecyclerOptions<Consultation2> options;
    Query query;
    String user_id;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.free_consultation);
        user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();


        add = findViewById(R.id.add);
        recyclerView = findViewById(R.id.lv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        //cons=(EditText) findViewById(R.id.cons);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(" Free Consultation ");
        toolbar.setTitleTextColor(Color.BLACK);

        query = databaseReference = FirebaseDatabase.getInstance().getReference().child("Consultaion2");


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(free_Consultation.this);
                View promptsView = li.inflate(R.layout.note_dialog, null);

                androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(free_Consultation.this);
                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                userInput = promptsView
                        .findViewById(R.id.notedailoag);
                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Save",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        String input = userInput.getText().toString();
                                        if (TextUtils.isEmpty(input)) {
                                            userInput.setError("Enter Text");
                                            return;
                                        }
                                        Consultation2 consultation = new Consultation2();
                                        // Module module=new Module();
                                        consultation.setTextCons(input);
                                        // module.setMsg(input);
                                        consultation.setUser_id(user_id);
                                        consultation.setDate(Calendar.getInstance().getTime());

                                        databaseReference.push().setValue(consultation).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(free_Consultation.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });


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

        setUpRcyclerView();
    }

    private void setUpRcyclerView() {
        options = new FirebaseRecyclerOptions.Builder<Consultation2>().setQuery(query, Consultation2.class).build();
        adapter = new FirebaseRecyclerAdapter<Consultation2, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final MyViewHolder holder, int position, @NonNull final Consultation2 model) {
                holder.text2.setText(model.getTextCons());
                holder.date.setText(simpleDateFormat.format(model.getDate()));
                holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(free_Consultation.this, addfree.class);
                        intent.putExtra("name", holder.user_name.getText());
                        intent.putExtra("date", holder.date.getText());
                        intent.putExtra("text", holder.text2.getText());
                        startActivity(intent);


                    }
                });
                FirebaseDatabase.getInstance().
                        getReference("User").child(model.getUser_id()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Module module = dataSnapshot.getValue(Module.class);
                            holder.user_name.setText(module.getName());
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
                return new MyViewHolder(view);
            }
        };
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout linearLayout;
        TextView user_name, text2, date;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            user_name = itemView.findViewById(R.id.username);
            text2 = itemView.findViewById(R.id.text2);
            date = itemView.findViewById(R.id.date);
            linearLayout = itemView.findViewById(R.id.line5);
        }
    }
}
