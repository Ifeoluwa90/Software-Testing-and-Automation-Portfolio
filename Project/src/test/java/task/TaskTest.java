package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class TaskTest {
    
    @Test
    void testCreateTaskSuccessfully() {
        Task task = new Task("123", "Test Task", "This is a test task");
        assertNotNull(task);
        assertEquals("123", task.getId());
        assertEquals("Test Task", task.getName());
        assertEquals("This is a test task", task.getDescription());
    }

    @Test
    void testTaskIdTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("12345678901", "Test Task", "This is a test task");
        });
    }

    @Test
    void testTaskIdNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task(null, "Test Task", "This is a test task");
        });
    }

    @Test
    void testTaskNameTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("123", "This name is way too long for a task", "This is a test task");
        });
    }

    @Test
    void testTaskNameNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("123", null, "This is a test task");
        });
    }

    @Test
    void testTaskDescriptionTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("123", "Test Task", "This description is way too long for a task and should definitely cause an error");
        });
    }

    @Test
    void testTaskDescriptionNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("123", "Test Task", null);
        });
    }

    @Test
    void testUpdateNameSuccessfully() {
        Task task = new Task("123", "Test Task", "This is a test task");
        task.setName("New Name");
        assertEquals("New Name", task.getName());
    }

    @Test
    void testUpdateNameTooLong() {
        Task task = new Task("123", "Test Task", "This is a test task");
        assertThrows(IllegalArgumentException.class, () -> {
            task.setName("This name is way too long for a task");
        });
    }

    @Test
    void testUpdateNameNull() {
        Task task = new Task("123", "Test Task", "This is a test task");
        assertThrows(IllegalArgumentException.class, () -> {
            task.setName(null);
        });
    }

    @Test
    void testUpdateDescriptionSuccessfully() {
        Task task = new Task("123", "Test Task", "This is a test task");
        task.setDescription("New Description");
        assertEquals("New Description", task.getDescription());
    }

    @Test
    void testUpdateDescriptionTooLong() {
        Task task = new Task("123", "Test Task", "This is a test task");
        assertThrows(IllegalArgumentException.class, () -> {
            task.setDescription("This description is way too long for a task and should definitely cause an error");
        });
    }

    @Test
    void testUpdateDescriptionNull() {
        Task task = new Task("123", "Test Task", "This is a test task");
        assertThrows(IllegalArgumentException.class, () -> {
            task.setDescription(null);
        });
    }
}