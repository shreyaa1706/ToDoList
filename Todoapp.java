import java.util.ArrayList;
import java.util.Scanner;

class Task {
    String title;
    boolean isDone;

    Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return (isDone ? "[✓] " : "[ ] ") + title;
    }
}

public class Todoapp {
    static ArrayList<Task> tasks = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("📝 Welcome to Your Java To-Do List!");

        while (true) {
            showMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addTask();
                    break;
                case "2":
                    viewTasks();
                    break;
                case "3":
                    markTaskAsDone();
                    break;
                case "4":
                    deleteTask();
                    break;
                case "5":
                    System.out.println("👋 Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    static void showMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1. ➕ Add Task");
        System.out.println("2. 📋 View Tasks");
        System.out.println("3. ✅ Mark Task as Done");
        System.out.println("4. ❌ Delete Task");
        System.out.println("5. 🚪 Exit");
        System.out.print("Enter your choice: ");
    }

    static void addTask() {
        System.out.print("Enter task description: ");
        String title = scanner.nextLine();
        tasks.add(new Task(title));
        System.out.println("Task added!");
    }

    static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet.");
            return;
        }
        System.out.println("Your Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    static void markTaskAsDone() {
        viewTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Enter task number to mark as done: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).isDone = true;
            System.out.println("Marked as done.");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    static void deleteTask() {
        viewTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Enter task number to delete: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            System.out.println("Task deleted.");
        } else {
            System.out.println("Invalid task number.");
        }
    }
}
