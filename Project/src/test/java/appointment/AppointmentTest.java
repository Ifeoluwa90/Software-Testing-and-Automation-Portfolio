package appointment;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppointmentTest {
    private Date futureDate;

    @BeforeEach
    void setUp() {
        futureDate = new Date(System.currentTimeMillis() + 86400000); // tomorrow
    }

    @Test
    void testValidAppointment() {
        Appointment appointment = new Appointment("A123", futureDate, "Test Appointment");
        
        assertAll("Appointment properties",
            () -> assertEquals("A123", appointment.getAppointmentId()),
            () -> assertEquals(futureDate, appointment.getAppointmentDate()),
            () -> assertEquals("Test Appointment", appointment.getDescription())
        );
    }
    
    @Test
    void testNullAppointmentId() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(null, futureDate, "Test");
        });
    }

    @Test
    void testNullAppointmentDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("A123", null, "Test");
        });
    }

    @Test
    void testNullDescription() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("A123", futureDate, null);
        });
    }
    
    @Test
    void testInvalidAppointmentId() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("12345678901", futureDate, "Test"); // > 10 chars
        });
    }
    
    @Test
    void testPastDate() {
        Date pastDate = new Date(System.currentTimeMillis() - 86400000); // yesterday
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("A123", pastDate, "Test");
        });
    }
    
    @Test
    void testLongDescription() {
        String longDesc = "This is a very long description that exceeds fifty characters limit";
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("A123", futureDate, longDesc);
        });
    }
}

