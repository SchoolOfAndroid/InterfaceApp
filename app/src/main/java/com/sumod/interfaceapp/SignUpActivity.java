package com.sumod.interfaceapp;


import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@EActivity(R.layout.activity_sign_up)
public class SignUpActivity extends AppCompatActivity {
    @ViewById(R.id.nameText)
    EditText nameText;

    @ViewById(R.id.emailText)
    EditText emailText;

    @ViewById(R.id.ed_user_phone)
    EditText userPhone;

    @ViewById(R.id.passwordText)
    EditText password;

    @ViewById(R.id.spinner_user_area)
    Spinner userArea;

    @ViewById(R.id.spinner_occupation)
    Spinner userOccupation;

    @ViewById(R.id.spinner_user_jobs)
    Spinner userJobs;

    @ViewById(R.id.spinner_business_set_up)
    Spinner businessSetUp;

    @ViewById(R.id.checkbox_find_jobs)
    CheckBox findJobs;

    @ViewById(R.id.checkbox_find_services)
    CheckBox findServices;

    @ViewById(R.id.checkbox_offer_jobs)
    CheckBox offerJobs;

    @ViewById(R.id.checkbox_offer_services)
    CheckBox offerServices;


    @Click(R.id.submit_button)
    protected void submitButtonClicked() {

        Call<String> call = Api.service.signup(
                nameText.getText().toString(),
                emailText.getText().toString(),
                userPhone.getText().toString(),
                password.getText().toString(),
                userArea.getSelectedItemPosition(),
                userOccupation.getSelectedItem().toString(),
                userJobs.getSelectedItem().toString(),
                businessSetUp.getSelectedItem().toString()
        );

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response) {
                if (response.isSuccess()) {
                    Toast.makeText(getApplicationContext(), "Your account is created", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Your account couldn't be created", Toast.LENGTH_LONG).show();
                }

            }


            @Override
            public void onFailure(Throwable t) {

                t.printStackTrace();
            }
        });
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
