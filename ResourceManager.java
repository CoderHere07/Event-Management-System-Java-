import java.util.Scanner;

public class ResourceManager {
    private static final int MAX_RESOURCES = 10;

    static class Resource {
        private String name;
        private int quantity;
        private boolean available;

        public Resource(String name, int quantity, boolean available) {
            this.name = name;
            this.quantity = quantity;
            this.available = available;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        @Override
        public String toString() {
            return "Resource: " + name + ", Quantity: " + quantity + ", Available: " + available;
        }
    }

    private Resource[] resources;
    private int resourceCount;

    public ResourceManager() {
        resources = new Resource[MAX_RESOURCES];
        resourceCount = 0;
    }

    public void addResource(String name, int quantity, boolean available) {
        if (resourceCount < MAX_RESOURCES) {
            resources[resourceCount] = new Resource(name, quantity, available);
            resourceCount++;
            System.out.println(name + " Added Successfully.");
        } else {
            System.out.println("Resource List is Full. Cannot Add More Resources.");
        }
    }

    public void updateResource(String name, int newQuantity, boolean newAvailability) {
        for (int i = 0; i < resourceCount; i++) {
            if (resources[i].getName().equalsIgnoreCase(name)) {
                resources[i].setQuantity(newQuantity);
                resources[i].setAvailable(newAvailability);
                System.out.println(name + " Update Successfully.");
                return;
            }
        }
        System.out.println(name + " Not Found.");
    }

    public Resource findResource(String name) {
        for (int i = 0; i < resourceCount; i++) {
            if (resources[i].getName().equalsIgnoreCase(name)) {
                return resources[i];
            }
        }
        System.out.println(name + " Not Found.");
        return null;
    }

    public void displayAllResources() {
        if (resourceCount == 0) {
            System.out.println("No Resources Available.");
        } else {
            System.out.println("\nAll Resources:");
            for (int i = 0; i < resourceCount; i++) {
                System.out.println(resources[i]);
            }
        }
    }

    public boolean checkAvailability(String name) {
        Resource resource = findResource(name);
        if (resource != null) {
            return resource.isAvailable();
        } else {
            return false;
        }
    }

    public void removeResource(String name) {
        for (int i = 0; i < resourceCount; i++) {
            if (resources[i].getName().equalsIgnoreCase(name)) {
                for (int j = i; j < resourceCount - 1; j++) {
                    resources[j] = resources[j + 1];
                }
                resources[resourceCount - 1] = null;  
                resourceCount--;
                System.out.println(name + " Removed Successfully.");
                return;
            }
        }
        System.out.println(name + " Not Found.");
    }


    public static void main(String[] args) {
        ResourceManager resourceManager = new ResourceManager();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 7) {
            System.out.println("\nResource Manager Menu:");
            System.out.println("1. Add Resource");
            System.out.println("2. Update Resource");
            System.out.println("3. Find Resource");
            System.out.println("4. Display All Resources");
            System.out.println("5. Check Availability");
            System.out.println("6. Remove Resource");
            System.out.println("7. Exit");
            System.out.print("Enter Your Choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter Resource Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Quantity: ");
                int quantity = scanner.nextInt();
                System.out.print("Is it Available? (true/false): ");
                boolean available = scanner.nextBoolean();
                resourceManager.addResource(name, quantity, available);

            } else if (choice == 2) {
                System.out.print("Enter Resource Name to Update: ");
                String name = scanner.nextLine();
                System.out.print("Enter New Quantity: ");
                int quantity = scanner.nextInt();
                System.out.print("Is it Available? (true/false): ");
                boolean available = scanner.nextBoolean();
                resourceManager.updateResource(name, quantity, available);

            } else if (choice == 3) {
                System.out.print("Enter Resource Name to Find: ");
                String name = scanner.nextLine();
                Resource found = resourceManager.findResource(name);
                if (found != null) {
                    System.out.println(found);
                }

            } else if (choice == 4) {
                resourceManager.displayAllResources();

            } else if (choice == 5) {
                System.out.print("Enter Resource Name to Check Availability: ");
                String name = scanner.nextLine();
                boolean available = resourceManager.checkAvailability(name);
                if (available) {
                    System.out.println(name + " is Available.");
                } else {
                    System.out.println(name + " is Not Available.");
                }

            } else if (choice == 6) {
                System.out.print("Enter Resource Name to Remove: ");
                String name = scanner.nextLine();
                resourceManager.removeResource(name);

            } else if (choice == 7) {
                System.out.println("Exiting Resource Manager. Goodbye!");

            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
