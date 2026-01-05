package com.hospital.hms.entities;

public class Bill {
private String billID, patientID;
private double amount;
private boolean isPaid;


public Bill(String billID, String patientID, double amount){
    this.billID = billID;
    this.patientID = patientID;
    this.amount = amount;
    this.isPaid = false;
}

public String getBillID() { return billID; }

public double getAmount() { return amount;}

public boolean isPaid() { return isPaid;}

public void markAsPaid() {
    this.isPaid = true;
    System.out.println("Bill " + billID + " has been settled.");
}

@Override
public String toString() {
    return "The bill " + billID + "for the patient " + patientID + " is " + amount + "$";
}
}
