package com.sumod.interfaceapp.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.sumod.interfaceapp.R;
import com.sumod.interfaceapp.adapters.ChatListAdapter;
import com.sumod.interfaceapp.model.Chat;

import java.util.ArrayList;


public class MessagesFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    
    private String mParam1;
    private String mParam2;

    private ListView listView_chats;
    ArrayList<Chat> chatList;
    public MessagesFragment() {
        // Required empty public constructor
    }


    public static MessagesFragment newInstance(String param1, String param2) {
        MessagesFragment fragment = new MessagesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView_chats = (ListView) view.findViewById(R.id.listView_chats);

        populateListView();

        listView_chats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "Open Chat with userID: " + chatList.get(position).getUser2_id(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void populateListView() {

        chatList = new ArrayList<>();

        chatList.add(new Chat(0, "User 0", "20:28"));
        chatList.add(new Chat(1, "User 1", "19:28"));
        chatList.add(new Chat(2, "User 2", "18:28"));
        chatList.add(new Chat(3, "User 3", "17:28"));
        chatList.add(new Chat(4, "User 4", "16:28"));
        chatList.add(new Chat(5, "User 5", "15:28"));

        ChatListAdapter myChatListAdapter = new ChatListAdapter(getContext(), chatList);

        listView_chats.setAdapter(myChatListAdapter);

    }

}
