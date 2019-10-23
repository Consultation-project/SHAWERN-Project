package com.example.shawerni;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class profile_Con extends Fragment implements View.OnClickListener {

    EditText userName;
    EditText email;
    EditText password;
    EditText phone;
    EditText age ;
    Button save;
    TextView userdrawer ;
    TextView emaildrawer ;
    private ProgressDialog progressDialog ;

    View view;

    DatabaseReference ref  = FirebaseDatabase.getInstance().getReference();
    DatabaseReference ref2  = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase b;
    FirebaseUser firebaseUser ;
    Module obj;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    final List<Module> mod = new ArrayList<Module>();
    private FirebaseAuth f1 = FirebaseAuth.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String userid = user.getUid();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
    final profile_Con context = this;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profilefrag_con, container, false);
        //ref= FirebaseDatabase.getInstance().getReference("User").child("User");



        progressDialog = new ProgressDialog(getContext());

        userName = (EditText) view.findViewById(R.id.eName);
        email = (EditText) view.findViewById(R.id.eEmail);
        password = (EditText) view.findViewById(R.id.ePassword);
        phone = (EditText) view.findViewById(R.id.ePhone);
        age = (EditText) view.findViewById(R.id.eeage);

        //userdrawer = view.findViewById(R.id.userdrawer);
        //emaildrawer=view.findViewById(R.id.emaildrawer);

        save = (Button) view.findViewById(R.id.save);
        mFirebaseInstance = FirebaseDatabase.getInstance();


        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("users");


        ref = FirebaseDatabase.getInstance().getReference("User");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();




        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                userName.setText(dataSnapshot.child("name").getValue().toString());
                //userdrawer.setText(dataSnapshot.child("name").getValue().toString());
                email.setText(dataSnapshot.child("email").getValue().toString());
                // emaildrawer.setText(dataSnapshot.child("email").getValue().toString());
                password.setText(dataSnapshot.child("password").getValue().toString());
                phone.setText(dataSnapshot.child("phoneNum").getValue().toString());
                age.setText(dataSnapshot.child("age").getValue().toString());




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




















        /*userName.setText(RegisterActivity.NAME);
        email.setText(RegisterActivity.EMAIL);
        password.setText(RegisterActivity.PASS);
        phone.setText(RegisterActivity.PHONE);*/

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                switch (view.getId()) {
                    case R.id.save:

                        if (checkDataEntered()) {


                            new AlertDialog.Builder(getContext()).setTitle("Update..").setMessage("Are you sure to update")
                                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            uploud();

                                        }}).setNegativeButton("NO",null).show();

                        }






////////////////////////////////////////////////////////////////////////////////////////////////////////


                        break;

                }

            } });

        return view;
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


        if (isEmpty((EditText) userName)&&(isEmpty(password))
        &&(isEmpty(phone))&& (isEmpty(age))&& (isEmpty(email))) {
            userName.setError("You must enter name!");
            password.setError("password is required!");
            phone.setError("Phone number is required!");
            age.setError("age is required!");
            email.setError("Enter valid email!");
            return false;
        }
        if (isEmpty((EditText) userName)) {
            userName.setError("You must enter name!");
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

        if (!isEmail(email)||(!email.getText().toString().substring(email.getText().toString().indexOf(".")+1).equals("com"))){
            email.setError("Enter valid email!");
            return false;
        }

        String MobilePattern = "[0-9]{10}";
        String num = "05";
        if ((!phone.getText().toString().matches(MobilePattern)) || (!phone.getText().toString().substring(0, 2).matches(num))) {
            phone.setError("not vaild , try again");
            return false;
        }

        if (isEmpty(phone)) {
            phone.setError("Phone number is required!");
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
        /*if ((Boolean) isEmpty(gender)) {
            gender.setError("gender is required!"); }*/
        return true;
    }

    void uploud (){
        progressDialog.setMessage("Updating...");
        progressDialog.show();
        ref2 = FirebaseDatabase.getInstance().getReference("User").child(f1.getUid());
        //Update profile
        HashMap<String , Object> map = new HashMap <>();
        map.put("name", userName.getText().toString());
        map.put("email",email.getText().toString());
        map.put("password",password.getText().toString());
        map.put("phoneNum",phone.getText().toString());
        map.put("age", age.getText().toString());
        ref2.updateChildren(map);
        //userdrawer.setText(userName.getText().toString());
        // emaildrawer.setText(email.getText().toString());

        progressDialog.dismiss();

        Toast.makeText(getContext(), "Updating your profile done Successfully", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View view) {



    }






}