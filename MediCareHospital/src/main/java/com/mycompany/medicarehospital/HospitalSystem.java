package com.mycompany.medicarehospital;

import java.util.ArrayList;
import java.util.List;

public class HospitalSystem {

    private ArrayList<Patient> patients;
    private Bed[] beds;

    public HospitalSystem() {

        patients = new ArrayList<>();

        // Create 20 beds
        beds = new Bed[20];

        for (int i = 0; i < 20; i++) {
            beds[i] = new Bed(String.format("B%02d", i + 1));
        }
    }

    // =========================
    // PATIENT MANAGEMENT
    // =========================

    public boolean registerPatient(Patient patient) {

        if (patient == null) {
            return false;
        }

        // Prevent duplicate Patient IDs
        if (searchPatient(patient.getPatientId()) != null) {
            return false;
        }

        patients.add(patient);
        return true;
    }

    public Patient searchPatient(String patientId) {

        for (Patient patient : patients) {

            if (patient.getPatientId().equalsIgnoreCase(patientId)) {
                return patient;
            }
        }

        return null;
    }

    public boolean updatePatient(
            String patientId,
            String firstName,
            String lastName,
            int age,
            String gender,
            String medicalCondition,
            PatientCategory category) {

        Patient patient = searchPatient(patientId);

        if (patient == null) {
            return false;
        }

        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setAge(age);
        patient.setGender(gender);
        patient.setMedicalCondition(medicalCondition);
        patient.setCategory(category);

        return true;
    }

    public boolean deletePatient(String patientId) {

        Patient patient = searchPatient(patientId);

        if (patient == null) {
            return false;
        }

        // Release bed before deleting patient
        releaseBed(patientId);

        patients.remove(patient);

        return true;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    // =========================
    // BED MANAGEMENT
    // =========================

    public boolean allocateBed(String patientId, String bedNumber) {

        Patient patient = searchPatient(patientId);

        // Only Inpatients can get beds
        if (!(patient instanceof Inpatient)) {
            return false;
        }

        Bed bed = findBed(bedNumber);

        // Bed does not exist
        if (bed == null) {
            return false;
        }

        // Bed is already occupied
        if (bed.isOccupied()) {
            return false;
        }

        // Allocate the bed
        bed.allocate(patientId);

        Inpatient inpatient = (Inpatient) patient;

        inpatient.setBedNumber(bedNumber);

        return true;
    }

    public boolean releaseBed(String patientId) {

        boolean released = false;

        for (Bed bed : beds) {

            if (patientId.equalsIgnoreCase(bed.getPatientId())) {

                bed.release();

                released = true;
            }
        }

        Patient patient = searchPatient(patientId);

        if (patient instanceof Inpatient) {

            Inpatient inpatient = (Inpatient) patient;

            inpatient.setBedNumber("Not Allocated");
        }

        return released;
    }

    private Bed findBed(String bedNumber) {

        for (Bed bed : beds) {

            if (bed.getBedNumber().equalsIgnoreCase(bedNumber)) {
                return bed;
            }
        }

        return null;
    }

    // =========================
    // BED COUNTS
    // =========================

    public int getAvailableBedCount() {

        int count = 0;

        for (Bed bed : beds) {

            if (!bed.isOccupied()) {
                count++;
            }
        }

        return count;
    }

    public int getOccupiedBedCount() {

        return beds.length - getAvailableBedCount();
    }

    // =========================
    // WARD LAYOUT
    // =========================

    public void displayWardLayout() {

        System.out.println("\n===== WARD LAYOUT =====");

        for (int i = 0; i < beds.length; i++) {

            String status;

            if (beds[i].isOccupied()) {
                status = "OCCUPIED";
            } else {
                status = "AVAILABLE";
            }

            System.out.printf(
                    "%s [%s]\t",
                    beds[i].getBedNumber(),
                    status
            );

            // 5 beds per row
            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
        }
    }

    // =========================
    // AVAILABLE BEDS
    // =========================

    public void displayAvailableBeds() {

        System.out.println("\n===== AVAILABLE BEDS =====");

        boolean found = false;

        for (Bed bed : beds) {

            if (!bed.isOccupied()) {

                System.out.print(
                        bed.getBedNumber() + " "
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("No beds available.");
        } else {
            System.out.println();
        }
    }

    // =========================
    // OCCUPIED BEDS
    // =========================

    public void displayOccupiedBeds() {

        System.out.println("\n===== OCCUPIED BEDS =====");

        boolean found = false;

        for (Bed bed : beds) {

            if (bed.isOccupied()) {

                System.out.println(
                        bed.getBedNumber()
                        + " -> Patient ID: "
                        + bed.getPatientId()
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("No occupied beds.");
        }
    }

    // =========================
    // DISPLAY ALL PATIENTS
    // =========================

    public void displayAllPatients() {

        System.out.println("\n===== ALL PATIENTS =====");

        if (patients.isEmpty()) {

            System.out.println("No patients registered.");

            return;
        }

        for (Patient patient : patients) {

            patient.displayDetails();

            System.out.println("-------------------------");
        }
    }

    // =========================
    // REPORTS
    // =========================

    public void displayReports() {

        System.out.println("\n===== HOSPITAL REPORT =====");

        int totalPatients = patients.size();
        int occupiedBeds = getOccupiedBedCount();
        int availableBeds = getAvailableBedCount();

        double occupancy =
                (occupiedBeds / 20.0) * 100;

        System.out.println(
                "Total Patients: " + totalPatients
        );

        System.out.println(
                "Occupied Beds: " + occupiedBeds
        );

        System.out.println(
                "Available Beds: " + availableBeds
        );

        System.out.printf(
                "Ward Occupancy: %.2f%%%n",
                occupancy
        );
    }
}