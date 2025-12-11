import java.util.ArrayList;
import java.util.LinkedList;

public class GuestManagement {

    public static void removeGuest(LinkedList<Guest> guestList, String name, String contact) {
        guestList.removeIf(guest -> guest.getName().equalsIgnoreCase(name) && guest.getContact().equals(contact));
    }

    public static Guest searchGuest(ArrayList<Guest> guestList, String name, String contact) {
        for (Guest guest : guestList) {
            if (guest.getName().equalsIgnoreCase(name) && guest.getContact().equals(contact)) {
                return guest;
            }
        }
        return null;
    }

    public static void displayAllGuests(ArrayList<Guest> guestList) {
        for (Guest guest : guestList) {
            System.out.println(guest);
        }
    }

    public void addGuestIfNotDuplicate(ArrayList<Guest> guestList, String name, String contact, String email, String address, int roomNumber) {
        for (Guest guest : guestList) {
            if (guest.getContact().equals(contact)) {
                System.out.println("Guest with contact number " + contact + " already exists! Cannot add duplicate.");
                return;
            }
        }
        guestList.add(new Guest(name, contact, email, address, roomNumber));
        System.out.println("Guest added successfully!");
    }
}
