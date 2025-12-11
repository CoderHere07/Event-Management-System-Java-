class Guest {
    private String name, contact, email, address;
    private int roomNumber;

    public Guest(String name, String contact, String email, String address, int roomNumber) {
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.address = address;
        this.roomNumber = roomNumber;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    @Override
    public String toString() {
        return "Guest{name='" + name + "', contact='" + contact + "', email='" + email + "', address='" + address + "', roomNumber=" + roomNumber + "}";
    }
}