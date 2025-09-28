import java.time.LocalTime;
import java.util.Scanner;

public class AstronautScheduleApp {
    public static void main(String[] args) {
        ScheduleManager scheduleManager = ScheduleManager.getInstance();
        TaskFactory taskFactory = new TaskFactory();
        AstronautObserver astronaut = new AstronautObserver("Neil Armstrong");
        scheduleManager.addObserver(astronaut);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Astronaut Daily Schedule Organizer");
        System.out.println("Type 'help' for a list of available commands.");

        while (true) {
            System.out.print("\nEnter command: ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting Astronaut Daily Schedule Organizer. Goodbye!");
                break;
            } else if (input.equalsIgnoreCase("help")) {
                printHelp();
            } else if (input.startsWith("add ")) {
                addTask(input, taskFactory, scheduleManager);
            } else if (input.startsWith("remove ")) {
                String description = input.substring(7).trim();
                scheduleManager.removeTask(description);
            } else if (input.startsWith("complete ")) {
                String description = input.substring(9).trim();
                scheduleManager.markTaskAsCompleted(description);
            } else if (input.startsWith("edit ")) {
                editTask(input, taskFactory, scheduleManager);
            } else if (input.equalsIgnoreCase("view")) {
                scheduleManager.viewTasks();
            } else if (input.startsWith("view ")) {
                String priority = input.substring(5).trim();
                scheduleManager.viewTasksByPriority(priority);
            } else {
                System.out.println("Unknown command. Type 'help' for a list of available commands.");
            }
        }
        scanner.close();
    }

    private static void printHelp() {
        System.out.println("\nAvailable Commands:");
        System.out.println("1. add [description], [start time], [end time], [priority] - Add a new task");
        System.out.println("2. remove [description] - Remove an existing task");
        System.out.println("3. complete [description] - Mark a task as completed");
        System.out.println("4. edit [old description], [new description], [new start time], [new end time], [new priority] - Edit an existing task");
        System.out.println("5. view - View all tasks sorted by start time");
        System.out.println("6. view [priority] - View tasks by priority level");
        System.out.println("7. exit - Exit the application");
    }

    private static void addTask(String input, TaskFactory taskFactory, ScheduleManager scheduleManager) {
        try {
            String[] parts = input.substring(4).split(",");
            if (parts.length < 4) {
                throw new IllegalArgumentException("Invalid input format. Use: add [description], [start time], [end time], [priority]");
            }
            String description = parts[0].trim();
            String startTimeStr = parts[1].trim();
            String endTimeStr = parts[2].trim();
            String priority = parts[3].trim();
            Task task = taskFactory.createTask(description, startTimeStr, endTimeStr, priority);
            scheduleManager.addTask(task);
        } catch (Exception e) {
            System.out.println("Error adding task: " + e.getMessage());
        }
    }

    private static void editTask(String input, TaskFactory taskFactory, ScheduleManager scheduleManager) {
        try {
            String[] parts = input.substring(5).split(",");
            if (parts.length < 5) {
                throw new IllegalArgumentException("Invalid input format. Use: edit [old description], [new description], [new start time], [new end time], [new priority]");
            }
            String oldDescription = parts[0].trim();
            String newDescription = parts[1].trim();
            String newStartTimeStr = parts[2].trim();
            String newEndTimeStr = parts[3].trim();
            String newPriority = parts[4].trim();
            scheduleManager.editTask(oldDescription, newDescription, newStartTimeStr, newEndTimeStr, newPriority);
        } catch (Exception e) {
            System.out.println("Error editing task: " + e.getMessage());
        }
    }
}
