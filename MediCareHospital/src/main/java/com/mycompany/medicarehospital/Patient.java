package com.mycompany.medicarehospital;

public class Patient {

    private String patientId;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String medicalCondition;
    private PatientCategory category;

    // Constructor
    public Patient(
            String patientId,
            String firstName,
            String lastName,
            int age,
            String gender,
            String medicalCondition,
            PatientCategory category) {

        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.medicalCondition = medicalCondition;
        this.category = category;
    }

    // Get Patient ID
    public String getPatientId() {
        return patientId;
    }

    // Get First Name
    public String getFirstName() {
        return firstName;
    }

    // Get Last Name
    public String getLastName() {
        return lastName;
    }

    // Get Age
    public int getAge() {
        return age;
    }

    // Get Gender
    public String getGender() {
        return gender;
    }

    // Get Medical Condition
    public String getMedicalCondition() {
        return medicalCondition;
    }

    // Get Category
    public PatientCategory getCategory() {
        return category;
    }

    // Set First Name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Set Last Name
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Set Age
    public void setAge(int age) {
        this.age = age;
    }

    // Set Gender
    public void setGender(String gender) {
        this.gender = gender;
    }

    // Set Medical Condition
    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    // Set Category
    public void setCategory(PatientCategory category) {
        this.category = category;
    }

    // Display patient details
    public void displayDetails() {

        System.out.println("Patient ID: " + patientId);
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Medical Condition: " + medicalCondition);
        System.out.println("Category: " + category);
    }
}