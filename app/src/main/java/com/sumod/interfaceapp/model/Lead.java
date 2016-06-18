package com.sumod.interfaceapp.model;


public class Lead {
    public int id;
    public int type_id;
    public int creator_id;
    public String description;
    public int job_sector_id;
    public int job_role_id;
    public int is_job_seeker;
    public int service_occupation_id;
    public int service_name_id;
    public int is_service_seeker;
    public int product_name_id;
    public int product_channel_id;
    public int is_product_seeker;
    public int location_id;


    public boolean isJobSeeker() {
        return is_job_seeker == 1;
    }


    public boolean isServiceSeeker() {
        return is_service_seeker == 1;
    }


    public boolean isProductSeeker() {
        return is_product_seeker == 1;
    }
}
