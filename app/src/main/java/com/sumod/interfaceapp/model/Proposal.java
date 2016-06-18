package com.sumod.interfaceapp.model;

/**
 * Created by sumodkulkarni on 19/6/16.
 */
public class Proposal {

    private int id;
    private String senderInfo;
    private String requestInfo;
    private String requestType;

    //Constructors


    public Proposal(String senderInfo, String requestInfo, String requestType) {
        this.senderInfo = senderInfo;
        this.requestInfo = requestInfo;
        this.requestType = requestType;
    }

    //Getters and Setters


    public String getSenderInfo() {
        return senderInfo;
    }

    public void setSenderInfo(String senderInfo) {
        this.senderInfo = senderInfo;
    }

    public String getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(String requestInfo) {
        this.requestInfo = requestInfo;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
