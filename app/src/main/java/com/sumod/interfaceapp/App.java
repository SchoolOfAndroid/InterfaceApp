package com.sumod.interfaceapp;


import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.sumod.interfaceapp.model.CoreData;
import com.sumod.interfaceapp.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class App extends Application {
    public static String HOST = "http://192.168.1.108/mahendra/";
    public static User currentUser = new User();
    public static CoreData core = new CoreData();

    @Override
    public void onCreate() {
        super.onCreate();

        // Immediately get the data from the server.
        Call<CoreData> call = Api.service.init();
        call.enqueue(new Callback<CoreData>() {
            @Override
            public void onResponse(Response<CoreData> response) {
                core = response.body();
            }


            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong. Please restart the app", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }
}
