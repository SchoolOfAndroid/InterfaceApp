package com.sumod.interfaceapp.model;

/**
 * Created by sumodkulkarni on 11/6/16.
 */
public class Job {

    private long id;
    private String occupation;
    private String job;
    private String jobDescription;
    private String location;


    //Constructors

    public Job() {
    }

    public Job(long id, String occupation, String job) {
        this.id = id;
        this.occupation = occupation;
        this.job = job;
    }

    public Job(long id, String occupation, String job, String jobDescription, String location) {
        this.id = id;
        this.occupation = occupation;
        this.job = job;
        this.jobDescription = jobDescription;
        this.location = location;
    }

    //Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
