package com.sumod.interfaceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.sumod.interfaceapp.adapters.JobListAdapter;
import com.sumod.interfaceapp.model.Job;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;

@EActivity(R.layout.activity_job_list)
public class JobListActivity extends AppCompatActivity {

    @ViewById(R.id.listView_jobs)
    ListView listView_jobs;

    @AfterViews
    protected void afterViews(){
        populateListView();
    }



    private void populateListView(){

        ArrayList<Job> jobList = new ArrayList<>();

        jobList.add(new Job(0, "Plumber", "Toilet Plumbing"));
        jobList.add(new Job(1, "Carpenter", "Fix front door"));
        jobList.add(new Job(2, "Teacher", "Math tuition"));
        jobList.add(new Job(3, "Electrician", "Change light bulb"));
        jobList.add(new Job(4, "Watchman", "Guard my house"));
        jobList.add(new Job(5, "Cableguy", "Fix the damn cable"));

        JobListAdapter myJobListAdapter = new JobListAdapter(this, jobList);

        listView_jobs.setAdapter(myJobListAdapter);

    }

}
