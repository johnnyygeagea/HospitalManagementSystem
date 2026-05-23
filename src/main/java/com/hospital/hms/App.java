package com.hospital.hms;

import com.hospital.hms.ui.ConsoleUI;
// --- ADD THIS IMPORT ---
import com.hospital.hms.db.DatabaseConnection; 
import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        
        // --- ADD THIS DATABASE CHECK BLOCK ---
        System.out.println("Connecting to PostgreSQL...");
        try (Connection testConn = DatabaseConnection.getConnection()) {
            if (testConn != null) {
                System.out.println("✅ Database Connection: ONLINE");
            }
        } catch (Exception e) {
            System.err.println("❌ Database Connection: OFFLINE");
            System.err.println("Reason: " + e.getMessage());
            System.err.println("Make sure Postgres is running and your password is correct.");
            return; // Stop the app here so you don't waste time in a broken UI
        }
        // --------------------------------------

        // 1. Initialize the Service Layer (The Brain)
        Hospital hmsService = new Hospital();

        // 2. Initialize the Presentation Layer (The Face)
        ConsoleUI terminalInterface = new ConsoleUI(hmsService);

        // 3. Hand over control to the UI loop
        try {
            terminalInterface.start();
        } catch (Exception e) {
            System.err.println("A critical system error occurred: " + e.getMessage());
        } finally {
            System.out.println("HMS Session Ended.");
        }
    }
}