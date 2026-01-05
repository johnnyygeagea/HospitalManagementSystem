package com.hospital.hms;

import com.hospital.hms.ui.ConsoleUI;

/**
 * The entry point for the Hospital Management System.
 * This class follows the "Main-as-Configuration" pattern, 
 * where it only initializes the system and starts the UI.
 */
public class App {
    public static void main(String[] args) {
        // 1. Initialize the Service Layer (The Brain)
        Hospital hmsService = new Hospital();

        // 2. Initialize the Presentation Layer (The Face)
        // We inject the hospital instance so the UI can interact with it
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