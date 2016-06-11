package com.sumod.interfaceapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;


@EActivity(R.layout.activity_entry)
public class EntryActivity extends AppCompatActivity {

    @Click(R.id.loginButton)
    void loginButtonClicked() {
        startActivity(new Intent(this, LoginActivity_.class));
    }


    @Click(R.id.registerButton)
    void registerButtonClicked() {
        startActivity(new Intent(this, SignUpActivity_.class));
    }
}
