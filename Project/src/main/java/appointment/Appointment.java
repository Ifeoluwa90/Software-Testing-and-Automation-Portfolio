package appointment;

import java.util.Date;

public class Appointment {
    private final String appointmentId;  // Unique, not null, max 10 chars, not updatable
    private Date appointmentDate;        // Not null, not in past
    private String description;          // Not null, max 50 chars
    
    public Appointment(String appointmentId, Date appointmentDate, String description) {
        validateAppointmentId(appointmentId);
        validateAppointmentDate(appointmentDate);
        validateDescription(description);
        
        this.appointmentId = appointmentId;
        this.appointmentDate = appointmentDate;
        this.description = description;
    }
    
    private void validateAppointmentId(String appointmentId) {
        if (appointmentId == null || appointmentId.length() > 10) {
            throw new IllegalArgumentException("Appointment ID must not be null and cannot exceed 10 characters");
        }
    }
    
    private void validateAppointmentDate(Date appointmentDate) {
        if (appointmentDate == null || appointmentDate.before(new Date())) {
            throw new IllegalArgumentException("Appointment date must not be null and cannot be in the past");
        }
    }
    
    private void validateDescription(String description) {
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Description must not be null and cannot exceed 50 characters");
        }
    }
    
    // Getters
    public String getAppointmentId() {
        return appointmentId;
    }
    
    public Date getAppointmentDate() {
        return appointmentDate;
    }
    
    public String getDescription() {
        return description;
    }
    
    // Setters (except for appointmentId which is final)
    public void setAppointmentDate(Date appointmentDate) {
        validateAppointmentDate(appointmentDate);
        this.appointmentDate = appointmentDate;
    }
    
    public void setDescription(String description) {
        validateDescription(description);
        this.description = description;
    }
}
