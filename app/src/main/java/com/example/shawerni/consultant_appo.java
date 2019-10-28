package com.example.shawerni;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class consultant_appo extends Fragment {
    private FirebaseAuth f1;
    private UserInfo userInfo;
    private DatabaseReference reservDatabaseReference;
    private DatabaseReference appointDatabaseReference;
    private DatabaseReference appointDatabaseReference2;

    View view;
    AlertDialog.Builder dialog;

    public String  Day,Month,Year,mname;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.consultant_appo, container, false);
        final FragmentActivity c = getActivity();
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.SearchList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(c);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        dialog = new AlertDialog.Builder(getContext());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new home.GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DatePickerTimeline timeline = view.findViewById(R.id.timeline);
        timeline.setDateLabelAdapter(new MonthView.DateLabelAdapter() {
            @Override
            public CharSequence getLabel(Calendar calendar, int index) {
                return Integer.toString(calendar.get( Calendar.MONTH) + 1) + "/" + (calendar.get( Calendar.YEAR) % 2000);
            }
        });



        f1 = FirebaseAuth.getInstance();

        final String userId = f1.getUid();
        userInfo        = new UserInfo(getContext());

        reservDatabaseReference= FirebaseDatabase.getInstance().getReference();
        appointDatabaseReference2 = FirebaseDatabase.getInstance().getReference();




        appointDatabaseReference2.child( "Consultant Request").child(userInfo.getKeyConId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {

                //  Appoint Nm = dataSnapshot.getValue(Appoint.class) ;
                System.out.println(dataSnapshot.getValue(ConModule.class).getName());
                 mname = dataSnapshot.getValue(ConModule.class).getName();
                Log.v("yyyyyyyyyyyyyyyyyyyy",dataSnapshot.getValue(ConModule.class).getName());



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        appointDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Consultant Request").child(userInfo.getKeyConId()).child("Appointments");
        appointDatabaseReference.keepSynced(true);

        timeline.setOnDateSelectedListener(new DatePickerTimeline.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int year, int month, int day, int index) {
                Day= String.valueOf(day);
                Month= String.valueOf(month);
                Year= String.valueOf(year);
                // final Query searchQuery = appointDatabaseReference.orderByChild( day+"-"+month+"-"+year );
                final Query searchQuery = appointDatabaseReference.child( day+"-"+(month+1)+"-"+year).orderByChild("status").equalTo("N");
                Log.v("",searchQuery.toString());


                //final Query searchQuery = peoplesDatabaseReference.orderByChild("search_name").equalTo(searchString);
                Toast.makeText( getContext(),searchQuery.toString(), Toast.LENGTH_SHORT ).show();
                Log.v( "kui" ,"uiktuiki");
                FirebaseRecyclerOptions<Appoint> recyclerOptions = new FirebaseRecyclerOptions.Builder<Appoint>()
                        .setQuery(searchQuery, Appoint.class)
                        .build();


                FirebaseRecyclerAdapter<Appoint, SearchPeopleVH> adapter = new FirebaseRecyclerAdapter<Appoint, SearchPeopleVH>(recyclerOptions) {
                    @Override
                    protected void onBindViewHolder(@NonNull SearchPeopleVH holder, final int position, @NonNull Appoint model) {
                        holder.status.setText(model.evtime);

                        /**on list >> clicking item, then, go to single user profile*/
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final DatabaseReference postRef = getRef(position);
                                final String postKey = postRef.getKey();

                               // appointDatabaseReference.child( Day +"-"+(Month+1)+"-"+Year).child(postKey);
                                Log.v("",appointDatabaseReference.child( Day +"-"+(Month+1)+"-"+Year).child(postKey).toString());
                            //   appointDatabaseReference.child( Day +"-"+(Integer.parseInt(Month)+1)+"-"+Year).child(postKey);


                                appointDatabaseReference.child( Day +"-"+String.valueOf(Integer.parseInt(Month)+1)+"-"+Year).child(postKey).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange( DataSnapshot dataSnapshot) {

                                          //  Appoint Nm = dataSnapshot.getValue(Appoint.class) ;
                                        System.out.println(dataSnapshot.getValue());
                                            final String name = dataSnapshot.child("userId").getValue().toString();
                                            final String date = dataSnapshot.child("evDate").getValue().toString();
                                            final String time = dataSnapshot.child("evtime").getValue().toString();
                                            String status = "not paid";
                                        Log.v("", dataSnapshot.child("evtime").getValue().toString());
                                        dialog.setMessage("Are you sure to Reserve?");
                                        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialoginterface, int i) {
                                                writeNewReserve(reservDatabaseReference, userInfo.getKeyConId(), date, time);
                                                updateAppo(appointDatabaseReference.child( Day +"-"+(Integer.parseInt(Month)+1)+"-"+Year).child(postKey));

                                            }
                                        });
                                        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialoginterface, int i) {

                                            }
                                        });
                                        dialog.show();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                                //  appointDatabaseReference.child( Day +"-"+(Month+1)+"-"+Year).child(postKey).child("status").setValue("Y");
                                String visit_user_id = getRef(position).getKey();
                                //Intent intent = new Intent( getActivity(), MainActivity.class);

                              //  intent.putExtra("visitUserId", visit_user_id);
                              //  startActivity(intent);


                            }
                        });


                    }

                    @NonNull
                    @Override
                    public SearchPeopleVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.all_single_date_display, viewGroup, false);
                        return new SearchPeopleVH(view);
                    }
                };
                recyclerView.setAdapter(adapter);
                searchPeopleProfile(day+"-"+month+"-"+year);

                adapter.startListening();
                adapter.notifyDataSetChanged();
                Toast.makeText( getContext(), Integer.toString(year )+ "/" + month +"/"+ day, Toast.LENGTH_SHORT ).show();
            }
        });



        timeline.setFirstVisibleDate(2019, Calendar.OCTOBER, 19);
        timeline.setLastVisibleDate(2020, Calendar.JULY, 19);
        return view;
    }

    private void writeNewReserve(DatabaseReference reservDatabaseReference, String userId, String evDate, String evtime) {
        userInfo        = new UserInfo(getContext());

        String key = reservDatabaseReference.child("Reservation").push().getKey();
        Reserve event = new Reserve(userId, evDate,evtime,key,"Not Paid",mname,userInfo.getKeyMethod());

        Map<String, Object> postValues = event.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/User/"+ userId + "/Reservation/" + key, postValues);

        reservDatabaseReference.updateChildren(childUpdates);
    }

    private void updateAppo(DatabaseReference postRef) {
        postRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                Appoint p = mutableData.getValue(Appoint.class);
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
    private void collectPhoneNumbers(Map<String,Object> market) {



        Log.d("tmz",""+market.values().toString());

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : market.entrySet()){

            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get phone field and append to list
            // Itemmarketlist.add((Long) singleUser.get("phone"));
            Log.d("tmz",""+singleUser.get("market_name"));
        }}
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void searchPeopleProfile(final String searchString) {
    }

    public static class SearchPeopleVH extends RecyclerView.ViewHolder{
        TextView name, status;
        public SearchPeopleVH(View itemView) {
            super(itemView);
            status = itemView.findViewById(R.id.all_user_status);

        }
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round( TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
/*               new AlertDialog.Builder(consultant_appo.this).setTitle("Reserve..").setMessage("Are you sure to resirve")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            logout();

                        }}).setNegativeButton("NO",null).show();

 */