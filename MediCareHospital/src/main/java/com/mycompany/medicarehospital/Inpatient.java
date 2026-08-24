package com.mycompany.medicarehospital;

public class Inpatient extends Patient {

    private String wardNumber;
    private String bedNumber;

    // Constructor
    public Inpatient(
            String patientId,
            String firstName,
            String lastName,
            int age,
            String gender,
            String medicalCondition,
            String wardNumber,
            String bedNumber) {

        super(
                patientId,
                firstName,
                lastName,
                age,
                gender,
                medicalCondition,
                PatientCategory.INPATIENT
        );

        this.wardNumber = wardNumber;
        this.bedNumber = bedNumber;
    }

    // Get ward number
    public String getWardNumber() {
        return wardNumber;
    }

    // Get bed number
    public String getBedNumber() {
        return bedNumber;
    }

    // Set ward number
    public void setWardNumber(String wardNumber) {
        this.wardNumber = wardNumber;
    }

    // Set bed number
    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    // Display inpatient details
    @Override
    public void displayDetails() {

        super.displayDetails();

        System.out.println("Ward Number: " + wardNumber);
        System.out.println("Bed Number: " + bedNumber);
    }
}