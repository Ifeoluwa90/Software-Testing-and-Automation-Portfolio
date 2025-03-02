package task;

import java.util.HashMap;
import java.util.Map;

public class TaskService {
    private final Map<String, Task> tasks;
    
    public TaskService() {
        tasks = new HashMap<>();
    }
    
    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        
        if (tasks.containsKey(task.getId())) {
            throw new IllegalArgumentException("Task ID already exists");
        }
        
        tasks.put(task.getId(), task);
    }
    
    public void deleteTask(String id) {
        if (!tasks.containsKey(id)) {
            throw new IllegalArgumentException("Task not found with ID: " + id);
        }
        
        tasks.remove(id);
    }
    
    public Task getTask(String id) {
        if (!tasks.containsKey(id)) {
            throw new IllegalArgumentException("Task not found with ID: " + id);
        }
        
        return tasks.get(id);
    }
    
    public void updateTaskName(String id, String newName) {
        Task task = getTask(id);
        task.setName(newName);
    }
    
    public void updateTaskDescription(String id, String newDescription) {
        Task task = getTask(id);
        task.setDescription(newDescription);
    }
}