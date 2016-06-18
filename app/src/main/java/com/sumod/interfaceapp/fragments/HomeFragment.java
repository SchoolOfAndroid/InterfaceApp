package com.sumod.interfaceapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sumod.interfaceapp.FindJobActivity_;
import com.sumod.interfaceapp.PostJobActivity_;
import com.sumod.interfaceapp.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private FloatingActionButton fab_postjob;

    private FloatingActionButton fab_findJob;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fab_findJob = (FloatingActionButton) view.findViewById(R.id.fab_findJob);
        fab_postjob = (FloatingActionButton) view.findViewById(R.id.fab_postjob);

        fab_postjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PostJobActivity_.class);
                startActivity(intent);
            }
        });

        fab_findJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FindJobActivity_.class);
                startActivity(intent);

            }
        });

    }
}
