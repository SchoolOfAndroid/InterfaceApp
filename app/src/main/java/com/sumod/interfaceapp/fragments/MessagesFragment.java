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

import com.sumod.interfaceapp.Api;
import com.sumod.interfaceapp.App;
import com.sumod.interfaceapp.R;
import com.sumod.interfaceapp.adapters.ChatListAdapter;
import com.sumod.interfaceapp.model.Chat;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;


public class MessagesFragment extends Fragment {
    private ListView listView_chats;
    List<Chat> chatList = new ArrayList<>();
    ChatListAdapter adapter;


    public MessagesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        adapter = new ChatListAdapter(getContext(), chatList);
        listView_chats.setAdapter(adapter);

        populateListView();

        listView_chats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "Open Chat with userID: " + chatList.get(position).getUser2_id(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    protected void populateListView() {
        chatList.clear();

        Api.service.getChats(App.currentUser.id).enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Response<List<Integer>> response) {
                chatList.clear();

                for (Integer i : response.body()) {
                    Chat chat = new Chat(i, "Chat #" + i);
                    chatList.add(chat);
                }

                adapter.notifyDataSetChanged();
            }


            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

}
