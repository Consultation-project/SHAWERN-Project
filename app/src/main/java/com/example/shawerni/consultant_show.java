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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class consultant_show extends Fragment {
    private FirebaseAuth f1;
    private UserInfo userInfo;
    private DatabaseReference reservDatabaseReference;
    private DatabaseReference appointDatabaseReference;
    private DatabaseReference appointDatabaseReference2;
    Button addapp;
    private FloatingActionButton feb;

    View view;
    AlertDialog.Builder dialog;

    public String  Day,Month,Year,mname;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.consultant_show, container, false);
        final FragmentActivity c = getActivity();
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.SearchList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(c);
        dialog = new AlertDialog.Builder(getContext());
        feb=view.findViewById(R.id.addapp);

        feb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               startActivity(new Intent(getActivity(), ActivityMyAppoint.class));
            }
        });
        recyclerView.setLayoutManager(layoutManager);
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






        appointDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Consultant Request").child(userId).child("Appointments");
        appointDatabaseReference.keepSynced(true);

        timeline.setOnDateSelectedListener(new DatePickerTimeline.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int year, int month, int day, int index) {
                if(day<10){
                    Day= String.valueOf("0"+day);
                    Month= String.valueOf("0"+month);

                }else{
                    Day= String.valueOf(day);
                    Month= String.valueOf(month);}

                Month= String.valueOf(month);
                Year= String.valueOf(year);
                // final Query searchQuery = appointDatabaseReference.orderByChild( day+"-"+month+"-"+year );
                final Query searchQuery = appointDatabaseReference.child( Day+"-"+(Integer.parseInt(Month)+1)+"-"+Year);
                Log.v("",Day+"-"+(month+1)+"-"+year);
                Log.v("",Day+"-"+(Integer.parseInt(Month)+1)+"-"+Year);


                //final Query searchQuery = peoplesDatabaseReference.orderByChild("search_name").equalTo(searchString);
                //Toast.makeText( getContext(),searchQuery.toString(), Toast.LENGTH_SHORT ).show();
                Log.v( "kui" ,"uiktuiki");
                FirebaseRecyclerOptions<Appoint> recyclerOptions = new FirebaseRecyclerOptions.Builder<Appoint>()
                        .setQuery(searchQuery, Appoint.class)
                        .build();


                FirebaseRecyclerAdapter<Appoint, SearchPeopleVH> adapter = new FirebaseRecyclerAdapter<Appoint, SearchPeopleVH>(recyclerOptions) {
                    @Override
                    protected void onBindViewHolder(@NonNull SearchPeopleVH holder, final int position, @NonNull Appoint model) {
                       // holder.mdate.setText(model.evDate);
                        holder.mtime.setText(model.evtime);

                        if (model.status.equals("Y")){
                            holder.mdel.setVisibility(View.GONE);
                    }
                        /**on list >> clicking item, then, go to single user profile*/
                        holder.mdel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final DatabaseReference postRef = getRef(position);
                                final String postKey = postRef.getKey();

                               // appointDatabaseReference.child( Day +"-"+(Month+1)+"-"+Year).child(postKey);
                                Log.v("",appointDatabaseReference.child(  Day+"-"+(Integer.parseInt(Month)+1)+"-"+Year).child(postKey).toString());
                            //   appointDatabaseReference.child( Day +"-"+(Integer.parseInt(Month)+1)+"-"+Year).child(postKey);
                                dialog.setMessage("Are you sure to delete the appoiment?");
                                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialoginterface, int i) {
                                        appointDatabaseReference.child(Day+"-"+(Integer.parseInt(Month)+1)+"-"+Year).child(postKey).removeValue();
                                    }
                                });
                                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialoginterface, int i) {

                                    }
                                });
                                dialog.show();

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
                        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.myappoint_display, viewGroup, false);
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



        timeline.setFirstVisibleDate(2019, Calendar.NOVEMBER, 11);
        timeline.setLastVisibleDate(2019, Calendar.DECEMBER, 4);
        return view;
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void searchPeopleProfile(final String searchString) {
    }

    public static class SearchPeopleVH extends RecyclerView.ViewHolder{
        TextView mdate, mtime;
        CircleImageView mdel;
        public SearchPeopleVH(View itemView) {
            super(itemView);
            mtime = itemView.findViewById(R.id.appoint_time);
           // mdate = itemView.findViewById(R.id.appdate);
            mdel = itemView.findViewById(R.id.delappoint);


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