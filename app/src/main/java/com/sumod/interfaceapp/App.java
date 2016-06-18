package com.sumod.interfaceapp;


import android.app.Application;

import com.sumod.interfaceapp.model.User;


public class App extends Application {
    public static String HOST = "http://192.168.1.1";
    public static User currentUser = new User();


    @Override
    public void onCreate() {
        super.onCreate();

    }
}
