package com.sumod.interfaceapp;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sumod.interfaceapp.model.Lead;
import com.sumod.interfaceapp.util.GPSTracker;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;


@EActivity(R.layout.activity_post)
public class PostActivity extends AppCompatActivity {

    @ViewById(R.id.spinner_post_service_or_job) Spinner service_or_job;

    @ViewById(R.id.jobPosting_need) Spinner jobPosting_need;
    @ViewById(R.id.jobPosting_job) Spinner jobPosting_job;

    @ViewById(R.id.service_spinner_name) Spinner serviceName;
    @ViewById(R.id.service_spinner_occup) Spinner serviceOccup;

    @ViewById(R.id.products_spinner_ch) Spinner productsChannel;
    @ViewById(R.id.products_spinner_name) Spinner productsName;

    @ViewById(R.id.jobPosting_description) EditText jobPosting_description;
    @ViewById(R.id.jobPosting_area) Spinner jobPosting_area;

    @ViewById(R.id.btn_postJob) Button button_postJob;

    @ViewById(R.id.jobs_layout) LinearLayout jobsLayout;
    @ViewById(R.id.service_layout) LinearLayout servicesLayout;
    @ViewById(R.id.products_layout) LinearLayout productsLayout;
    @ViewById(R.id.results_layout) LinearLayout resultsLayout;

    @ViewById(R.id.toolbar) Toolbar mToolbar;
    @ViewById(R.id.title) TextView title;

    private static final int PERMISSIONS_ACCESS_COARSE_LOCATION = 100;


    @Click(R.id.btn_postJob)
    protected void postJob() {
        // TODO: validate();

        // Create the lead
        Lead lead = new Lead();
        lead.creator_id = App.currentUser.id;
        lead.description =   jobPosting_description.getText().toString();
        lead.location_id = jobPosting_area.getSelectedItemPosition();

        switch (service_or_job.getSelectedItemPosition()) {
            case 1:
                // TODO Don't use selected positions; use proper id..
                lead.job_role_id = jobPosting_need.getSelectedItemPosition();
                lead.job_sector_id = jobPosting_job.getSelectedItemPosition();
                lead.is_job_seeker = 0;
                break;
            case 2:
                lead.service_name_id = serviceName.getSelectedItemPosition();
                lead.service_occupation_id = serviceOccup.getSelectedItemPosition();
                lead.is_service_seeker = 0;
                break;
            case 3:
                lead.product_channel_id = productsChannel.getSelectedItemPosition();
                lead.product_name_id = productsName.getSelectedItemPosition();
                lead.is_product_seeker = 0;
                break;
        }

        // Send it to the backend
        Call<Lead> call = Api.service.createLead(
                lead.creator_id,
                lead.description,
                lead.job_sector_id,
                lead.job_role_id,
                lead.is_job_seeker,
                lead.service_occupation_id,
                lead.service_name_id,
                lead.is_service_seeker,
                lead.product_name_id,
                lead.product_channel_id,
                lead.is_product_seeker,
                lead.location_id
        );


        call.enqueue(new Callback<Lead>() {
            @Override
            public void onResponse(Response<Lead> response) {
                if (response.isSuccess()) {
                    Toast.makeText(getApplicationContext(), "Your post has been submitted!", Toast.LENGTH_LONG).show();
                    finish();
                } else {

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

//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        populateSpinner(service_or_job, R.array.service_or_job_array);
        populateSpinner(jobPosting_need, R.array.job_roles_array);
        populateSpinner(jobPosting_job, R.array.job_sectors_array);
        populateSpinner(jobPosting_area, R.array.areas_array);

        populateSpinner(serviceName, R.array.service_name_array);
        populateSpinner(serviceOccup, R.array.service_occupations_array);

        populateSpinner(productsChannel, R.array.product_channel_array);
        populateSpinner(productsName, R.array.product_name_array);
        title.setText("Submit a new posting");

        service_or_job.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateLayouts();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                updateLayouts();
            }
        });

        updateLayouts();
    }


    void updateLayouts() {
        jobsLayout.setVisibility(View.GONE);
        servicesLayout.setVisibility(View.GONE);
        productsLayout.setVisibility(View.GONE);

        if (service_or_job.getSelectedItemPosition() == 0) {
            resultsLayout.setVisibility(View.GONE);
        } else {
            resultsLayout.setVisibility(View.VISIBLE);

            switch (service_or_job.getSelectedItemPosition()) {
                case 1:
                    jobsLayout.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    servicesLayout.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    productsLayout.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }


    private void populateSpinner(Spinner spinner, int array_id) {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                array_id, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.get_location) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSIONS_ACCESS_COARSE_LOCATION);

                //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method

            } else {
                // Android version is lesser than 6.0 or the permission is already granted.

                GPSTracker gps = new GPSTracker(this);
                if (gps.canGetLocation()){
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    Toast.makeText(PostActivity.this, "Lat:" + latitude + " / Long:" + longitude, Toast.LENGTH_SHORT).show();
                }
                else {
                    gps.showSettingsAlert(this);
                }

            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_ACCESS_COARSE_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted

                GPSTracker gps = new GPSTracker(this);
                if (gps.canGetLocation()){
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    Toast.makeText(PostActivity.this, "Lat:" + latitude + " / Long:" + longitude, Toast.LENGTH_SHORT).show();
                }
                else {
                    gps.showSettingsAlert(this);
                }

            } else {
                Toast.makeText(this, "Location Permissions not granted", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
