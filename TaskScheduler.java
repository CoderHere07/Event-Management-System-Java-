import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Task implements Comparable<Task> {
    private String name;
    private int priority;

    public Task(String name, int priority) {
        if (priority < 1 || priority > 5) {
            throw new IllegalArgumentException("Priority must be between 1 (highest) and 5 (lowest)");
        }
        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if (priority < 1 || priority > 5) {
            throw new IllegalArgumentException("Priority must be between 1 and 5.");
        }
        this.priority = priority;
    }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.priority, other.priority);
    }

    @Override
    public String toString() {
        return "Task name is: " + name + "', priority is :" + priority ;
    }
}

public class TaskScheduler {
    private ArrayList<Task> taskList;

    public TaskScheduler() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(String name, int priority) {
        taskList.add(new Task(name, priority));
        sortTasks();
        System.out.println("Task added successfully.");
    }

    public boolean removeTask(String taskName) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getName().equalsIgnoreCase(taskName)) {
                taskList.remove(i);
                System.out.println("Task removed successfully.");
                return true;
            }
        }
        System.out.println("Task not found.");
        return false;
    }

    public void getNextTask() {
        if (!taskList.isEmpty()) {
            System.out.println("Next task: " + taskList.get(0));
        } else {
            System.out.println("No tasks available.");
        }
    }

    public void completeTask() {
        if (!taskList.isEmpty()) {
            System.out.println("Completed task: " + taskList.remove(0));
        } else {
            System.out.println("No tasks to complete.");
        }
    }

    public void displayAllTasks() {
        if (taskList.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("All tasks in priority order:");
            for (Task task : taskList) {
                System.out.println(task);
            }
        }
    }

    private void sortTasks() {
        Collections.sort(taskList);
    }

    // **New Methods**
    public boolean editTaskPriority(String taskName, int newPriority) {
        for (Task task : taskList) {
            if (task.getName().equalsIgnoreCase(taskName)) {
                task.setPriority(newPriority);
                sortTasks();
                System.out.println("Task priority updated.");
                return true;
            }
        }
        System.out.println("Task not found.");
        return false;
    }

    public void clearAllTasks() {
        taskList.clear();
        System.out.println("All tasks have been removed.");
    }

    public void findTask(String taskName) {
        for (Task task : taskList) {
            if (task.getName().equalsIgnoreCase(taskName)) {
                System.out.println("Task found: " + task);
                return;
            }
        }
        System.out.println("Task not found.");
    }

    public void getTasksByPriority(int priority) {
        boolean found = false;
        System.out.println("Tasks with priority " + priority + ":");
        for (Task task : taskList) {
            if (task.getPriority() == priority) {
                System.out.println(task);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks found with this priority.");
        }
    }

    public void reverseTaskOrder() {
        Collections.reverse(taskList);
        System.out.println("Task order reversed.");
    }

    public void handleInput(int choice, Scanner scanner) {
        if (choice == 1) {
            System.out.print("Enter task name: ");
            String name = scanner.nextLine();
            System.out.print("Enter task priority (1 to 5): ");
            int priority = scanner.nextInt();
            scanner.nextLine();
            try {
                addTask(name, priority);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid priority. Task not added.");
            }
        } else if (choice == 2) {
            System.out.print("Enter task name to remove: ");
            String taskName = scanner.nextLine();
            removeTask(taskName);
        } else if (choice == 3) {
            getNextTask();
        } else if (choice == 4) {
            completeTask();
        } else if (choice == 5) {
            displayAllTasks();
        } else if (choice == 6) {
            System.out.print("Enter task name to edit priority: ");
            String taskName = scanner.nextLine();
            System.out.print("Enter new priority (1 to 5): ");
            int newPriority = scanner.nextInt();
            scanner.nextLine();
            editTaskPriority(taskName, newPriority);
        } else if (choice == 7) {
            clearAllTasks();
        } else if (choice == 8) {
            System.out.print("Enter task name to search: ");
            String taskName = scanner.nextLine();
            findTask(taskName);
        } else if (choice == 9) {
            System.out.print("Enter priority level (1 to 5): ");
            int priority = scanner.nextInt();
            scanner.nextLine();
            getTasksByPriority(priority);
        } else if (choice == 10) {
            reverseTaskOrder();
        } else if (choice == 11) {
            System.out.println("Exiting Task Scheduler. Goodbye!");
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

}