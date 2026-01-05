package com.hospital.hms.entities;

public class Patient implements Comparable<Patient>{
private String patientID, name, condition;
private TriagePriority priority;

public Patient(String patientID, String name, String condition, TriagePriority priority) {
        this.patientID = patientID;
        this.name = name;
        this.condition = condition;
        this.priority = priority;
    }

    // Getters
    public String getPatientID() { return patientID; }
    public String getName() { return name; }
    public TriagePriority getPriority() { return priority; }

    // Setter for updating condition (useful after diagnosis)
    public void setCondition(String newCondition) {
        this.condition = newCondition;
    }

    @Override
    public int compareTo(Patient other) {
        return Integer.compare(other.priority.getLevel(), this.priority.getLevel());

    }

    @Override
    public String toString() {
        return name + " (ID: " + patientID + ", Priority: " + priority + ", Condition: " + condition + ")";
    }

}
