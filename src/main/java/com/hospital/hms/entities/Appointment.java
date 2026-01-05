package com.hospital.hms.entities;

public class Appointment {
private String appointmentID, patientID, doctorID, appointmentDate;

public Appointment(String appointmentID, String patientID, String doctorID, String appointmentDate){
    this.appointmentID = appointmentID;
    this.patientID = patientID;
    this.doctorID = doctorID;
    this.appointmentDate = appointmentDate;
} 

public String getAppointmentID() {
    return appointmentID;
}

public String getDetails() {
    return "Appointment ID: " + appointmentID + 
           "\nPatient ID: " + patientID + 
           "\nDoctor ID: " + doctorID + 
           "\nDate: " + appointmentDate;
}
@Override
public String toString() {
    return "The appointment " + appointmentID + " is scheduled for " + appointmentDate;
}
}
