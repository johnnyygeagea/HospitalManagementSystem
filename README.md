# Hospital Management System (HMS)

This project is a Java-based application designed to manage core hospital operations, including patient triage, staff management, and billing services. The system focuses on combining efficient in-memory data structures with persistent relational database storage to handle real-time medical scenarios reliably.

---

## Technical Features

*   **Triage Management:** Implements a `PriorityQueue` to manage patient flow based on medical acuity (3 Levels mapped to custom PostgreSQL ENUM types: `CRITICAL`, `SERIOUS`, and `STABLE`).
*   **Billing & Persistence Module:** Transitioned from an in-memory `HashMap` tracking mechanism to a robust **PostgreSQL database backend** utilizing core JDBC patterns. This guarantees $O(1)$ structural access profiles while ensuring multi-session data persistence.
*   **Staff Organization:** Utilizes Object-Oriented principles (Inheritance and Polymorphism) to manage Doctors, Nurses, and Administrators in Java, cleanly mapped to relational tables using foreign key inheritance with `ON DELETE CASCADE`.
*   **Secure Architecture:** Imples decoupled credential handling using system **Environment Variables** (`System.getenv()`) to keep sensitive infrastructure settings isolated from version control.

---

## System Architecture & Stack

- **Language:** Java 21
- **Database:** PostgreSQL
- **Build System & Dependencies:** Maven
- **Driver Integration:** PostgreSQL JDBC Driver

---

## Setup and Installation

### 1. Environment Variables Configuration
To run this application locally, you must configure your runtime environment or your IDE tool to inject the following system environment variables:

| Variable | Description | Example Value |
| --- | --- | --- |
| `DB_URL` | The JDBC database connection target string | `jdbc:postgresql://localhost:5432/hospital_db` |
| `DB_USER` | Your local database authentication username | `postgres` |
| `DB_PASSWORD` | Your local database authentication password | `your_secret_password` |

*(Note: If using VS Code, these can be set inside your localized `.vscode/launch.json` file, which is actively ignored by version control to guarantee security).*

### 2. Database Initialization
Before starting the application, instantiate your schemas by running your SQL script inside your database client (e.g., pgAdmin, DBeaver) against your targeting `hospital_db` instance.

---

## Project Objectives

The primary goal of this project was to bridge advanced Java collections with real-world persistence layers to solve logistical problems in a healthcare setting. By prioritizing patient urgency dynamically and maintaining persistent, constraint-checked access to clinical and financial records, the system ensures both medical reliability and backend data performance.