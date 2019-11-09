package com.example.shawerni;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.github.badoualy.datepicker.DatePickerTimeline;
import com.github.badoualy.datepicker.MonthView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class wallet extends Fragment {
    private FirebaseAuth f1;
    private UserInfo userInfo;
    Button paidbtn;

    private DatabaseReference appointDatabaseReference;
    View view;

    public String  Day,Month,Year;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.reserv_appo, container, false);
        final FragmentActivity c = getActivity();
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.SearchList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(c);


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        paidbtn=view.findViewById(R.id.paid);

        f1 = FirebaseAuth.getInstance();

        final String userId = f1.getUid();
        userInfo = new UserInfo(getContext());


        appointDatabaseReference = FirebaseDatabase.getInstance().getReference().child( "User").child(userId).child("Reservation");
        appointDatabaseReference.keepSynced(true);



        final Query searchQuery = appointDatabaseReference;
        //Log.v("",searchQuery.toString());


        //  Toast.makeText( getContext(),searchQuery.toString(), Toast.LENGTH_SHORT ).show();
        Log.v( "kui" ,"uiktuiki");
        FirebaseRecyclerOptions<Reserve> recyclerOptions = new FirebaseRecyclerOptions.Builder<Reserve>()
                .setQuery(searchQuery, Reserve.class)
                .build();

        FirebaseRecyclerAdapter<Reserve, walet> adapter = new FirebaseRecyclerAdapter<Reserve, walet>(recyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull walet holder, final int position, @NonNull Reserve model) {
                holder.status.setText(model.status);
                holder.evtime.setText(model.evtime);
                holder.evdate.setText(model.evDate);
                holder.name.setText(model.conName);
                holder.appointconnection.setText(model.appointconnection);

                holder.paid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final DatabaseReference postRef = getRef(position);
                        final String postKey = postRef.getKey();


                        String visit_user_id = getRef(position).getKey();
                        Intent intent = new Intent( getActivity(), payment_information.class);


                         startActivity(intent);


                    }
                });


                /**on list >> clicking item, then, go to single user profile*/
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final DatabaseReference postRef = getRef(position);
                        final String postKey = postRef.getKey();

                        // appointDatabaseReference.child( Day +"-"+(Month+1)+"-"+Year).child(postKey);
                        //  Log.v("",appointDatabaseReference.child( Day +"-"+(Month+1)+"-"+Year).child(postKey).toString());
                        //  appointDatabaseReference.child( Day +"-"+(Integer.parseInt(Month)+1)+"-"+Year).child(postKey);



                        //  appointDatabaseReference.child( Day +"-"+(Month+1)+"-"+Year).child(postKey).child("status").setValue("Y");
                        //   updateAppo(appointDatabaseReference.child( Day +"-"+(Integer.parseInt(Month)+1)+"-"+Year).child(postKey));
                        String visit_user_id = getRef(position).getKey();
                        Intent intent = new Intent( getActivity(), MainActivity.class);

                        intent.putExtra("visitUserId", visit_user_id);
                        startActivity(intent);


                    }
                });
            }

            @NonNull
            @Override
            public walet onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.all_single_reserv_display, viewGroup, false);
                return new walet(view);
            }
        };
        recyclerView.setAdapter(adapter);
        //  searchPeopleProfile("");

        adapter.startListening();
        adapter.notifyDataSetChanged();
        // Toast.makeText( getContext(), Integer.toString(year )+ "/" + month +"/"+ day, Toast.LENGTH_SHORT ).show();

        return view;
    }


    private void updateAppo(DatabaseReference postRef) {
        postRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                Reserve p = mutableData.getValue(Reserve.class);
                if (p == null) {
                    return Transaction.success(mutableData);
                }

                p.status = "Y";


                // Set value and report transaction success
                mutableData.setValue(p);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

            }
        });
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }



    public static class walet extends RecyclerView.ViewHolder{
        TextView name, evdate,evtime,status,appointconnection;
        Button paid;
        public walet(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.conname);
            evdate = itemView.findViewById(R.id.appodate);
            evtime = itemView.findViewById(R.id.appotime);
            status = itemView.findViewById(R.id.appostatus);
            appointconnection = itemView.findViewById(R.id.appocommunication);
            //method = itemView.findViewById(R.id.appocommunication);
            paid = itemView.findViewById(R.id.paid);

            //Type of communication
        }
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round( TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
