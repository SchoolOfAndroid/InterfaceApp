package com.sumod.interfaceapp.model;


import java.util.ArrayList;
import java.util.List;


public class CoreData {
    public List<Value> service_names = new ArrayList<>();
    public List<Value> locations = new ArrayList<>();
    public List<Value> job_sectors = new ArrayList<>();
    public List<Value> job_roles = new ArrayList<>();
    public List<Value> service_occupations = new ArrayList<>();
    public List<Value> product_channels = new ArrayList<>();
    public List<Value> product_names = new ArrayList<>();


    public class Value {
        public int id;
        public String name;
    }
};