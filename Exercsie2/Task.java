import java.time.LocalTime;
import java.util.Objects;

public class Task {
    private final String description;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final String priority;
    private boolean isCompleted;

    public Task(String description, LocalTime startTime, LocalTime endTime, String priority) {
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time.");
        }
        this.description = Objects.requireNonNull(description, "Description cannot be null.");
        this.startTime = Objects.requireNonNull(startTime, "Start time cannot be null.");
        this.endTime = Objects.requireNonNull(endTime, "End time cannot be null.");
        this.priority = Objects.requireNonNull(priority, "Priority cannot be null.");
        this.isCompleted = false;
    }

    // Getters
    public String getDescription() { return description; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }
    public String getPriority() { return priority; }
    public boolean isCompleted() { return isCompleted; }

    // Setter
    public void setCompleted(boolean completed) { this.isCompleted = completed; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return description.equals(task.description);
    }

    @Override
    public int hashCode() { return Objects.hash(description); }

    @Override
    public String toString() {
        return String.format(
            "%s - %s: %s [Priority: %s] %s",
            startTime, endTime, description, priority,
            isCompleted ? "(Completed)" : ""
        );
    }
}
