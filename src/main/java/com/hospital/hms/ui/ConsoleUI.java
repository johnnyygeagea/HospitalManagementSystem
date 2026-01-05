package com.hospital.hms.ui;

import com.hospital.hms.Hospital;
import com.hospital.hms.entities.*;
import com.hospital.hms.exceptions.StaffNotFoundException;
import java.util.Scanner;
import java.util.List;

public class ConsoleUI {
    private final Hospital hospital;
    private final Scanner scanner;

    public ConsoleUI(Hospital hospital) {
        this.hospital = hospital;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Main application loop.
     */
    public void start() {
        boolean running = true;
        System.out.println("HMS Professional: System Online");

        while (running) {
            displayMenu();
            String choice = scanner.nextLine();

            // Inside the start() method
switch (choice) {
    case "1": admitPatient(); break;
    case "2": treatNextPatient(); break;
    case "3": addNewStaff(); break;
    case "4": findStaffMember(); break;
    case "5": findPatientById(); break;   // Added for completeness
    case "6": scheduleAppointment(); break;
    case "7": manageBilling(); break;      // THE NEW OPTION
    case "8": viewAppointments(); break;
    case "9": running = false; break;
    default: System.out.println("[ERROR] Invalid selection.");
}
        }
        System.out.println("System Shutdown. Goodbye.");
    }

    private void displayMenu() {
    System.out.println("\n--- HMS MAIN MENU ---");
    System.out.println("1. Triage: Admit Patient      2. Triage: Treat Next Patient");
    System.out.println("3. Staff: Add Member          4. Staff: Search by ID");
    System.out.println("5. Patient: Search by ID      6. Schedule Appointment");
    System.out.println("7. Billing: Manage            8. View All Appointments");
    System.out.println("9. Exit");
    System.out.print("Selection > ");
}
    // --- CASE 1: ADMIT ---
    private void admitPatient() {
    System.out.println("\n--- Admit New Patient (Type 'back' at any time to cancel) ---");

    while (true) {
        System.out.print("Patient Name: ");
        String name = scanner.nextLine();
        if (name.equalsIgnoreCase("back")) return; // GO BACK OPTION

        System.out.print("Patient ID: ");
        String id = scanner.nextLine();
        if (id.equalsIgnoreCase("back")) return;

        // LOOP FOR PRIORITY (So they don't have to restart the whole form)
        TriagePriority tp = null;
        while (tp == null) {
            System.out.print("Priority (CRITICAL, SERIOUS, STABLE) or 'back': ");
            String prio = scanner.nextLine().toUpperCase();
            
            if (prio.equals("BACK")) return;

            try {
                tp = TriagePriority.valueOf(prio);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please type the full word (e.g., CRITICAL).");
            }
        }

        // If we reach here, we have all valid data
        hospital.admitPatient(new Patient(id, name, "Waiting", tp));
        System.out.println("Patient " + name + " admitted successfully.");
        break; // Exit the main admit loop
    }
}

private void scheduleAppointment() {
    System.out.println("\n--- Schedule New Appointment ---");
    
    System.out.print("Enter Patient ID: ");
    String pId = scanner.nextLine();
    Patient p = hospital.findPatient(pId); // Requires the findPatient method in Hospital

    if (p == null) {
        System.out.println("[ERROR] Patient not found in registry. Admit them first.");
        return;
    }

    System.out.print("Enter Staff ID: ");
    String sId = scanner.nextLine();
    
    try {
        Staff s = hospital.findStaff(sId);
        
        System.out.print("Enter Date (e.g., 2025-10-25): ");
        String date = scanner.nextLine();
        
        System.out.print("Enter Time (e.g., 14:30): ");
        String time = scanner.nextLine();
        
        String fullDateTime = date + " at " + time;
        String apptId = "APT-" + (System.currentTimeMillis() % 10000);

        // This must match your Appointment constructor exactly
        Appointment newAppt = new Appointment(apptId, pId, sId, fullDateTime);
        hospital.scheduleAppointment(newAppt);
        
        System.out.println("[SUCCESS] Appointment scheduled for " + p.getName() + 
                           " with " + s.getName());

    } catch (Exception e) {
        System.out.println("[ERROR] Staff not found: " + e.getMessage());
    }
}
private void manageBilling() {
    System.out.println("\n1. Issue New Bill | 2. Pay Bill | 3. Back");
    String choice = scanner.nextLine();
    
    if (choice.equals("1")) {
        System.out.print("Patient ID: ");
        String pId = scanner.nextLine();
        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        String billId = "B" + (System.currentTimeMillis() % 1000);
        
        hospital.issueBill(billId, pId, amount);
        System.out.println("[SUCCESS] Bill " + billId + " issued.");
    } else if (choice.equals("2")) {
        System.out.print("Enter Bill ID to pay: ");
        String bId = scanner.nextLine();
        if (hospital.payBill(bId)) {
            System.out.println("[SUCCESS] Bill paid.");
        } else {
            System.out.println("[ERROR] Bill not found or already paid.");
        }
    }
}

    // --- CASE 2: TREAT ---
    private void treatNextPatient() {
        Patient p = hospital.treatNextPatient();
        if (p != null) {
            System.out.println("Medical Action: Now treating " + p);
        } else {
            System.out.println("Triage alert: No patients currently waiting.");
        }
    }

    // --- CASE 3: ADD STAFF ---
    private void addNewStaff() {
        System.out.print("Staff ID: ");
        String id = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Role (1: Doctor, 2: Nurse): ");
        String role = scanner.nextLine();

        Staff member = null;
        if (role.equals("1")) {
            System.out.print("Specialty: ");
            String spec = scanner.nextLine();
            member = new Doctor(id, name, spec);
        } else if (role.equals("2")) {
            System.out.print("Floor Assignment: ");
            String floor = scanner.nextLine();
            member = new Nurse(id, name, floor);
        }

        if (member != null && hospital.addStaff(member)) {
            System.out.println(member.getClass().getSimpleName() + " registered.");
        } else {
            System.out.println("Registration failed. ID might already exist.");
        }
    }

    // --- CASE 4: SEARCH (The Exception Demo) ---
    private void findStaffMember() {
        System.out.print("Enter ID to search: ");
        String id = scanner.nextLine();
        try {
            Staff s = hospital.findStaff(id);
            System.out.println("🔍 Result: " + s);
        } catch (StaffNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // --- CASE 5: VIEW ---
    private void viewAppointments() {
        List<Appointment> apps = hospital.getAllAppointments();
        if (apps.isEmpty()) {
            System.out.println("Schedule is currently empty.");
        } else {
            System.out.println("\n--- Scheduled Appointments ---");
            apps.forEach(System.out::println);
        }
    }
    private void findPatientById() {
    System.out.print("Enter Patient ID to search: ");
    String id = scanner.nextLine();
    Patient p = hospital.findPatient(id);
    
    if (p != null) {
        System.out.println("[RESULT] " + p);
    } else {
        System.out.println("[ERROR] Patient with ID " + id + " not found.");
    }
}
}