package com.example.shawerni;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NewAppointActivity  extends Fragment implements View.OnClickListener {

    public EditText editTextName;
    public EditText editTextCountry;
    public EditText editTextWeight;
    private DatabaseReference mDatabase;
String over;
    public  static TextView textViewEmptyView;
    private static final String TAG = "NewEventActivity";
    private static final String REQUIRED = "Required";
    long cusertickit=0,counttickit=0;
    public ProgressBar myProgressBar;
    AlertDialog.Builder dialog;
    Calendar calendar;
    int hour,min;
    TimePicker picker;
    private FirebaseAuth f1;
    private EditText mdateField;
    private EditText mtimeField;

    private Button mSubmitButton;
    View view;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_new_appoint, container, false);
        //ref= FirebaseDatabase.getInstance().getReference("User").child("User");

        Toolbar toolbar = view.findViewById(R.id.toolbar2);


       // setSupportActionBar(toolbar);
         f1 = FirebaseAuth.getInstance();
     //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      // getSupportActionBar().setDisplayShowHomeEnabled(true);
     //  setTitle("Appointment Time and date");
        toolbar.setTitleTextColor(Color.WHITE);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mdateField = (EditText) view.findViewById(R.id.appoint_date);
        mtimeField = (EditText) view.findViewById(R.id.appoint_time);
        dialog = new AlertDialog.Builder(getContext());
        mSubmitButton = (Button) view.findViewById(R.id.fab_submit_post);
        mdateField .setOnClickListener(new View.OnClickListener() {
           @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
// mAmountField.setText( String.valueOf((Integer.valueOf(  mChildCountField.getText().toString())*10)+(Integer.valueOf(  mTeensCountField.getText().toString())*20 )) );
                opendPickerDialog();
            }
        });
        mtimeField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimePickerDialog();
            }
        });

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPost();
                dialog.setMessage("You add the appointment successfully")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                             //   startActivity(new Intent( NewAppointActivity.this , MainActivity.class));
                              //  finish();
                            }
                        }).show();
            }
        });
        return view;

    }
    private void submitPost() {
        int u=0;

        final String evDate = mdateField.getText().toString();
        final String evTime = mtimeField.getText().toString();



        // Title is required

        if (TextUtils.isEmpty(evTime)) {
            mtimeField .setError(REQUIRED);
            return;
        }
        if (TextUtils.isEmpty(evDate)) {
            mdateField .setError(REQUIRED);
            return;
        }
        // Body is required

        final String userId = f1.getUid();

        setEditingEnabled(false);
        //Toast.makeText(getContext(), "Tickit Posting ...", Toast.LENGTH_SHORT).show();
        writeNewAppoint("",userId,evDate,evTime);
       // finish();



    }
    private void writeNewAppoint(String id, String userId, String evDate, String evtime) {

        Date d = new Date();
        String sDate1=mdateField.getText().toString();
        Date date1= null;
        try {
            date1 = new SimpleDateFormat("dd-MM-yyyy").parse( sDate1 );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        CharSequence date  = android.text.format.DateFormat.format("dd-MM-yyyy",date1 );//

        String key = mDatabase.child("Appointments").push().getKey();
        Appoint event = new Appoint(userId, evDate,evtime,key,"N");
        Map<String, Object> postValues = event.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/Consultant Request/"+ userId + "/Appointments/" + evDate + "/"+ key, postValues);

        mDatabase.updateChildren(childUpdates);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {

          //  onBackPressed();

            // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);

    }

    private String updateTime(int hours, int mins) {
        String timeSet = "";
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12)
            timeSet = "PM";
        else
            timeSet = "AM";
        String minutes = "";
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);
        // Append in a StringBuilder
        String aTime = new StringBuilder().append(hours).append(':')
                .append(minutes).append(" ").append(timeSet).toString();
        return aTime;
    }
    private String updateTime2(int hours, int mins) {
        String timeSet = "";
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12)
            timeSet = "PM";
        else
            timeSet = "AM";
        String minutes = "";
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);
        // Append in a StringBuilder
        String aTime = new StringBuilder().append(hours).append(':')
                .append(minutes).append(" ").append(timeSet).toString();
        return aTime;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void opendPickerDialog()
    {


        Calendar newCalendar= new Calendar() {
            @Override
            protected void computeTime() {

            }

            @Override
            protected void computeFields() {

            }

            @Override
            public void add(int i, int i1) {

            }

            @Override
            public void roll(int i, boolean b) {

            }

            @Override
            public int getMinimum(int i) {
                return 0;
            }

            @Override
            public int getMaximum(int i) {
                return 0;
            }

            @Override
            public int getGreatestMinimum(int i) {
                return 0;
            }

            @Override
            public int getLeastMaximum(int i) {
                return 0;
            }
        };
        final Calendar newDate = Calendar.getInstance();
        newDate.setTime(new Date(  ));
        final DatePickerDialog dialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                newDate.set( year, monthOfYear, dayOfMonth );
                CharSequence date  = android.text.format.DateFormat.format("dd-MM-yyyy", newDate.getTime());

                mdateField.setText(date ) ;
            }
        }, newCalendar.get(newCalendar.YEAR), newCalendar.get(newCalendar.MONTH), newCalendar.get(newCalendar.DAY_OF_MONTH));
        dialog.getDatePicker().setMaxDate( System.currentTimeMillis()+999999990+999999990);
        dialog.getDatePicker().setMinDate( System.currentTimeMillis() - 1000);
        dialog.show();

    }

    private void openTimePickerDialog()
    {
        TimePickerDialog dialog=new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                //set selected time to textview
                mtimeField.setText(updateTime(hour,min)+"-"+updateTime2((hour+1),min));
                //over=
            }
        },hour,min,false);
        dialog.show();
    }

    private void setEditingEnabled(boolean enabled) {

        if (enabled) {
            mSubmitButton.setVisibility( View.VISIBLE);
        } else {

            mSubmitButton.setVisibility( View.GONE);
        }
    }




    @Override
    public void onClick(View v) {

    }
}
