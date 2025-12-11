import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Guest> guestList = new ArrayList<>();
        TaskScheduler scheduler = new TaskScheduler();
        EventTimeline timeline = new EventTimeline();
        BudgetManagement budgetManager = new BudgetManagement();
        ResourceManager resourceManager = new ResourceManager();

        boolean running = true;
        while (running) {
            System.out.println("\nEvent Management System");
            System.out.println("1. Guest Management");
            System.out.println("2. Task Scheduler");
            System.out.println("3. Event Timeline");
            System.out.println("4. Budget Management");
            System.out.println("5. Resource Management");
            System.out.println("6. View Complete Details");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 7.");
                scanner.nextLine();
                continue;
            }
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == 1) {
                handleGuestManagement(scanner, guestList);
            } else if (choice == 2) {
                handleTaskScheduler(scanner, scheduler);
            } else if (choice == 3) {
                handleEventTimeline(scanner, timeline);
            } else if (choice == 4) {
                handleBudgetManagement(scanner, budgetManager);
            } else if (choice == 5) {
                handleResourceManagement(scanner, resourceManager);
            } else if (choice == 6) {
                viewCompleteDetails(guestList, scheduler, timeline, budgetManager, resourceManager);
            } else if (choice == 7) {
                running = false;
                System.out.println("Exiting the system... Goodbye!");
            } else {
                System.out.println("Invalid choice. Please select a valid option.");
            }
        }
        scanner.close();
    }

    private static void viewCompleteDetails(ArrayList<Guest> guestList, TaskScheduler scheduler, EventTimeline timeline, BudgetManagement budgetManager, ResourceManager resourceManager) {
        System.out.println("\nComplete Event Details:");
        GuestManagement.displayAllGuests(guestList);
        scheduler.displayAllTasks();
        timeline.displayAllEvents();
        budgetManager.displayAllExpenses();
        resourceManager.displayAllResources();
    }

    private static void handleGuestManagement(Scanner scanner, ArrayList<Guest> guestList) {
        boolean running = true;
        while (running) {
            System.out.println("\nGuest Management Menu:");
            System.out.println("1. Add Guest");
            System.out.println("2. Display All Guests");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == 3) {
                running = false;
                continue;
            } else if (choice == 1) {
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                System.out.print("Enter contact: ");
                String contact = scanner.nextLine();
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                System.out.print("Enter address: ");
                String address = scanner.nextLine();
                System.out.print("Enter room number: ");
                int roomNumber = scanner.nextInt();
                scanner.nextLine();
                guestList.add(new Guest(name, contact, email, address, roomNumber));
                System.out.println("Guest added successfully.");
            } else if (choice == 2) {
                GuestManagement.displayAllGuests(guestList);
            }
        }
    }

    private static void handleTaskScheduler(Scanner scanner, TaskScheduler scheduler) {
        boolean running = true;
        while (running) {
            System.out.println("\nTask Scheduler Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. View Next Task");
            System.out.println("4. Complete Task");
            System.out.println("5. Display All Tasks");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == 6) {
                running = false;
                continue;
            }
            
            scheduler.handleInput(choice, scanner);
        }
    }

    private static void handleEventTimeline(Scanner scanner, EventTimeline timeline) {
        boolean running = true;
        while (running) {
            System.out.println("\nEvent Timeline Menu:");
            System.out.println("1. Add Event");
            System.out.println("2. Display All Events");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == 3) {
                running = false;
                continue;
            } else if (choice == 1) {
                System.out.print("Enter event name: ");
                String name = scanner.nextLine();
                System.out.print("Enter event time: ");
                String time = scanner.nextLine();
                System.out.print("Enter event location: ");
                String location = scanner.nextLine();
                timeline.addEvent(new Event(name, time, location));
                System.out.println("Event added successfully.");
            } else if (choice == 2) {
                timeline.displayAllEvents();
            }
        }
    }
    
    
    private static void handleBudgetManagement(Scanner scanner, BudgetManagement budgetManager) {
        boolean running = true;
        while (running) {
            System.out.println("\nBudget Management Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. Undo Last Expense");
            System.out.println("3. View Last Expense");
            System.out.println("4. Display All Expenses");
            System.out.println("5. Clear All Expenses");
            System.out.println("6. Find Expense by Category");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter expense category: ");
                String category = scanner.nextLine();
                System.out.print("Enter expense amount: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();
                budgetManager.addExpense(category, amount);
            } else if (choice == 2) {
                budgetManager.undoLastExpense();
            } else if (choice == 3) {
                budgetManager.viewLastExpense();
            } else if (choice == 4) {
                budgetManager.displayAllExpenses();
            } else if (choice == 5) {
                budgetManager.clearAllExpenses();
            } else if (choice == 6) {
                System.out.print("Enter category to search: ");
                String searchCategory = scanner.nextLine();
                budgetManager.findExpense(searchCategory);
            } else if (choice == 7) {
                running = false;
            } else {
                System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private static void handleResourceManagement(Scanner scanner, ResourceManager resourceManager) {
        boolean running = true;
        while (running) {
            System.out.println("\nResource Management Menu:");
            System.out.println("1. Add Resource");
            System.out.println("2. Update Resource");
            System.out.println("3. Find Resource");
            System.out.println("4. Display All Resources");
            System.out.println("5. Check Availability");
            System.out.println("6. Remove Resource");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter resource name: ");
                String name = scanner.nextLine();
                System.out.print("Enter quantity: ");
                int quantity = scanner.nextInt();
                System.out.print("Is it available? (true/false): ");
                boolean available = scanner.nextBoolean();
                scanner.nextLine();
                resourceManager.addResource(name, quantity, available);
            } else if (choice == 2) {
                System.out.print("Enter resource name to update: ");
                String updateName = scanner.nextLine();
                System.out.print("Enter new quantity: ");
                int newQuantity = scanner.nextInt();
                System.out.print("Is it available? (true/false): ");
                boolean newAvailability = scanner.nextBoolean();
                scanner.nextLine();
                resourceManager.updateResource(updateName, newQuantity, newAvailability);
            } else if (choice == 3) {
                System.out.print("Enter resource name to find: ");
                String findName = scanner.nextLine();
                ResourceManager.Resource found = resourceManager.findResource(findName);
                if (found != null) {
                    System.out.println(found);
                }
            } else if (choice == 4) {
                resourceManager.displayAllResources();
            } else if (choice == 5) {
                System.out.print("Enter resource name to check availability: ");
                String checkName = scanner.nextLine();
                boolean isAvailable = resourceManager.checkAvailability(checkName);
                System.out.println(checkName + (isAvailable ? " is Available." : " is Not Available."));
            } else if (choice == 6) {
                System.out.print("Enter resource name to remove: ");
                String removeName = scanner.nextLine();
                resourceManager.removeResource(removeName);
            } else if (choice == 7) {
                running = false;
            } else {
                System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

}
