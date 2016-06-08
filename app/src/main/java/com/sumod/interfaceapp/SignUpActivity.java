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

@EActivity(R.layout.activity_sign_up)
public class SignUpActivity extends AppCompatActivity {

    @ViewById(R.id.ed_referrer_phone)
    EditText referrerPhone;

    @ViewById(R.id.ed_infosource)
    EditText infoSource;

    @ViewById(R.id.ed_user_phone)
    EditText userPhone;

    @ViewById(R.id.spinner_user_area)
    Spinner userArea;

    @ViewById(R.id.spinner_occupation)
    Spinner userOccupation;

    @ViewById(R.id.spinner_user_jobs)
    Spinner userJobs;

    @ViewById(R.id.spinner_business_set_up)
    Spinner businessSetUp;

    @ViewById(R.id.submit_button)
    Button submitButton;

    @Click(R.id.submit_button)
    protected void submitButtonClicked(){

        //This is where first OTP is verified and user is regstered.

    }

    @AfterViews
    protected void afterViews(){

        populateSpinner(userArea, R.array.areas_array);
        populateSpinner(userOccupation, R.array.occupations_array);
        populateSpinner(userJobs, R.array.jobs_array);
        populateSpinner(businessSetUp, R.array.business_setup_array);

    }

    private void populateSpinner(Spinner spinner, int array_id){

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                array_id, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);

    }
}
