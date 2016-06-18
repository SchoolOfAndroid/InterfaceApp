package com.sumod.interfaceapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sumod.interfaceapp.FindActivity_;
import com.sumod.interfaceapp.FindJobActivity_;
import com.sumod.interfaceapp.PostActivity_;
import com.sumod.interfaceapp.PostJobActivity_;
import com.sumod.interfaceapp.R;

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
                Intent intent = new Intent(getContext(), PostActivity_.class);
                startActivity(intent);
            }
        });

        fab_findJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FindActivity_.class);
                startActivity(intent);

            }
        });

    }
}
