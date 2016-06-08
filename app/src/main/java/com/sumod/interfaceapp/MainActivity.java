package com.sumod.interfaceapp;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
