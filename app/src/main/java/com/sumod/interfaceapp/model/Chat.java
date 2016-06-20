package com.sumod.interfaceapp.model;


/**
 * Created by sumodkulkarni on 18/6/16.
 */
public class Chat {

    private int _id;
    private int user_id;    //this is the user's own ID
    private int user2_id;   //this is the ID of the person the user is chatting with

    private String user2_name;
    private String lastMessageTime;  //This is the time at which the last message in the chat was sent/received

    public Integer propsal_id;
    public String chat_name;

    //Constructors


    public Chat() {
    }


    public Chat(int propsal_id, String chat_name) {
        this.propsal_id = propsal_id;
        this.chat_name = chat_name;
    }


    public Chat(int user2_id, String user2_name, String lastMessageTime) {
        this.user2_id = user2_id;
        this.user2_name = user2_name;
        this.lastMessageTime = lastMessageTime;
    }

    //Getters and Setters


    public int get_id() {
        return _id;
    }


    public void set_id(int _id) {
        this._id = _id;
    }


    public int getUser_id() {
        return user_id;
    }


    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


    public int getUser2_id() {
        return user2_id;
    }


    public void setUser2_id(int user2_id) {
        this.user2_id = user2_id;
    }


    public String getUser2_name() {
        return user2_name;
    }


    public void setUser2_name(String user2_name) {
        this.user2_name = user2_name;
    }


    public String getLastMessageTime() {
        return lastMessageTime;
    }


    public void setLastMessageTime(String lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }
}
