package com.sumod.interfaceapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_sign_up)
public class SignUpActivity extends AppCompatActivity {
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


    @Click(R.id.submit_button)
    protected void submitButtonClicked() {

        // This is where first OTP is verified and user is registered.
        Toast.makeText(this, "Your account is created", Toast.LENGTH_LONG).show();
//        finishActivity();
//        Intent intent = new Intent(getApplicationContext(), MainActivity_.class);
//        startActivity(intent);
        finish();
    }


    @AfterViews
    protected void afterViews() {
        populateSpinner(userArea, R.array.areas_array);
        populateSpinner(userOccupation, R.array.occupations_array);
        populateSpinner(userJobs, R.array.jobs_array);
        populateSpinner(businessSetUp, R.array.business_setup_array);
    }


    private void populateSpinner(Spinner spinner, int array_id) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                array_id, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);
    }
}
