package com.sumod.interfaceapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.sumod.interfaceapp.R;
import com.sumod.interfaceapp.model.Proposal;

import java.util.ArrayList;

/**
 * Created by sumodkulkarni on 19/6/16.
 */
public class ProposalListAdapter extends BaseAdapter implements ListAdapter {

    private Context context;
    private ArrayList<Proposal> proposalsList;

    public ProposalListAdapter(Context context, ArrayList<Proposal> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
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

        return view;
    }
}
