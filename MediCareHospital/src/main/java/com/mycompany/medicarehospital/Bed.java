package com.mycompany.medicarehospital;

public class Bed {

    private String bedNumber;
    private boolean occupied;
    private String patientId;

    // Constructor
    public Bed(String bedNumber) {
        this.bedNumber = bedNumber;
        this.occupied = false;
        this.patientId = null;
    }

    // Get bed number
    public String getBedNumber() {
        return bedNumber;
    }

    // Check if bed is occupied
    public boolean isOccupied() {
        return occupied;
    }

    // Get the patient using the bed
    public String getPatientId() {
        return patientId;
    }

    // Allocate bed to a patient
    public void allocate(String patientId) {
        this.patientId = patientId;
        this.occupied = true;
    }

    // Release the bed
    public void release() {
        this.patientId = null;
        this.occupied = false;
    }
}