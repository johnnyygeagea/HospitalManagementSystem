package com.hospital.hms.entities;

public abstract class Staff {
    private String staffID, name, jobTitle;

    public Staff(String id, String name, String title){
        this.staffID = id;
        this.name = name;
        this.jobTitle = title;
    }

    public String getStaffID() {
        return staffID;
    }
    public String getName() {
        return name;
    }
    public String getJobTitle() {
        return jobTitle;
    }

    @Override
    public String toString(){
        return name + " (" + jobTitle + ", ID: " + staffID + ")";
    }
    

}
