package com.example.shawerni;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class free_Consultation extends AppCompatActivity  {


    FloatingActionButton add;
    int x ;
    //EditText cons;
    ListView listView;
    Consultation note;
    ArrayList<String> arrayList = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;
    DatabaseReference databaseReference;
    //var2
    DatabaseReference retreff ;

    FirebaseDatabase database;
    ArrayAdapter<String> arrayAdapter2;
    ArrayList<String> list2;
    //private ProgressDialog progressDialog2;
    final Context context = this;
    EditText userInput;
    //new varb


    // private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.free_consultation);

        add=(FloatingActionButton)findViewById(R.id.add);
        listView=(ListView)findViewById(R.id.lv);
        //cons=(EditText) findViewById(R.id.cons);


        //progressDialog2 = new ProgressDialog(this);


        arrayAdapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_expandable_list_item_1,arrayList);
        note=new Consultation();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Consultaion");

        //update




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//d
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.note_dialog, null);

                androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(context);
                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                userInput = (EditText) promptsView
                        .findViewById(R.id.notedailoag);
                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Save",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {

                                        String input=userInput.getText().toString();
                                        arrayList.add(input);
                                        note.setTextCons(input.trim());
                                        databaseReference.push().setValue(note);
                                        Toast.makeText(free_Consultation.this," consultation is added",Toast.LENGTH_LONG).show();
                                        listView.setAdapter(arrayAdapter);
                                        arrayAdapter.notifyDataSetChanged();
                                        database=FirebaseDatabase.getInstance();
                                        retreff=database.getReference("Consultaion");
                                        list2=new ArrayList<>();
                                        // arrayAdapter2=new ArrayAdapter<String>(free_Consultation.this,R.layout.note_dialog,R.id.notedailoag,list2);
                                        retreff.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                for(DataSnapshot ds: dataSnapshot.getChildren()){

                                                    note=ds.getValue(Consultation.class);
                                                    arrayList.add(note.getTextCons().toString()+ "  ");
                                                    //update





                                                    //update


                                                }

                                                listView.setAdapter(arrayAdapter);

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

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


                //d


                //d



            }
        });
        //ter




        //ret*/


    }

    /**
     * Inserting new note in db
     * and refreshing the list
     */
    private void createNote(String note) {
        // inserting note in db and getting
        // newly inserted note id
        //long id = db.insertNote(note);

        // get the newly inserted note from d
        // Note n = db.getNote(id);

       /* if (n != null) {
            // adding new note to array list at 0 position
            notesList.add(0, n);

            // refreshing the list
            mAdapter.notifyDataSetChanged();

            toggleEmptyNotes();
        }
    }

    /**
     * Updating note in db and updating
     * item in the list by its position
     */


        // updating note in db
       /* db.updateNote(n);

        // refreshing the list
        notesList.set(position, n);
        mAdapter.notifyItemChanged(position);

        toggleEmptyNotes();
    }

    /**
     * Deleting note from SQLite and removing the
     * item from the list by its position
     */
   /* private void deleteNote(int position) {
        // deleting the note from db
       db.deleteNote(notesList.get(position));

        // removing the note from the list
        notesList.remove(position);
        mAdapter.notifyItemRemoved(position);

        toggleEmptyNotes();
    }*/

        /**
         * Opens dialog with Edit - Delete options
         * Edit - 0
         * Delete - 0
         */
        // private void showActionsDialog(final int position) {
    }

    /**
     * Shows alert dialog with EditText options to enter / edit
     * a note.
     * when shouldUpdate=true, it automatically displays old note and changes the
     * button text to UPDATE
     */
    //private void showNoteDialog(final boolean shouldUpdate, final Note note, final int position) {
}