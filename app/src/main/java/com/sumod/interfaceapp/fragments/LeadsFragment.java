package com.sumod.interfaceapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sumod.interfaceapp.Api;
import com.sumod.interfaceapp.PostJobActivity_;
import com.sumod.interfaceapp.R;
import com.sumod.interfaceapp.adapters.JobListAdapter;
import com.sumod.interfaceapp.model.Job;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeadsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;


    private ListView listView_jobs;
    private FloatingActionButton fab_postjob;

    public static final String EXTRA_JOB = ".JOB";
    public static final String EXTRA_NEED = ".NEED";
    public static final String EXTRA_LOCATION = ".LOC";

    public LeadsFragment() {
        // Required empty public constructor
    }

    public static LeadsFragment newInstance(String param1, String param2) {
        LeadsFragment fragment = new LeadsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leads, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView_jobs = (ListView) view.findViewById(R.id.listView_jobs);

        fab_postjob = (FloatingActionButton) view.findViewById(R.id.fab_postjob);
        fab_postjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PostJobActivity_.class);
                startActivity(intent);
            }
        });

        populateListView();
    }


    protected void populateListView() {

        ArrayList<Job> jobList = new ArrayList<>();

        jobList.add(new Job(0, "Plumber", "Toilet Plumbing"));
        jobList.add(new Job(1, "Carpenter", "Fix front door"));
        jobList.add(new Job(2, "Teacher", "Math tuition"));
        jobList.add(new Job(3, "Electrician", "Change light bulb"));
        jobList.add(new Job(4, "Watchman", "Guard my house"));
        jobList.add(new Job(5, "Cableguy", "Fix the damn cable"));

        JobListAdapter myJobListAdapter = new JobListAdapter(getContext(), jobList);

        listView_jobs.setAdapter(myJobListAdapter);

    }
}
