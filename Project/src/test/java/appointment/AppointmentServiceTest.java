package appointment;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppointmentServiceTest {
    private AppointmentService appointmentService;
    private Date futureDate;
    private Appointment testAppointment;
    
    @BeforeEach
    void setUp() {
        appointmentService = new AppointmentService();
        futureDate = new Date(System.currentTimeMillis() + 86400000); // tomorrow
        testAppointment = new Appointment("A123", futureDate, "Test Appointment");
    }
    
    @Test
    void testAddValidAppointment() {
        appointmentService.addAppointment(testAppointment);
        Appointment retrieved = appointmentService.getAppointment("A123");
        
        assertAll("Retrieved appointment",
            () -> assertEquals(testAppointment.getAppointmentId(), retrieved.getAppointmentId()),
            () -> assertEquals(testAppointment.getAppointmentDate(), retrieved.getAppointmentDate()),
            () -> assertEquals(testAppointment.getDescription(), retrieved.getDescription())
        );
    }

    @Test
    void testAddNullAppointment() {
        assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.addAppointment(null);
        });
    }
    
    @Test
    void testAddDuplicateAppointment() {
        Appointment duplicate = new Appointment("A123", futureDate, "Test 2");
        
        appointmentService.addAppointment(testAppointment);
        assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.addAppointment(duplicate);
        });
    }
    
    @Test
    void testDeleteAppointment() {
        appointmentService.addAppointment(testAppointment);
        appointmentService.deleteAppointment("A123");
        assertNull(appointmentService.getAppointment("A123"));
    }

    @Test
    void testDeleteNullAppointmentId() {
        assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.deleteAppointment(null);
        });
    }
    
    @Test
    void testDeleteNonexistentAppointment() {
        assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.deleteAppointment("nonexistent");
        });
    }
}