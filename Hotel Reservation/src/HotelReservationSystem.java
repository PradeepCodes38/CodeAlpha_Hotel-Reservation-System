import java.util.ArrayList;
import java.util.Scanner;

class Room {
    private int roomNumber;
    private String category; // e.g., Single, Double, Suite
    private double pricePerNight;
    private boolean isAvailable;

    public Room(int roomNumber, String category, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void reserve() {
        this.isAvailable = false;
    }

    public void release() {
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber +
                " | Category: " + category +
                " | Price/Night: ₹" + pricePerNight +
                " | Available: " + (isAvailable ? "Yes" : "No");
    }
}

class Booking {
    private String guestName;
    private Room room;
    private int numberOfNights;
    private double totalCost;

    public Booking(String guestName, Room room, int numberOfNights) {
        this.guestName = guestName;
        this.room = room;
        this.numberOfNights = numberOfNights;
        this.totalCost = numberOfNights * room.getPricePerNight();
    }

    public String getDetails() {
        return "Guest: " + guestName +
                "\nRoom: " + room.getRoomNumber() +
                "\nCategory: " + room.getCategory() +
                "\nNights: " + numberOfNights +
                "\nTotal Cost: ₹" + totalCost;
    }
}

public class HotelReservationSystem {
    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Booking> bookings = new ArrayList<>();

    // Initialize some rooms
    static {
        rooms.add(new Room(101, "Single", 50.0));
        rooms.add(new Room(102, "Double", 75.0));
        rooms.add(new Room(103, "Suite", 120.0));
        rooms.add(new Room(104, "Single", 50.0));
        rooms.add(new Room(105, "Double", 75.0));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nHotel Reservation System Menu:");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Booking Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewAvailableRooms();
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    viewBookings();
                    break;
                case 4:
                    System.out.println("Thank you for using the Hotel Reservation System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }

    private static void viewAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println(room);
            }
        }
    }

    private static void makeReservation(Scanner scanner) {
        System.out.println("\nEnter your name: ");
        scanner.nextLine(); // Consume leftover newline
        String guestName = scanner.nextLine();

        System.out.println("Enter room number to reserve: ");
        int roomNumber = scanner.nextInt();

        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom != null) {
            System.out.println("Enter number of nights: ");
            int nights = scanner.nextInt();

            selectedRoom.reserve();
            Booking booking = new Booking(guestName, selectedRoom, nights);
            bookings.add(booking);

            System.out.println("Reservation successful!");
            System.out.println(booking.getDetails());
        } else {
            System.out.println("Room not available or invalid room number.");
        }
    }

    private static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("\nNo bookings found.");
        } else {
            System.out.println("\nBooking Details:");
            for (Booking booking : bookings) {
                System.out.println("-----------------------------");
                System.out.println(booking.getDetails());
            }
        }
    }
}

