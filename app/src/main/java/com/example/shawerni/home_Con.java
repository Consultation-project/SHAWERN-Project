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

    private List<Recieved_request> requestList = new ArrayList<>();
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
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference retreffq ;
    String consul;
    private CustomAdapter customAdapter;

    public home_Con() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.homefrag_con, container, false);

Log.d(TAG,"onCreate");

        prepareMovieData();




        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        requestList.get(position);
                        Intent intent = new Intent(getActivity(), answer.class);
                        intent.putExtra("SENDER", requestList.get(position).getSender());
                        intent.putExtra("MSG", requestList.get(position).getMsg());
                        intent.putExtra("Con", consul);
                        startActivity(intent);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }


                }));

        customAdapter = new CustomAdapter( requestList , context);
        recyclerView.setAdapter(customAdapter);

        return view;
    }

    private void prepareMovieData() {

        retreff = FirebaseDatabase.getInstance().getReference("User");
        retreff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {


                    if(ds.getValue(Module.class).getMsg()!= null) {
                        Module m = ds.getValue(Module.class);
                        senderS = m.getName();
                        idS = m.getId();
                        m1 = m.getMsg();
                        requestList.add(new Recieved_request(senderS, m1, idS));
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
