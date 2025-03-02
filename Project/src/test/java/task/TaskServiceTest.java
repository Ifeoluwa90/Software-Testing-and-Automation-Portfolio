package task;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskServiceTest {
    private TaskService taskService;
    
    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }
    
    @Test
    void testAddTask() {
        Task task = new Task("1", "Test Task", "Test Description");
        taskService.addTask(task);
        Task retrievedTask = taskService.getTask("1");
        
        assertAll("Task properties",
            () -> assertNotNull(retrievedTask),
            () -> assertEquals("1", retrievedTask.getId()),
            () -> assertEquals("Test Task", retrievedTask.getName()),
            () -> assertEquals("Test Description", retrievedTask.getDescription())
        );
    }
    
    @Test
    void testAddDuplicateTask() {
        Task task1 = new Task("1", "Test Task", "Test Description");
        Task task2 = new Task("1", "Another Task", "Another Description");
        
        taskService.addTask(task1);
        assertThrows(IllegalArgumentException.class, () -> {
            taskService.addTask(task2);
        });
    }
    
    @Test
    void testAddMultipleTasks() {
        Task task1 = new Task("1", "Task 1", "Description 1");
        Task task2 = new Task("2", "Task 2", "Description 2");
        
        taskService.addTask(task1);
        taskService.addTask(task2);
        
        assertEquals("Task 1", taskService.getTask("1").getName());
        assertEquals("Task 2", taskService.getTask("2").getName());
    }
    
    @Test
    void testDeleteTask() {
        Task task = new Task("1", "Test Task", "Test Description");
        taskService.addTask(task);
        taskService.deleteTask("1");
        
        assertThrows(IllegalArgumentException.class, () -> {
            taskService.getTask("1");
        });
    }
    
    @Test
    void testDeleteNonExistentTask() {
        assertThrows(IllegalArgumentException.class, () -> {
            taskService.deleteTask("nonexistent");
        });
    }
    
    @Test
    void testUpdateTaskName() {
        Task task = new Task("1", "Test Task", "Test Description");
        taskService.addTask(task);
        
        taskService.updateTaskName("1", "Updated Task");
        assertEquals("Updated Task", taskService.getTask("1").getName());
    }
    
    @Test
    void testUpdateTaskDescription() {
        Task task = new Task("1", "Test Task", "Test Description");
        taskService.addTask(task);
        
        taskService.updateTaskDescription("1", "Updated Description");
        assertEquals("Updated Description", taskService.getTask("1").getDescription());
    }
    
    @Test
    void testUpdateNonExistentTaskName() {
        assertThrows(IllegalArgumentException.class, () -> {
            taskService.updateTaskName("nonexistent", "Updated Task");
        });
    }
    
    @Test
    void testUpdateNonExistentTaskDescription() {
        assertThrows(IllegalArgumentException.class, () -> {
            taskService.updateTaskDescription("nonexistent", "Updated Description");
        });
    }
    
    @Test
    void testUpdateTaskNameTooLong() {
        Task task = new Task("1", "Test Task", "Test Description");
        taskService.addTask(task);
        
        assertThrows(IllegalArgumentException.class, () -> {
            taskService.updateTaskName("1", "This name is way too long for a task");
        });
    }
    
    @Test
    void testUpdateTaskDescriptionTooLong() {
        Task task = new Task("1", "Test Task", "Test Description");
        taskService.addTask(task);
        
        assertThrows(IllegalArgumentException.class, () -> {
            taskService.updateTaskDescription("1", 
                "This description is way too long and should definitely cause an error");
        });
    }
}
