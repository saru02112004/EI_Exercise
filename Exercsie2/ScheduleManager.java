import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class ScheduleManager {
    private static ScheduleManager instance;
    private final List<Task> tasks;
    private final List<ScheduleObserver> observers;

    private ScheduleManager() {
        this.tasks = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public static synchronized ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public void addTask(Task task) {
        if (hasConflict(task)) {
            String message = "Conflict detected: Task '" + task.getDescription() + "' overlaps with existing tasks.";
            notifyObservers(message);
            return;
        }
        tasks.add(task);
        tasks.sort(Comparator.comparing(Task::getStartTime));
        notifyObservers("Task added successfully: " + task.getDescription());
    }

    public void removeTask(String description) {
        Task taskToRemove = tasks.stream()
            .filter(t -> t.getDescription().equalsIgnoreCase(description))
            .findFirst()
            .orElse(null);
        if (taskToRemove != null) {
            tasks.remove(taskToRemove);
            notifyObservers("Task removed successfully: " + description);
        } else {
            notifyObservers("Error: Task not found - " + description);
        }
    }

    public void markTaskAsCompleted(String description) {
        Task task = tasks.stream()
            .filter(t -> t.getDescription().equalsIgnoreCase(description))
            .findFirst()
            .orElse(null);
        if (task != null) {
            task.setCompleted(true);
            notifyObservers("Task marked as completed: " + description);
        } else {
            notifyObservers("Error: Task not found - " + description);
        }
    }

    public void editTask(String oldDescription, String newDescription, String newStartTimeStr, String newEndTimeStr, String newPriority) {
        Task taskToEdit = tasks.stream()
            .filter(t -> t.getDescription().equalsIgnoreCase(oldDescription))
            .findFirst()
            .orElse(null);
        if (taskToEdit != null) {
            try {
                LocalTime newStartTime = LocalTime.parse(newStartTimeStr, DateTimeFormatter.ofPattern("HH:mm"));
                LocalTime newEndTime = LocalTime.parse(newEndTimeStr, DateTimeFormatter.ofPattern("HH:mm"));
                Task newTask = new Task(newDescription, newStartTime, newEndTime, newPriority);
                if (hasConflict(newTask, oldDescription)) {
                    notifyObservers("Conflict detected: Edited task overlaps with existing tasks.");
                    return;
                }
                tasks.remove(taskToEdit);
                tasks.add(newTask);
                tasks.sort(Comparator.comparing(Task::getStartTime));
                notifyObservers("Task edited successfully: " + oldDescription);
            } catch (Exception e) {
                notifyObservers("Error editing task: " + e.getMessage());
            }
        } else {
            notifyObservers("Error: Task not found - " + oldDescription);
        }
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks scheduled for the day.");
            return;
        }
        System.out.println("\n--- Astronaut Schedule ---");
        tasks.forEach(System.out::println);
    }

    public void viewTasksByPriority(String priority) {
        List<Task> filteredTasks = tasks.stream()
            .filter(t -> t.getPriority().equalsIgnoreCase(priority))
            .collect(Collectors.toList());
        if (filteredTasks.isEmpty()) {
            System.out.println("No tasks found with priority: " + priority);
            return;
        }
        System.out.println("\n--- Tasks with Priority: " + priority + " ---");
        filteredTasks.forEach(System.out::println);
    }

    public void addObserver(ScheduleObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(String message) {
        for (ScheduleObserver observer : observers) {
            observer.update(message);
        }
    }

    private boolean hasConflict(Task newTask) {
        return tasks.stream().anyMatch(existingTask ->
            newTask.getStartTime().isBefore(existingTask.getEndTime()) &&
            newTask.getEndTime().isAfter(existingTask.getStartTime())
        );
    }

    private boolean hasConflict(Task newTask, String oldDescription) {
        return tasks.stream().anyMatch(existingTask ->
            !existingTask.getDescription().equalsIgnoreCase(oldDescription) &&
            newTask.getStartTime().isBefore(existingTask.getEndTime()) &&
            newTask.getEndTime().isAfter(existingTask.getStartTime())
        );
    }
}
