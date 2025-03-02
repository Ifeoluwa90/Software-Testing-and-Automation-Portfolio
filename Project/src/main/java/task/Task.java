package task;

public class Task {
    private final String id;  // final because it cannot be updatable
    private String name;
    private String description;

    public Task(String id, String name, String description) {
        // Validate id
        if (id == null || id.length() > 10) {
            throw new IllegalArgumentException("Task ID cannot be null and cannot exceed 10 characters");
        }
        
        // Validate name
        if (name == null || name.length() > 20) {
            throw new IllegalArgumentException("Task name cannot be null and cannot exceed 20 characters");
        }
        
        // Validate description
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Task description cannot be null and cannot exceed 50 characters");
        }
        
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Setters (note: no setter for ID as it's not updatable)
    public void setName(String name) {
        if (name == null || name.length() > 20) {
            throw new IllegalArgumentException("Task name cannot be null and cannot exceed 20 characters");
        }
        this.name = name;
    }

    public void setDescription(String description) {
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Task description cannot be null and cannot exceed 50 characters");
        }
        this.description = description;
    }
}