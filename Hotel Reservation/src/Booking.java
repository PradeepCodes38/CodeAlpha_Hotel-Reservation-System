class Booking {
    private String guestName;
    private Room room;
    private int numberOfNights;
    private double totalCost;

    public Booking(String guestName, Room room, int numberOfNights) {
        this.guestName = guestName;
        this.room = room;
        this.numberOfNights = numberOfNights;
        this.totalCost = room.getPricePerNight() * numberOfNights;
    }

    public String getDetails() {
        return "Guest: " + guestName +
                "\nRoom: " + room.getRoomNumber() +
                " (" + room.getCategory() + ")" +
                "\nNights: " + numberOfNights +
                "\nTotal Cost: ₹" + totalCost;
    }
}