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
import com.sumod.interfaceapp.adapters.ProposalListAdapter;
import com.sumod.interfaceapp.model.Proposal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProposalsFragment extends Fragment {

    private ListView listView_proposals;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    public ProposalsFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProposalsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProposalsFragment newInstance(String param1, String param2) {
        ProposalsFragment fragment = new ProposalsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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

        listView_proposals = (ListView) view.findViewById(R.id.listView_proposals);
        populateListView();
    }


    protected void populateListView() {
        final ArrayList<Proposal> proposals = new ArrayList<>();

        Call<List<Proposal>> call = Api.service.getProposals(App.currentUser.id);

        call.enqueue(new Callback<List<Proposal>>() {
            @Override
            public void onResponse(Response<List<Proposal>> response) {
                proposals.addAll(response.body());
            }


            @Override
            public void onFailure(Throwable t) {

            }
        });

//        proposals.add(new Proposal("John", "RequestInfoHere", "Job"));
//        proposals.add(new Proposal("James", "RequestInfoHere", "Service"));
//        proposals.add(new Proposal("Jack", "RequestInfoHere", "Job"));
//        proposals.add(new Proposal("Jenna", "RequestInfoHere", "Service"));
//        proposals.add(new Proposal("Jacob", "RequestInfoHere", "Job"));

        ProposalListAdapter myProposalListAdapter = new ProposalListAdapter(getContext(), proposals);

        listView_proposals.setAdapter(myProposalListAdapter);
    }
}
