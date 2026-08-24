package com.mycompany.medicarehospital;

import java.util.Scanner;

public class MediCareHospital {

    static Scanner scanner = new Scanner(System.in);
    static HospitalSystem hospital = new HospitalSystem();

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== MEDICARE HOSPITAL SYSTEM =====");
            System.out.println("1. Register Patient");
            System.out.println("2. Search Patient");
            System.out.println("3. Update Patient");
            System.out.println("4. Delete Patient");
            System.out.println("5. Display All Patients");
            System.out.println("6. Allocate Bed");
            System.out.println("7. Release Bed");
            System.out.println("8. Display Ward Layout");
            System.out.println("9. Display Available Beds");
            System.out.println("10. Display Occupied Beds");
            System.out.println("11. Display Reports");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    registerPatient();
                    break;
                case 2:
                    searchPatient();
                    break;
                case 3:
                    updatePatient();
                    break;
                case 4:
                    deletePatient();
                    break;
                case 5:
                    hospital.displayAllPatients();
                    break;
                case 6:
                    allocateBed();
                    break;
                case 7:
                    releaseBed();
                    break;
                case 8:
                    hospital.displayWardLayout();
                    break;
                case 9:
                    hospital.displayAvailableBeds();
                    break;
                case 10:
                    hospital.displayOccupiedBeds();
                    break;
                case 11:
                    hospital.displayReports();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    public static void registerPatient() {

        System.out.println("\n--- Register Patient ---");

        System.out.print("Patient ID: ");
        String id = scanner.nextLine();

        if (hospital.searchPatient(id) != null) {
            System.out.println("Patient ID already exists!");
            return;
        }

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Gender: ");
        String gender = scanner.nextLine();

        System.out.print("Medical Condition: ");
        String condition = scanner.nextLine();

        System.out.println("1. Inpatient");
        System.out.println("2. Outpatient");
        System.out.println("3. Emergency");

        System.out.print("Choose category: ");
        int categoryChoice = Integer.parseInt(scanner.nextLine());

        Patient patient;

        if (categoryChoice == 1) {

            patient = new Inpatient(
                    id,
                    firstName,
                    lastName,
                    age,
                    gender,
                    condition,
                    "Ward 1",
                    "Not Allocated"
            );

        } else if (categoryChoice == 2) {

            patient = new Patient(
                    id,
                    firstName,
                    lastName,
                    age,
                    gender,
                    condition,
                    PatientCategory.OUTPATIENT
            );

        } else {

            patient = new Patient(
                    id,
                    firstName,
                    lastName,
                    age,
                    gender,
                    condition,
                    PatientCategory.EMERGENCY
            );
        }

        if (hospital.registerPatient(patient)) {
            System.out.println("Patient registered successfully!");
        }
    }

    public static void searchPatient() {

        System.out.print("Enter Patient ID: ");
        String id = scanner.nextLine();

        Patient patient = hospital.searchPatient(id);

        if (patient != null) {
            patient.displayDetails();
        } else {
            System.out.println("Patient not found.");
        }
    }

    public static void updatePatient() {

        System.out.print("Enter Patient ID: ");
        String id = scanner.nextLine();

        Patient patient = hospital.searchPatient(id);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.print("New First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("New Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("New Age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("New Gender: ");
        String gender = scanner.nextLine();

        System.out.print("New Medical Condition: ");
        String condition = scanner.nextLine();

        System.out.println("1. Inpatient");
        System.out.println("2. Outpatient");
        System.out.println("3. Emergency");

        System.out.print("Choose category: ");
        int choice = Integer.parseInt(scanner.nextLine());

        PatientCategory category;

        if (choice == 1) {
            category = PatientCategory.INPATIENT;
        } else if (choice == 2) {
            category = PatientCategory.OUTPATIENT;
        } else {
            category = PatientCategory.EMERGENCY;
        }

        hospital.updatePatient(
                id,
                firstName,
                lastName,
                age,
                gender,
                condition,
                category
        );

        System.out.println("Patient updated successfully!");
    }

    public static void deletePatient() {

        System.out.print("Enter Patient ID: ");
        String id = scanner.nextLine();

        if (hospital.deletePatient(id)) {
            System.out.println("Patient deleted successfully!");
        } else {
            System.out.println("Patient not found.");
        }
    }

    public static void allocateBed() {

        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();

        System.out.print("Enter Bed Number: ");
        String bedNumber = scanner.nextLine();

        if (hospital.allocateBed(patientId, bedNumber)) {
            System.out.println("Bed allocated successfully!");
        } else {
            System.out.println("Bed allocation failed.");
        }
    }

    public static void releaseBed() {

        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();

        if (hospital.releaseBed(patientId)) {
            System.out.println("Bed released successfully!");
        } else {
            System.out.println("Bed could not be released.");
        }
    }
}