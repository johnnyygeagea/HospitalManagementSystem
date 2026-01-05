package com.hospital.hms.entities;

public class Doctor extends Staff{
    private String specialty;
    private boolean isAvailable;

    public Doctor(String staffID, String name, String specialty){
        super(staffID, name, "Doctor");
        this.specialty = specialty;
        this.isAvailable = true;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void assignToPatient(String patientID) {
        this.isAvailable = false;
        System.out.println(getName() + " is assigned to patient " + patientID);
    }

    @Override 
    public String toString() {
        return super.toString() + " | Specialty: " + specialty + " | Available : " + (isAvailable ? "Yes" : "No");
    }
}
