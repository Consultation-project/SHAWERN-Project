package com.example.shawerni;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private UserInfo userInfo;

    static String ID;
    // TextView emailMenu ;
    //TextView UserMenue ;
    private FirebaseAuth firebaseAuth;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(Color.WHITE);

        userInfo        = new UserInfo(this);

       //UserMenue = findViewById(R.id.userdrawer);
        //UserMenue.setText(profile.Userdrawer);
        //emailMenu = findViewById(R.id.emaildrawer);
        //emailMenu.setText(profile.Emaildrawer);




        firebaseAuth = FirebaseAuth.getInstance();


        /*if(firebaseAuth.getCurrentUser()== null){
            finish();
            startActivity(new Intent(this , LoginActivity.class));
        }*/

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            setTitle(R.string.menu_home);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new home()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            getSupportFragmentManager().beginTransaction().replace(R.id.container,new home()).commit();
            setTitle(R.string.menu_home);


        } else if (id == R.id.nav_Profile) {

            getSupportFragmentManager().beginTransaction().replace(R.id.container,new profile()).commit();
            setTitle(R.string.menu_profile);

        } else if (id == R.id.nav_Wallet) {

            getSupportFragmentManager().beginTransaction().replace(R.id.container,new wallet()).commit();
            setTitle(R.string.menu_Wallet);

        } else if (id == R.id.nav_History) {

            getSupportFragmentManager().beginTransaction().replace(R.id.container,new history()).commit();
            setTitle(R.string.menu_History);

        } else if (id == R.id.nav_Settings) {

            getSupportFragmentManager().beginTransaction().replace(R.id.container,new settings()).commit();
            setTitle(R.string.menu_Settings);

        } else if (id == R.id.nav_ConcatUs) {

            getSupportFragmentManager().beginTransaction().replace(R.id.container,new concatus()).commit();
            setTitle(R.string.menu_ConcatUs);

        }else if (id == R.id.nav_Logout) {
            setTitle("Logout");

            new AlertDialog.Builder(MainActivity.this).setTitle("Logout..").setMessage("Are you sure to exit")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            userInfo.clearUserInfo();
                            logout();

                        }}).setNegativeButton("NO",null).show();

        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public  void logout(){


        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this , Registeration_sections.class));




    }

}
