package com.sumod.interfaceapp.model;


public class Lead {
    public Integer id;
    public Integer creator_id;
    public String description;
    public Integer job_sector_id;
    public Integer job_role_id;
    public Integer is_job_seeker;
    public Integer service_occupation_id;
    public Integer service_name_id;
    public Integer is_service_seeker;
    public Integer product_name_id;
    public Integer product_channel_id;
    public Integer is_product_seeker;
    public Integer location_id;


    public boolean isJobSeeker() {
        return is_job_seeker == 1;
    }


    public boolean isServiceSeeker() {
        return is_service_seeker == 1;
    }


    public boolean isProductSeeker() {
        return is_product_seeker == 1;
    }


    public boolean isNeeded() {
        return isJobSeeker() || isServiceSeeker() || isProductSeeker();
    }


    public String getTitle() {
        return "title";
    }
}
