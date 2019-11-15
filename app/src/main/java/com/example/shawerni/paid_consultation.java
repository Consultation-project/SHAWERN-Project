package com.example.shawerni;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class paid_consultation extends AppCompatActivity {

    ListView listView;
    MyReclyecon myr ;
    ArrayList personCer = new ArrayList();

    private UserInfo userInfo;
    String[] personNames = {"Abdulilah Almeghem","Islam Rajeb","Mosaad Almejel","Mohammed Abo Alazm","Abdullah Alabod"};


    int[] personImages = {R.drawable.abd,R.drawable.islamra,R.drawable.mosaadalm,R.drawable.mohammedabo,R.drawable.abdullahalab};
    private String departmentName;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitylist_main);
        //finding listview

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        setTitle(R.string.paidCon);
        toolbar.setTitleTextColor(Color.WHITE);

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            departmentName = intent.getExtras().getString(Constance.key.TITLE);
            if (departmentName != null) {
                setTitle(getIntent().getStringExtra(Constance.key.TITLE));
            }//inner if


        }//outer if


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

                personCer.add("b");
                personCer.add("b");
                personCer.add("b");
                personCer.add("c");
                personCer.add("G");
                personCer.add("n");
                personCer.add("l");
                personCer.add("g");


                //  intent.putExtra("image",personImages[i]);
               // userInfo.setKeyConid(personNames[i]);
               // userInfo.setKeyImagename(personImages[i]);
               // userInfo.setCer(personCer[i]);
                startActivity(intent);

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search_bar,menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String userinput = newText.toLowerCase();
                String[] s2 = new String[100];
                int i = 0;

                for (String s : personNames) {

                    if ((s.toLowerCase().contains(userinput))) {
                        if ((s.contains(userinput))) {
                            s2[i++] = s;
                        }
                    }}
                    // myr.getFilter().filter(newText);
                    //   ArrayList<String> m = new ArrayList<>()
                    //myr.updat(s2, userinput);
                    return false;

            }});

        return true;
    }

}
