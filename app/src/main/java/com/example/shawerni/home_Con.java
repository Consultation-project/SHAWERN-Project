package com.example.shawerni;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class home_Con extends Fragment {

    private ArrayList<Recieved_request> requestList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CustomAdapter mAdapter;
    private Context context;
    private Recieved_request recievedRequest;
    private View view;
    DatabaseReference retreff;
    private TextView sender;
    private TextView msg;
    private String m1;
    private String id;
    private  String uid;
    private String senderS;
    private String idS;
    private ArrayList<String> MSG = new ArrayList<>();
    private final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("User");

    public home_Con() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.homefrag_con, container, false);

Log.d(TAG,"onCreate");

        prepareMovieData();


         //myRef.child().push().child("Consultation").setValue("what is the best antihistamin drug for...");

        return view;
    }

    private void prepareMovieData() {

        retreff = FirebaseDatabase.getInstance().getReference("User");
        retreff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {


                    Module m = ds.getValue(Module.class);
                    senderS = m.getName();
                    idS = m.getId();
                    m1 = m.getMsg();


                    if (m1 != null) {
                        requestList.add(new Recieved_request(senderS, m1, idS));
                        inRecycle();
                    }

                    if (m1 == null){
                        requestList.add(new Recieved_request(senderS, "no", idS));
                    inRecycle();
                }
                                                          }
                                                      }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });



    }




    private void inRecycle() {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        CustomAdapter myr = new CustomAdapter(requestList, getContext());
        recyclerView.setAdapter(myr);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }
}
