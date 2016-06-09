package com.sumod.interfaceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_find_job)
public class FindJobActivity extends AppCompatActivity {

    @ViewById(R.id.findJob_occupation)
    Spinner findJob_occupation;

    @ViewById(R.id.findJob_job)
    Spinner findJob_job;

    @ViewById(R.id.findJob_area)
    Spinner findJob_area;

    @ViewById(R.id.btn_findJobs)
    Button btn_findJobs;

    @Click(R.id.btn_findJobs)
    protected void gotoListJobsActivity(){
        //Here make a request to database asking for available job postings
        //according to the kind of job requested and then take the user to ListJobsActivity
        //which will be a listview listing all available jobs
    }

    @AfterViews
    protected void AfterViews(){
        populateSpinner(findJob_occupation, R.array.occupations_array);
        populateSpinner(findJob_area, R.array.areas_array);
        populateSpinner(findJob_job, R.array.jobs_array);
    }

    protected void populateSpinner(Spinner spinner, int array_id) {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                array_id, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);
    }
}