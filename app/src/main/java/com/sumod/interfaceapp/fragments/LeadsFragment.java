package com.sumod.interfaceapp.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sumod.interfaceapp.Api;
import com.sumod.interfaceapp.App;
import com.sumod.interfaceapp.R;
import com.sumod.interfaceapp.adapters.LeadListAdapter;
import com.sumod.interfaceapp.model.Lead;

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
//    private FloatingActionButton fab_postjob;

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
        populateListView();
    }


    protected void populateListView() {
        final ArrayList<Lead> leadLists = new ArrayList<>();

        Call<List<Lead>> call = Api.service.getLeads(App.currentUser.id);

        call.enqueue(new Callback<List<Lead>>() {
            @Override
            public void onResponse(Response<List<Lead>> response) {
                leadLists.addAll(response.body());
            }


            @Override
            public void onFailure(Throwable t) {

            }
        });

        LeadListAdapter myLeadListAdapter = new LeadListAdapter(getContext(), leadLists);
        listView_jobs.setAdapter(myLeadListAdapter);
    }
}
