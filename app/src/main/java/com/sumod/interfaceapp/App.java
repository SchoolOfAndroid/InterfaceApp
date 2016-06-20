package com.sumod.interfaceapp;


import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.sumod.interfaceapp.fragments.FilterFragment;
import com.sumod.interfaceapp.model.CoreData;
import com.sumod.interfaceapp.model.User;

import module.base.ApplicationPrefs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class App extends Application {
    public static String HOST = "http://192.168.1.103/mahendra/";
    public static User currentUser = new User();
    public static CoreData core = new CoreData();
    public static FilterFragment.FilterSettings filter = new FilterFragment.FilterSettings();
    public static Context context;

    public static final String TAG = App.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static App mInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

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

//        new ApplicationPrefs(this);

        mInstance = this;
    }


    public static synchronized App getInstance() {
        return mInstance;
    }


    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    /*
    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.¬mImageLoader;
    }*/


    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }


    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }


    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
