package com.sumod.interfaceapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.jobPosting_need)
    Spinner jobPosting_need;

    @ViewById(R.id.jobPosting_job)
    Spinner jobPosting_job;

    @ViewById(R.id.jobPosting_description)
    EditText jobPosting_description;

    @ViewById(R.id.jobPosting_area)
    Spinner jobPosting_area;

    @ViewById(R.id.fab_postjob)
    FloatingActionButton fab_postjob;

    @ViewById(R.id.fab_findJob)
    FloatingActionButton fab_findJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @AfterViews
    protected void afterViews(){

        fab_postjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PostJobActivity_.class);
                startActivity(intent);
            }
        });

        fab_findJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Enter code to go to FindJobActivity
            }
        });

    }
}
