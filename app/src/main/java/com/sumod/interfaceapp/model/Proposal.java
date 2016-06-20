package com.sumod.interfaceapp.model;

/**
 * Created by sumodkulkarni on 19/6/16.
 */
public class Proposal {
    public int id;
    public Lead lead;
    public User user;


    public String getSenderInfo() {
        if (user != null) return user.name + " - " + user.phone;
        else return "";
    }

    public String getRequestInfo() {
        return lead.getTitle();
    }


    public String getRequestType() {
        return lead.getMeta();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
