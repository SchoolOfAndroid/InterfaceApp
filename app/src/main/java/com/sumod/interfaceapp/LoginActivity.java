package com.sumod.interfaceapp;


import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.sumod.interfaceapp.model.User;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {
    @ViewById(R.id.phoneText) EditText email;


    @Click(R.id.loginButton)
    void loginClicked() {
        String android_id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);

        Call<User> call = Api.service.login(
                email.getText().toString(),
                android_id
        );

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response) {
                if (response.isSuccess()) {
                    Toast.makeText(getApplicationContext(), "You are logged in", Toast.LENGTH_LONG).show();
                    App.currentUser = response.body();

                    Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "You are not registered on this phone", Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }
}
