public interface ScheduleObserver {
    void update(String message);
}

public class AstronautObserver implements ScheduleObserver {
    private final String name;

    public AstronautObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.printf("[Notification for %s] %s%n", name, message);
    }
}
