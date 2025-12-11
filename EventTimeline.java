import java.util.*;

class Event {
    private String name;
    private String time;
    private String location;

    public Event(String name, String time, String location) {
        this.name = name;
        this.time = time;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public void updateDetails(String time, String location) {
        this.time = time;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Event: " + name + ", Time: " + time + ", Location: " + location;
    }
}

class EventTimeline {
    private Queue<Event> eventQueue;
    private Stack<Event> removedEvents;
    private HashMap<String, List<Event>> locationMap;
    private PriorityQueue<Event> eventPriorityQueue;
    
    public EventTimeline() {
        this.eventQueue = new LinkedList<>();
        this.removedEvents = new Stack<>();
        this.locationMap = new HashMap<>();
        this.eventPriorityQueue = new PriorityQueue<>(Comparator.comparing(Event::getTime));
    }

    public Event viewNextEvent() {
        return eventQueue.peek();
    }

    public void displayAllEvents() {
        if (eventQueue.isEmpty()) {
            System.out.println("No events scheduled.");
            return;
        }
        System.out.println("All Scheduled Events:");
        for (Event event : eventQueue) {
            System.out.println(event);
        }
    }

    public boolean addEvent(Event newEvent) {
        eventQueue.add(newEvent);
        eventPriorityQueue.add(newEvent);
        locationMap.computeIfAbsent(newEvent.getLocation(), k -> new ArrayList<>()).add(newEvent);
        System.out.println("Event added successfully.");
        return true;
    }

    public boolean updateEvent(String eventName, String newTime, String newLocation) {
        Event targetEvent = null;
        for (Event event : eventQueue) {
            if (event.getName().equals(eventName)) {
                targetEvent = event;
                break;
            }
        }
        if (targetEvent == null) {
            System.out.println("Event not found.");
            return false;
        }
        eventQueue.remove(targetEvent);
        targetEvent.updateDetails(newTime, newLocation);
        return addEvent(targetEvent);
    }

    public void removeEvent() {
        if (!eventQueue.isEmpty()) {
            Event removedEvent = eventQueue.poll();
            removedEvents.push(removedEvent);
            System.out.println("Removed event: " + removedEvent);
        } else {
            System.out.println("No events to remove.");
        }
    }

    public void undoLastRemoval() {
        if (!removedEvents.isEmpty()) {
            Event restoredEvent = removedEvents.pop();
            eventQueue.add(restoredEvent);
            System.out.println("Restored event: " + restoredEvent);
        } else {
            System.out.println("No event to restore.");
        }
    }

    public void findEventByLocation(String location) {
        List<Event> events = locationMap.get(location);
        if (events == null || events.isEmpty()) {
            System.out.println("No events found at this location.");
        } else {
            System.out.println("Events at " + location + ":");
            for (Event event : events) {
                System.out.println(event);
            }
        }
    }
    
    public void listUpcomingEvents() {
        System.out.println("Upcoming events:");
        List<Event> events = new ArrayList<>(eventPriorityQueue);
        for (Event event : events) {
            System.out.println(event);
        }
    }
    
    public void clearAllEvents() {
        eventQueue.clear();
        removedEvents.clear();
        locationMap.clear();
        eventPriorityQueue.clear();
        System.out.println("All events have been cleared.");
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EventTimeline timeline = new EventTimeline();
        boolean running = true;

        while (running) {
            System.out.println("\nEvent Timeline Menu:");
            System.out.println("1. Add Event");
            System.out.println("2. Remove Next Event");
            System.out.println("3. Update Event");
            System.out.println("4. View Next Event");
            System.out.println("5. Display All Events");
            System.out.println("6. Undo Last Removal");
            System.out.println("7. Find Events by Location");
            System.out.println("8. List Upcoming Events");
            System.out.println("9. Clear All Events");
            System.out.println("10. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter event name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter event time: ");
                    String time = scanner.nextLine();
                    System.out.print("Enter event location: ");
                    String location = scanner.nextLine();
                    timeline.addEvent(new Event(name, time, location));
                    break;
                case 2:
                    timeline.removeEvent();
                    break;
                case 3:
                    System.out.print("Enter event name: ");
                    String eventName = scanner.nextLine();
                    System.out.print("Enter new time: ");
                    String newTime = scanner.nextLine();
                    System.out.print("Enter new location: ");
                    String newLocation = scanner.nextLine();
                    timeline.updateEvent(eventName, newTime, newLocation);
                    break;
                case 4:
                    System.out.println("Next Event: " + timeline.viewNextEvent());
                    break;
                case 5:
                    timeline.displayAllEvents();
                    break;
                case 6:
                    timeline.undoLastRemoval();
                    break;
                case 7:
                    System.out.print("Enter location: ");
                    String loc = scanner.nextLine();
                    timeline.findEventByLocation(loc);
                    break;
                case 8:
                    timeline.listUpcomingEvents();
                    break;
                case 9:
                    timeline.clearAllEvents();
                    break;
                case 10:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }
}
