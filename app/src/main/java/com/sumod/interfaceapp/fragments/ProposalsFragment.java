package com.sumod.interfaceapp.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.sumod.interfaceapp.Api;
import com.sumod.interfaceapp.App;
import com.sumod.interfaceapp.R;
import com.sumod.interfaceapp.adapters.ProposalListAdapter;
import com.sumod.interfaceapp.model.Proposal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProposalsFragment extends Fragment {
    private ListView listView;
    private ProposalListAdapter adapter;
    private final List<Proposal> proposals = new ArrayList<>();
    private Button refreshButton;


    public ProposalsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_proposals, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new ProposalListAdapter(getContext(), proposals);

        listView = (ListView) view.findViewById(R.id.listView_proposals);
        listView.setAdapter(adapter);
        populateListView();

        refreshButton = (Button) view.findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateListView();
            }
        });
    }


    protected void populateListView() {
        proposals.clear();

        Call<List<Proposal>> call = Api.service.getProposals(App.currentUser.id);
        call.enqueue(new Callback<List<Proposal>>() {
            @Override
            public void onResponse(Response<List<Proposal>> response) {
                proposals.clear();
                proposals.addAll(response.body());
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
