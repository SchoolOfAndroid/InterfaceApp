package com.sumod.interfaceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONException;
import org.json.JSONObject;
import module.app.AppController;
import module.base.ApplicationPrefs;
import module.blu.ServerHelper;

public class SplashActivity extends Activity {

    private String CHECK_USER = "checkUser";
    ProgressBar progressBar1;
    private Handler mHandler;
    private int mInterval = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar1= (ProgressBar) findViewById(R.id.progressBar1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkUser();
            }
        }, 2000);

    }



    private void checkUser() {

        progressBar1.setVisibility(View.VISIBLE);

        String deviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        String url = ServerHelper.CHECK_USER +"?deviceId="+deviceId;

        StringRequest strReq = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response",response);

                        try {
                            JSONObject res=new JSONObject(response);
                            String status=res.getString("status");
                            String nickname=res.getString("nickname");
                            String uid=res.getString("uid");

                            if(status.equals("new"))
                            {
                                ApplicationPrefs.getInstance(getApplicationContext()).setUserId(uid);
                                ApplicationPrefs.getInstance(getApplicationContext()).setNickName(nickname);
                            }

                            progressBar1.setVisibility(View.GONE);
                            finish();

                            Intent in=new Intent(getApplicationContext(),NavigationDrawerActivity.class);
                            startActivity(in);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar1.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        });

        strReq.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


        // Adding request to request queue
//        AppController.getInstance().addToRequestQueue(strReq,CHECK_USER);

    }



}
