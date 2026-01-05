package com.hospital.hms.exceptions;

/**
 * Custom exception thrown when a requested staff ID 
 * does not exist in the hospital roster.
 */
public class StaffNotFoundException extends RuntimeException {
    
    public StaffNotFoundException(String id) {
        // Pass a custom message to the parent RuntimeException class
        super("Search Error: Staff member with ID '" + id + "' could not be found.");
    }
}