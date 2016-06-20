package com.sumod.interfaceapp.adapters;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.sumod.interfaceapp.ChatActivity;
import com.sumod.interfaceapp.R;
import com.sumod.interfaceapp.model.Proposal;

import java.util.List;


/**
 * Created by sumodkulkarni on 19/6/16.
 */
public class ProposalListAdapter extends BaseAdapter implements ListAdapter {

    private Context context;
    private List<Proposal> proposalsList;


    public ProposalListAdapter(Context context, List<Proposal> list) {
        this.context = context;
        this.proposalsList = list;
    }


    @Override
    public int getCount() {
        return proposalsList.size();
    }


    @Override
    public Object getItem(int position) {
        return proposalsList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return proposalsList.get(position).getId();
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.proposals_list_item, null);
        }

        TextView senderInfo = (TextView) view.findViewById(R.id.proposal_list_sender_info);
        TextView reqInfo = (TextView) view.findViewById(R.id.proposal_list_req_info);
        TextView reqType = (TextView) view.findViewById(R.id.proposal_list_req_type);

        senderInfo.setText(proposalsList.get(position).getSenderInfo());
        reqInfo.setText(proposalsList.get(position).getRequestInfo());
        reqType.setText(proposalsList.get(position).getRequestType());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra(ChatActivity.PROPOSALID, proposalsList.get(position).id);
                context.startActivity(intent);
            }
        });

        return view;
    }
}
