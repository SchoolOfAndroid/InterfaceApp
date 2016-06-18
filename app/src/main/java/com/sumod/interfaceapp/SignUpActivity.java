package com.sumod.interfaceapp;


import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sumod.interfaceapp.model.User;

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
        String android_id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);

        Call<User> call = Api.service.signup(
                nameText.getText().toString(),
                userPhone.getText().toString(),
                android_id,
                businessSetUp.getSelectedItem().toString(),
                1,
                0, 0,
                1, null,
                0, 0
        );


        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response) {
                if (response.isSuccess()) {
                    Toast.makeText(getApplicationContext(), "Your account is created", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Your account is already registered", Toast.LENGTH_LONG).show();
                }

            }


            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
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
