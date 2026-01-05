package com.hospital.hms.entities;

import java.util.ArrayList;

public class Nurse extends Staff {
private String floorAssignment;
private ArrayList<String> currentPatients;

public Nurse(String id, String name, String floorAssignment) {
    super(id, name, "Nurse");
    this.floorAssignment = floorAssignment;
    this.currentPatients = new ArrayList<>();
}

public String getFloorAssignment(){ return floorAssignment;}

public void assignPatient(String patientID) {
    if(!currentPatients.contains(patientID)) 
    currentPatients.add(patientID);
    System.out.println(getName() + " is now monitoring patient " + patientID);
}

@Override
    public String toString() {
        return super.toString() + " | Floor: " + floorAssignment + " | Assigned Patients: " + currentPatients.size();
    }

}
