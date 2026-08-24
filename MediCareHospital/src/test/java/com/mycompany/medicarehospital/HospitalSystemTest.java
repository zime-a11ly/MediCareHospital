package com.mycompany.medicarehospital;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HospitalSystemTest {

    // ==========================================
    // TEST 1: Hospital System Creation
    // ==========================================
    @Test
    public void testHospitalSystemCreation() {

        HospitalSystem system = new HospitalSystem();

        assertNotNull(system);
    }

    // ==========================================
    // TEST 2: Register Null Patient
    // ==========================================
    @Test
    public void testRegisterNullPatient() {

        HospitalSystem system = new HospitalSystem();

        boolean result = system.registerPatient(null);

        assertFalse(result);
    }

    // ==========================================
    // TEST 3: Search Patient
    // ==========================================
    @Test
    public void testSearchPatient() {

        HospitalSystem system = new HospitalSystem();

        Patient result = system.searchPatient("P001");

        assertNull(result);
    }

    // ==========================================
    // TEST 4: Search Patient Not Found
    // ==========================================
    @Test
    public void testSearchPatientNotFound() {

        HospitalSystem system = new HospitalSystem();

        Patient result = system.searchPatient("P999");

        assertNull(result);
    }

    // ==========================================
    // TEST 5: Update Patient Not Found
    // ==========================================
    @Test
    public void testUpdatePatient() {

        HospitalSystem system = new HospitalSystem();

        boolean result = system.updatePatient(
                "P001",
                "John",
                "Smith",
                25,
                "Male",
                "Flu",
                null
        );

        assertFalse(result);
    }

    // ==========================================
    // TEST 6: Delete Patient Not Found
    // ==========================================
    @Test
    public void testDeletePatient() {

        HospitalSystem system = new HospitalSystem();

        boolean result = system.deletePatient("P001");

        assertFalse(result);
    }

    // ==========================================
    // TEST 7: Get Patients
    // ==========================================
    @Test
    public void testGetPatients() {

        HospitalSystem system = new HospitalSystem();

        List<Patient> result = system.getPatients();

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    // ==========================================
    // TEST 8: Initial Available Beds
    // ==========================================
    @Test
    public void testGetAvailableBedCount() {

        HospitalSystem system = new HospitalSystem();

        int result = system.getAvailableBedCount();

        assertEquals(20, result);
    }

    // ==========================================
    // TEST 9: Initial Occupied Beds
    // ==========================================
    @Test
    public void testGetOccupiedBedCount() {

        HospitalSystem system = new HospitalSystem();

        int result = system.getOccupiedBedCount();

        assertEquals(0, result);
    }

    // ==========================================
    // TEST 10: Allocate Bed
    // ==========================================
    @Test
    public void testAllocateBed() {

        HospitalSystem system = new HospitalSystem();

        boolean result = system.allocateBed(
                "P001",
                "B01"
        );

        assertFalse(result);
        assertEquals(20, system.getAvailableBedCount());
        assertEquals(0, system.getOccupiedBedCount());
    }

    // ==========================================
    // TEST 11: Release Bed
    // ==========================================
    @Test
    public void testReleaseBed() {

        HospitalSystem system = new HospitalSystem();

        boolean result = system.releaseBed("P001");

        assertFalse(result);
        assertEquals(20, system.getAvailableBedCount());
        assertEquals(0, system.getOccupiedBedCount());
    }

    // ==========================================
    // TEST 12: Invalid Bed Number
    // ==========================================
    @Test
    public void testInvalidBedNumber() {

        HospitalSystem system = new HospitalSystem();

        boolean result = system.allocateBed(
                "P001",
                "INVALID"
        );

        assertFalse(result);
    }

    // ==========================================
    // TEST 13: Ward Layout
    // ==========================================
    @Test
    public void testDisplayWardLayout() {

        HospitalSystem system = new HospitalSystem();

        assertDoesNotThrow(() -> {
            system.displayWardLayout();
        });
    }

    // ==========================================
    // TEST 14: Available Beds
    // ==========================================
    @Test
    public void testDisplayAvailableBeds() {

        HospitalSystem system = new HospitalSystem();

        assertDoesNotThrow(() -> {
            system.displayAvailableBeds();
        });
    }

    // ==========================================
    // TEST 15: Occupied Beds
    // ==========================================
    @Test
    public void testDisplayOccupiedBeds() {

        HospitalSystem system = new HospitalSystem();

        assertDoesNotThrow(() -> {
            system.displayOccupiedBeds();
        });
    }

    // ==========================================
    // TEST 16: Display All Patients
    // ==========================================
    @Test
    public void testDisplayAllPatients() {

        HospitalSystem system = new HospitalSystem();

        assertDoesNotThrow(() -> {
            system.displayAllPatients();
        });
    }

    // ==========================================
    // TEST 17: Hospital Reports
    // ==========================================
    @Test
    public void testDisplayReports() {

        HospitalSystem system = new HospitalSystem();

        assertDoesNotThrow(() -> {
            system.displayReports();
        });
    }

    // ==========================================
    // TEST 18: Total Bed Count
    // ==========================================
    @Test
    public void testTotalBedCount() {

        HospitalSystem system = new HospitalSystem();

        int available = system.getAvailableBedCount();
        int occupied = system.getOccupiedBedCount();

        assertEquals(20, available + occupied);
    }

    // ==========================================
    // TEST 19: Release Non-Existing Patient
    // ==========================================
    @Test
    public void testReleaseNonExistingPatient() {

        HospitalSystem system = new HospitalSystem();

        boolean result = system.releaseBed("P999");

        assertFalse(result);
    }

    // ==========================================
    // TEST 20: Delete Non-Existing Patient
    // ==========================================
    @Test
    public void testDeleteNonExistingPatient() {

        HospitalSystem system = new HospitalSystem();

        boolean result = system.deletePatient("P999");

        assertFalse(result);
    }

    // ==========================================
    // TEST 21: Allocate Bed To Non-Existing Patient
    // ==========================================
    @Test
    public void testAllocateBedToNonExistingPatient() {

        HospitalSystem system = new HospitalSystem();

        boolean result = system.allocateBed(
                "P999",
                "B01"
        );

        assertFalse(result);
    }

    // ==========================================
    // TEST 22: Bed Count After Failed Allocation
    // ==========================================
    @Test
    public void testBedCountAfterFailedAllocation() {

        HospitalSystem system = new HospitalSystem();

        system.allocateBed("P999", "B01");

        assertEquals(20, system.getAvailableBedCount());
        assertEquals(0, system.getOccupiedBedCount());
    }

    // ==========================================
    // TEST 23: Empty Patient List
    // ==========================================
    @Test
    public void testEmptyPatientList() {

        HospitalSystem system = new HospitalSystem();

        assertTrue(system.getPatients().isEmpty());
    }

    // ==========================================
    // TEST 24: Display Empty Patient List
    // ==========================================
    @Test
    public void testDisplayEmptyPatientList() {

        HospitalSystem system = new HospitalSystem();

        assertDoesNotThrow(() -> {
            system.displayAllPatients();
        });
    }

    // ==========================================
    // TEST 25: Display Empty Occupied Beds
    // ==========================================
    @Test
    public void testDisplayEmptyOccupiedBeds() {

        HospitalSystem system = new HospitalSystem();

        assertDoesNotThrow(() -> {
            system.displayOccupiedBeds();
        });
    }

}