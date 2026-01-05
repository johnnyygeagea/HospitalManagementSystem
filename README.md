# Hospital Management System (HMS)

This project is a Java-based application designed to manage core hospital operations, including patient triage, staff management, and billing services. The system focuses on utilizing efficient data structures to handle real-time medical scenarios.

## Technical Features
- Triage Management: Implements a PriorityQueue to manage patient flow based on medical acuity (3 Levels: CRITICAL, SERIOUS, and STABLE).
- Billing Module: Uses a HashMap for O(1) complexity lookup of patient financial records and balance management.
- Staff Organization: Utilizes Object-Oriented principles (Inheritance and Polymorphism) to manage Doctors, Nurses, and Administrators.
- Project Structure: Managed via Maven for dependency handling and standardized build lifecycles.

## Implementation Details
- Language: Java 21
- Build System: Maven
- Architecture: Object-Oriented Design (OOD)

## Project Objectives
The primary goal of this project was to apply advanced Java collections to solve logistical problems in a healthcare setting. By prioritizing patient urgency and maintaining constant-time access to records, the system ensures both medical efficiency and data performance.
