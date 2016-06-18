package com.sumod.interfaceapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.sumod.interfaceapp.R;
import com.sumod.interfaceapp.model.Chat;

import java.util.ArrayList;

/**
 * Created by sumodkulkarni on 18/6/16.
 */
public class ChatListAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<Chat> chatList = new ArrayList<>();
    private Context context;


    public ChatListAdapter(Context context, ArrayList<Chat> list) {
        this.chatList = list;
        this.context = context;
    }


    @Override
    public int getCount() {
        return chatList.size();
    }


    @Override
    public Object getItem(int pos) {
        return chatList.get(pos);
    }


    @Override
    public long getItemId(int pos) {

        return chatList.get(pos).get_id();
        //just return 0 if your list items do not have an Id variable.

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.chat_list_item, null);
        }

        //Handle TextView and display string from your list
        ImageView user2image = (ImageView) view.findViewById(R.id.user2_profile_picture);
        TextView user2name = (TextView) view.findViewById(R.id.user2_name);
        TextView lastMessageTime = (TextView) view.findViewById(R.id.last_message_time);

        //Call this method when setting the profile picture for user2
        user2image.setImageDrawable(context.getResources().getDrawable(R.drawable.profile4));
        user2name.setText(chatList.get(position).getUser2_name());
        lastMessageTime.setText(chatList.get(position).getLastMessageTime());

        return view;
    }


}
