package com.sumod.interfaceapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.sumod.interfaceapp.adapters.LeadListAdapter;
import com.sumod.interfaceapp.model.Job;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@EActivity(R.layout.activity_job_list)
public class JobListActivity extends AppCompatActivity {

    @ViewById(R.id.listView_jobs)
    ListView listView_jobs;

    public static final String EXTRA_JOB = ".JOB";
    public static final String EXTRA_NEED = ".NEED";
    public static final String EXTRA_LOCATION = ".LOC";


    @AfterViews
    protected void populateListView() {
        final ArrayList<Job> jobList = new ArrayList<>();

        Intent intent = getIntent();
        int location = intent.getIntExtra(EXTRA_LOCATION, -1);
        String job = intent.getStringExtra(EXTRA_JOB);
        String need = intent.getStringExtra(EXTRA_NEED);

        if (job.equals("Select Job")) job = null;
        if (need.equals("Select Occupation")) need = null;

        Call<List<Job>> call = Api.service.listJobs(need, job, location);
        call.enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Response<List<Job>> response) {
                jobList.addAll(response.body());

//                LeadListAdapter myLeadListAdapter = new LeadListAdapter(JobListActivity.this, jobList);
//                listView_jobs.setAdapter(myLeadListAdapter);
            }


            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
