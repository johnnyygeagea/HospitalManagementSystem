package com.hospital.hms;

import com.hospital.hms.entities.*;
import com.hospital.hms.exceptions.StaffNotFoundException; // Assuming you created this
import java.util.*;

public class Hospital {
    // Data structures chosen for specific performance goals
    private final PriorityQueue<Patient> triageQueue;   // O(log n) sorting by priority
    private final HashMap<String, Staff> staffRoster;   // O(1) lookup for staff
    private final HashMap<String, Bill> billingLedger;  // O(1) lookup for billing
    private final ArrayList<Appointment> appointments; // O(1) addition, ordered list
    private HashMap<String, Patient> patientRegistry;

    public Hospital() {
        this.triageQueue = new PriorityQueue<>();
        this.staffRoster = new HashMap<>();
        this.billingLedger = new HashMap<>();
        this.appointments = new ArrayList<>();
        this.patientRegistry = new HashMap<>();
    }

    // --- STAFF MANAGEMENT ---
    
    /**
     * Adds a staff member if the ID is unique.
     * @return true if added, false if ID already exists.
     */
    public boolean addStaff(Staff s) {
        if (s == null || staffRoster.containsKey(s.getStaffID())) {
            return false;
        }
        staffRoster.put(s.getStaffID(), s);
        return true;
    }

    /*
     Finds staff by ID. 
     */
    public Staff findStaff(String id) {
        Staff s = staffRoster.get(id);
        if (s == null) {
            throw new StaffNotFoundException(id);
        }
        return s;
    }

    // --- TRIAGE SYSTEM ---

    public void admitPatient(Patient p) {
    if (p != null) {
        triageQueue.add(p);
        patientRegistry.put(p.getPatientID(), p); // Add to lookup map
    }
}

public Patient findPatient(String id) {
    return patientRegistry.get(id); // O(1) Lookup
}

    /**
     * Retrieves and removes the highest priority patient.
     * @return Patient or null if queue is empty.
     */
    public Patient treatNextPatient() {
        return triageQueue.poll(); 
    }

    // --- APPOINTMENTS ---

    public void scheduleAppointment(Appointment appt) {
        if (appt != null) {
            appointments.add(appt);
        }
    }

    /**
     * Returns a read-only view of appointments to protect data integrity.
     */
    public List<Appointment> getAllAppointments() {
        return Collections.unmodifiableList(appointments);
    }

    // --- BILLING ---

    public boolean issueBill(String billID, String patientID, double amount) {
        if (billID == null || billingLedger.containsKey(billID)) {
            return false;
        }
        billingLedger.put(billID, new Bill(billID, patientID, amount));
        return true;
    }

    /**
     * Processes payment for a specific bill.
     * @return true if payment successful, false if bill missing or already paid.
     */
    public boolean payBill(String billID) {
        Bill b = billingLedger.get(billID);
        if (b != null && !b.isPaid()) {
            b.markAsPaid();
            return true;
        }
        return false;
    }
}