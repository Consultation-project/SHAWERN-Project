package com.example.shawerni;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainListActivity extends AppCompatActivity {
    ListView listView;

    private UserInfo userInfo;
    String[] personNames = {"Abdulilah Almeghem","Islam Rajeb","Mosaad Almejel","Mohammed Abo Alazm","Abdullah Alabod"};


    int[] personImages = {R.drawable.abd, R.drawable.islamra, R.drawable.mosaadalm, R.drawable.mohammedabo, R.drawable.abdullahalab};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitylist_main);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setTitleTextColor(Color.WHITE);
        //finding listview
        listView = findViewById(R.id.listview);
        userInfo        = new UserInfo(this);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(),personNames[i],Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),ListdataActivity.class);
                //intent.putExtra("name",personNames[i]);

                String[] personCer = {"- Holds a bachelor's degree in law.\n" +
                        "- He holds many training courses in the fields of law and law.\n" +
                        "- Trainee lawyer\n"
                        ,
                        "- He holds a Bachelor of Laws from Cairo University.\n" +
                                "- He holds an MA in International Law from Cairo University.\n" +
                                "- He holds a Diploma of Islamic Sharia from Cairo University.\n" +
                                "- Appeal lawyer and member of the Egyptian Bar Association.\n"
                        ,
                        "- He holds a high diploma in American legal systems from the University of Cincinnati, USA.\n" +
                                "- He holds a master's degree in law from the University of Cincinnati, USA.\n" +
                                "- He holds a bachelor's degree in Sharia from Imam Muhammad bin Saud Islamic University\n"
                        ,
                        "- Holds a bachelor's degree from the Faculty of Law, Menoufia University, Egypt.\n" +
                                "- Holds specialized courses in international arbitration from the International Arbitration Judges Club in Cairo.\n" +
                                "- Member of the Egyptian Bar Association and a member of the Arab Lawyers Union\n" +
                                "- Member of the International Arbitration Judges Club in the Arab Republic of Egypt."
                        ,
                        "- He holds a bachelor's degree from Imam Muhammad bin Saud Islamic University (Sharia).\n" +
                                "- Legal Consultant at Al Salem Law Firm.\n" +
                                "- Legal Consultant at Durrat Al Ard Contracting (Landy).\n" };

                //  intent.putExtra("image",personImages[i]);
                userInfo.setKeyConid(personNames[i]);
                userInfo.setKeyImagename(personImages[i]);
                userInfo.setCer(personCer[i]);
                startActivity(intent);

            }
        });
    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return personImages.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.row_data,null);
            //getting view in row_data
            TextView name = view1.findViewById(R.id.fruits);
            ImageView image = view1.findViewById(R.id.images);

            name.setText(personNames[i]);
            image.setImageResource(personImages[i]);
            return view1;



        }
    }
}

