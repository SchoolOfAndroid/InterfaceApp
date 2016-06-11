package com.sumod.interfaceapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;


@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

    @Click(R.id.loginButton)
    void loginClicked() {
        Intent intent = new Intent(getApplicationContext(), MainActivity_.class);
        startActivity(intent);
    }
}
