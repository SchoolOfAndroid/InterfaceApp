package com.sumod.interfaceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_post_job)
public class PostJobActivity extends AppCompatActivity {

    @ViewById(R.id.jobPosting_need)
    Spinner jobPosting_need;

    @ViewById(R.id.jobPosting_job)
    Spinner jobPosting_job;

    @ViewById(R.id.jobPosting_description)
    EditText jobPosting_description;

    @ViewById(R.id.jobPosting_area)
    Spinner jobPosting_area;

    @ViewById(R.id.btn_postJob)
    Button button_postJob;

    @Click(R.id.btn_postJob)
    protected void postJob(){
        //Enter post job algorithm here
    }


    @AfterViews
    protected void afterViews(){

        populateSpinner(jobPosting_need, R.array.occupations_array);
        populateSpinner(jobPosting_job, R.array.jobs_array);
        populateSpinner(jobPosting_area, R.array.areas_array);

    }

    private void populateSpinner(Spinner spinner, int array_id){

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                array_id, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);

    }
}
