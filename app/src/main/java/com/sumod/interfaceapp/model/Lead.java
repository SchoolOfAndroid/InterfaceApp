package com.sumod.interfaceapp.model;


import com.sumod.interfaceapp.App;
import com.sumod.interfaceapp.R;


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


    public boolean isJob() {
        return job_role_id != null && job_sector_id != null;
    }


    public boolean isService() {
        return service_name_id != null && service_occupation_id != null;
    }


    public boolean isProduct() {
        return product_channel_id != null && product_name_id != null;
    }


    public boolean isNeeded() {
        return isJobSeeker() || isServiceSeeker() || isProductSeeker();
    }


    public String getTitle() {
        if (isJob()) {
            String arr1[] = App.context.getResources().getStringArray(R.array.job_roles_array);
            String arr2[] = App.context.getResources().getStringArray(R.array.job_sectors_array);
            return arr2[job_sector_id] + " - " + arr1[job_role_id];
        } else if (isService()) {
            String arr1[] = App.context.getResources().getStringArray(R.array.service_name_array);
            String arr2[] = App.context.getResources().getStringArray(R.array.service_occupations_array);
            return arr2[service_occupation_id] + " - " + arr1[service_name_id];
        } else if (isProduct()) {
            String arr1[] = App.context.getResources().getStringArray(R.array.product_name_array);
            String arr2[] = App.context.getResources().getStringArray(R.array.product_channel_array);
            return arr1[product_name_id] + arr2[product_channel_id];
        }

        return "title";
    }


    public String getMeta() {
        if (isJob()) {
            if (isJobSeeker()) {
                return "job wanted";
            } else return "job available";
        } else if (isProduct()) {
            if (isProductSeeker()) {
                return "product request";
            } else return "product for sale";
        } else if (isService()) {
            if (isServiceSeeker()) {
                return "service wanted";
            } else return "service offered";
        }
        return "";
    }
}
