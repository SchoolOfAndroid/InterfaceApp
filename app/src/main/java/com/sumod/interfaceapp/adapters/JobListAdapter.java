package com.sumod.interfaceapp.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.sumod.interfaceapp.R;
import com.sumod.interfaceapp.model.Job;
import com.sumod.interfaceapp.multisms.MessageEditorActivity;

import java.util.ArrayList;


/**
 * Created by sumodkulkarni on 12/6/16.
 */

public class JobListAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<Job> jobList = new ArrayList<Job>();
    private Context context;


    public JobListAdapter(Context context, ArrayList<Job> list) {
        this.jobList = list;
        this.context = context;
    }


    @Override
    public int getCount() {
        return jobList.size();
    }


    @Override
    public Object getItem(int pos) {
        return jobList.get(pos);
    }


    @Override
    public long getItemId(int pos) {

        return jobList.get(pos).getId();
        //just return 0 if your list items do not have an Id variable.

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.job_list_item, null);
        }

        //Handle TextView and display string from your list
        TextView jobName = (TextView) view.findViewById(R.id.list_item_job);
        TextView jobOccupation = (TextView) view.findViewById(R.id.list_item_occupation);
        TextView jobDescription = (TextView) view.findViewById(R.id.list_item_description);

        jobName.setText(jobList.get(position).getJob());
        jobOccupation.setText(jobList.get(position).getOccupation());
        jobDescription.setText(jobList.get(position).getDescription());

        //Handle buttons and add onClickListeners
        ImageView openChat = (ImageView) view.findViewById(R.id.list_item_eng);
        ImageView referJob = (ImageView) view.findViewById(R.id.list_item_refer);

        openChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                Toast.makeText(context, "Chat was clicked", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });
        referJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                Intent intent = new Intent(context, MessageEditorActivity.class);
                intent.putExtra("job", jobList.get(position).getJob());
                intent.putExtra("occupation", jobList.get(position).getOccupation());
                context.startActivity(intent);
                notifyDataSetChanged();
            }
        });

        return view;
    }
}