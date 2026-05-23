package com.hospital.hms.db;

import com.hospital.hms.entities.Staff;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StaffDAO {

    public void saveStaff(Staff staff) {
        // We use 'job_title' to match your SQL table column
        String sql = "INSERT INTO staff (staff_id, name, job_title) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Mapping Java attributes to SQL parameters
            pstmt.setString(1, staff.getStaffID());
            pstmt.setString(2, staff.getName());
            pstmt.setString(3, staff.getJobTitle());
            
            pstmt.executeUpdate();
            System.out.println("✅ " + staff.getName() + " saved to PostgreSQL!");

        } catch (SQLException e) {
            // Check for specific MENA-level security/error handling
            if (e.getSQLState().equals("23505")) { // Unique violation
                System.err.println("❌ Error: Staff ID already exists.");
            } else {
                System.err.println("❌ Database Error: " + e.getMessage());
            }
        }
    }
}
