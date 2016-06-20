package com.sumod.interfaceapp;


import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
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

    @ViewById(R.id.spinner_user_area) Spinner userArea;
    @ViewById(R.id.spinner_business_set_up) Spinner businessSetUp;

    @ViewById(R.id.jobPosting_need) Spinner jobPosting_need;
    @ViewById(R.id.jobPosting_job) Spinner jobPosting_job;

    @ViewById(R.id.service_spinner_name) Spinner serviceName;
    @ViewById(R.id.service_spinner_occup) Spinner serviceOccup;

    @ViewById(R.id.products_spinner_ch) Spinner productsChannel;
    @ViewById(R.id.products_spinner_name) Spinner productsName;


    Integer getVal(Spinner spn) {
        if (spn.getSelectedItemPosition() == 0) return null;
        else return spn.getSelectedItemPosition();
    }


    @Click(R.id.submit_button)
    protected void submitButtonClicked() {
        String android_id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);

        Call<User> call = Api.service.signup(
                nameText.getText().toString(),
                userPhone.getText().toString(),
                android_id,
                businessSetUp.getSelectedItem().toString(),

                getVal(userArea),
                getVal(jobPosting_job), getVal(jobPosting_need),
                getVal(serviceOccup), getVal(serviceName),
                getVal(productsChannel), getVal(productsName)
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
        populateSpinner(businessSetUp, R.array.business_setup_array);

        populateSpinner(jobPosting_need, R.array.job_roles_array);
        populateSpinner(jobPosting_job, R.array.job_sectors_array);

        populateSpinner(serviceName, R.array.service_name_array);
        populateSpinner(serviceOccup, R.array.service_occupations_array);

        populateSpinner(productsChannel, R.array.product_channel_array);
        populateSpinner(productsName, R.array.product_name_array);
    }


    private void populateSpinner(Spinner spinner, int array_id) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                array_id, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);
    }
}
