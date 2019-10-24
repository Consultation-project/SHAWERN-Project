package com.example.shawerni;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class wallet extends Fragment {

    private static final String TAG = "Activity";

    private View view;
    private ArrayList<String> nName = new ArrayList<> ();
    private ArrayList<ConModule> con1 = new ArrayList<> ();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.payconsultationlist, container, false);


        Log.d (TAG, "onCreate");

        //  Ename();

       //  int counter = 1;

        nName.add ("Consultation ");

        inRecycle();

        return view;
    }


    private void inRecycle() {
        RecyclerView recyclerView = view.findViewById (R.id.recycler_view);
        MyReclyecon myr = new MyReclyecon (nName, getContext ());
        recyclerView.setAdapter (myr);
        recyclerView.setLayoutManager (new LinearLayoutManager (getContext ()));


    }
}