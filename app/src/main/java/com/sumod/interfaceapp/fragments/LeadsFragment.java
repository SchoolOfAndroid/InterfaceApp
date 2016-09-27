package com.sumod.interfaceapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.sumod.interfaceapp.Api;
import com.sumod.interfaceapp.App;
import com.sumod.interfaceapp.MapsActivity;
import com.sumod.interfaceapp.NavigationDrawerActivity;
import com.sumod.interfaceapp.R;
import com.sumod.interfaceapp.adapters.LeadListAdapter;
import com.sumod.interfaceapp.model.Lead;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LeadsFragment extends Fragment {
    private ListView listView;
    private List<Lead> leads = new ArrayList<>();
    private LeadListAdapter adapter;
    private Button refreshButton;
    private FloatingActionButton fabMap;


    public LeadsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new LeadListAdapter(getContext(), leads);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_leads, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.listView_jobs);
        refreshButton = (Button) view.findViewById(R.id.refreshButton);
        fabMap = (FloatingActionButton) view.findViewById(R.id.fab_map);
        listView.setAdapter(adapter);
        populateListView();

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateListView();
            }
        });
        fabMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);

                startActivity(intent);
            }
        });
    }


    protected void populateListView() {
        leads.clear();

        Call<List<Lead>> call = Api.service.getLeads(App.currentUser.id,
                ((App.filter.findJobs & App.filter.offerJobs) ? null : (App.filter.findJobs ? 1 : 0)),
                ((App.filter.findServices & App.filter.offerServices) ? null : (App.filter.findServices ? 1 : 0)),
                ((App.filter.findProducts & App.filter.offerProducts) ? null : (App.filter.findProducts ? 1 : 0)),

                !(App.filter.findJobs | App.filter.offerJobs) ? 1 : 0,
                !(App.filter.findServices | App.filter.offerServices) ? 1 : 0,
                !(App.filter.findProducts | App.filter.offerProducts) ? 1 : 0
        );
        call.enqueue(new Callback<List<Lead>>() {
            @Override
            public void onResponse(Response<List<Lead>> response) {
                leads.clear();
                leads.addAll(response.body());
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
