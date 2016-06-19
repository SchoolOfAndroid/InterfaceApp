package com.sumod.interfaceapp;

import android.app.Activity;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import module.adapter.MessageAdapter;
import module.app.AppController;
import module.base.ApplicationPrefs;
import module.bean.Chat;
import module.bean.Message;
import module.blu.ServerHelper;
import module.http.BaseHttpRequestToken;

public class ChatActivity extends Activity {

    private String GET_LIST = "getList";
    private String SEND_MSG = "sendmsg";

    private RecyclerView mrecyclerview;
    public List<Message> mlist;
    public MessageAdapter mAdapter;

    EditText writeMsg;
    ImageView uploadMsg;
    ProgressBar progressBar2;

    private Handler mHandler;
    private int mInterval = 5000;

    // pagination
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initUI();
        setValues();
    }


    private void initUI() {
        mrecyclerview= (RecyclerView) findViewById(R.id.msgslist);
        mlist=new ArrayList<>();
        mAdapter=new MessageAdapter(mlist,getApplicationContext(),ChatActivity.this);
        writeMsg= (EditText) findViewById(R.id.writeMsg);
        uploadMsg= (ImageView) findViewById(R.id.uploadMsg);
        progressBar2= (ProgressBar) findViewById(R.id.progressBar2);
        progressBar2.setVisibility(View.VISIBLE);
        mHandler = new Handler();

    }

    private void setValues() {
        mrecyclerview.setAdapter(mAdapter);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(ChatActivity.this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mrecyclerview.setLayoutManager(mLayoutManager);

        uploadMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadMsg.setEnabled(false);
                sendMsg();
            }
        });

        mrecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();
                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            // for progress loader...
                            loading = false;
                            Log.v("LSTITEM", "Last Item Wow !");
                            //Do pagination.. i.e. fetch new data
                            startRepeatingTask();
                        }
                    }
                }
                else if (dy < 0 ) {
                    // Recycle view scrolling up...
                    stopRepeatingTask();
                    AppController.getInstance().cancelPendingRequests(GET_LIST);
                }

            }
        });
    }


    private void processCommentData() {

        String url = ServerHelper.GET_LIST;

        StringRequest strReq = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG",response);

                        Gson gson = new Gson();
                        Chat chatlist = gson.fromJson(response, Chat.class);
                        mlist.clear();
                        mlist.addAll(chatlist.result);
                        mAdapter.notifyDataSetChanged();
                        mrecyclerview.scrollToPosition(mlist.size() - 1);
                        progressBar2.setVisibility(View.GONE);

                        mHandler.postDelayed(mStatusChecker, mInterval);
                        loading=true;

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading=true;
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        });

        strReq.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, GET_LIST);

    }


    private void sendMsg() {

        if(writeMsg.getText().toString().trim().length()==0){
            writeMsg.setError("required");
            return;
        }
        progressBar2.setVisibility(View.VISIBLE);

        String url = ServerHelper.SEND_MESSAGE;

        HashMap<String,String> mymap=new HashMap<>();
        mymap.put("uid",ApplicationPrefs.getInstance(getApplicationContext()).getUserId());
        mymap.put("message",writeMsg.getText().toString());

        BaseHttpRequestToken strReq = new BaseHttpRequestToken(getApplicationContext(),Request.Method.POST, url,mymap,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG",response);
                        writeMsg.setText("");
                        uploadMsg.setEnabled(true);
                        processCommentData();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
                uploadMsg.setEnabled(true);
            }
        });

        strReq.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, SEND_MSG);

    }


    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            processCommentData();
        }
    };

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopRepeatingTask();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startRepeatingTask();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppController.getInstance().cancelPendingRequests(GET_LIST);
        AppController.getInstance().cancelPendingRequests(SEND_MSG);

    }
}
