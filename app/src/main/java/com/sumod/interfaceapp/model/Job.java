package com.sumod.interfaceapp.model;

/**
 * Created by sumodkulkarni on 11/6/16.
 */
public class Job {
    private long id;
    private String occupation;
    private String job;
    private String need;
    private String description;
    private String location_id;


    //Constructors

    public Job() {
    }

    public Job(long id, String occupation, String job) {
        this.id = id;
        this.occupation = occupation;
        this.job = job;
    }

    public Job(long id, String need, String job, String jobDescription, String location_id) {
        this.id = id;
        this.need = need;
        this.job = job;
        this.description = jobDescription;
        this.location_id = location_id;
    }

    //Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getOccupation() {
        return need;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location_id;
    }

    public void setLocation(String location) {
        this.location_id = location;
    }

}
