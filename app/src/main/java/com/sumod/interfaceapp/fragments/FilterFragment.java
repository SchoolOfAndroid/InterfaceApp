package com.sumod.interfaceapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.sumod.interfaceapp.App;
import com.sumod.interfaceapp.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;


@EFragment(R.layout.fragment_filter)
public class FilterFragment extends Fragment {
    @ViewById(R.id.checkbox_find_jobs) CheckBox findJobs;
    @ViewById(R.id.checkbox_find_services) CheckBox findServices;
    @ViewById(R.id.checkbox_offer_jobs) CheckBox offerJobs;
    @ViewById(R.id.checkbox_offer_services) CheckBox offerServices;
    @ViewById(R.id.checkbox_offer_products) CheckBox offerProducts;
    @ViewById(R.id.checkbox_find_products) CheckBox findProducts;

    public FilterFragment() {
        // Required empty public constructor
    }


    @AfterViews
    void afterViews() {
        findJobs.setChecked(App.filter.findJobs);
        findServices.setChecked(App.filter.findServices);
        offerJobs.setChecked(App.filter.offerJobs);
        offerServices.setChecked(App.filter.offerServices);
        offerProducts.setChecked(App.filter.offerProducts);
        findProducts.setChecked(App.filter.findProducts);

        findJobs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("c", "" + isChecked);
                App.filter.findJobs = isChecked;
            }
        });

        findServices.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                App.filter.findServices = isChecked;
            }
        });

        offerJobs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                App.filter.offerJobs = isChecked;
            }
        });

        offerServices.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                App.filter.offerServices = isChecked;
            }
        });

        findProducts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                App.filter.findProducts = isChecked;
            }
        });

        offerProducts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                App.filter.offerProducts = isChecked;
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_filter, container, false);
    }


    public static class FilterSettings {
        public Boolean findJobs = true;
        public Boolean offerJobs = true;
        public Boolean findServices = true;
        public Boolean offerServices = true;
        public Boolean offerProducts = true;
        public Boolean findProducts = true;
    }
}
