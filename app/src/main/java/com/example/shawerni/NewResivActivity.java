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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;
import java.util.Date;

public class NewResivActivity extends AppCompatActivity {

    public EditText editTextName;
    public EditText editTextCountry;
    public EditText editTextWeight;
    public  static TextView textViewEmptyView;
    private static final String TAG = "NewEventActivity";
    private static final String REQUIRED = "Required";
    long cusertickit=0,counttickit=0;
    public ProgressBar myProgressBar;
    AlertDialog.Builder dialog;
    Calendar calendar;
    int hour,min;
    TimePicker picker;
    private EditText mChildCountField;
    private EditText mTeensCountField;
    private EditText mdateField;
    private EditText mtimeField;
    private EditText mAmountField;

    private Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_new_res);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Appointment Time and date");
        toolbar.setTitleTextColor(Color.WHITE);

        mTeensCountField = (EditText) findViewById(R.id.teenscount);
        mdateField = (EditText) findViewById(R.id.tickit_date);
        mtimeField = (EditText) findViewById(R.id.tickit_time);
        dialog = new AlertDialog.Builder(this);
        mSubmitButton = (Button) findViewById(R.id.fab_submit_post);
        mdateField .setOnClickListener(new View.OnClickListener() {
           @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
//                mAmountField.setText( String.valueOf((Integer.valueOf(  mChildCountField.getText().toString())*10)+(Integer.valueOf(  mTeensCountField.getText().toString())*20 )) );

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

                dialog.setMessage("Booking done successfully")
//
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                                startActivity(new Intent(NewResivActivity.this , MainActivity.class));
                                finish();
                            }
                        }).show();

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
        final DatePickerDialog dialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
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
        TimePickerDialog dialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                //set selected time to textview
                mtimeField.setText(updateTime(hour,min));
            }
        },hour,min,false);
        dialog.show();
    }
    private void submitPost() {
        int u=0;
        final String title = mChildCountField.getText().toString();
        final String body = mTeensCountField.getText().toString();
        final String evDate = mdateField.getText().toString();
        final String evTime = mtimeField.getText().toString();
        final String evLoc = mAmountField.getText().toString();


        // Title is required
        if (TextUtils.isEmpty(title)) {
            mChildCountField.setError(REQUIRED);
            return;
        }
        if (TextUtils.isEmpty(evTime)) {
            mtimeField .setError(REQUIRED);
            return;
        }
        if (TextUtils.isEmpty(evDate)) {
            mdateField .setError(REQUIRED);
            return;
        } if (TextUtils.isEmpty(evLoc)) {
            mAmountField.setError(REQUIRED);
            return;
        }
        // Body is required
        if (TextUtils.isEmpty(body)) {
            mTeensCountField.setError(REQUIRED);
            return;
        }

        setEditingEnabled(false);
        Toast.makeText(this, "Tickit Posting ...", Toast.LENGTH_SHORT).show();

        finish();

    }

    private void setEditingEnabled(boolean enabled) {
        mChildCountField.setEnabled(enabled);
        mTeensCountField.setEnabled(enabled);
        if (enabled) {
            mSubmitButton.setVisibility( View.VISIBLE);
        } else {

            mSubmitButton.setVisibility( View.GONE);
        }
    }

    private void writeNewTickit(String userId, String sub, String body, String evDate, String evtime, String evloc, String id) {

        Date d = new Date();
        CharSequence date  = android.text.format.DateFormat.format("d-MM-yyyy", d.getTime());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
