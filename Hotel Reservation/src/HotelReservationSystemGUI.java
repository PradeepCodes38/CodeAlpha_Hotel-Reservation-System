import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HotelReservationSystemGUI {
    private ArrayList<Room> rooms;
    private ArrayList<Booking> bookings;

    public HotelReservationSystemGUI() {
        rooms = new ArrayList<>();
        bookings = new ArrayList<>();
        initializeRooms();
        setupGUI();
    }

    private void initializeRooms() {
        rooms.add(new Room(101, "Single", 50.0));
        rooms.add(new Room(102, "Double", 75.0));
        rooms.add(new Room(103, "Suite", 120.0));
        rooms.add(new Room(104, "Single", 50.0));
        rooms.add(new Room(105, "Double", 75.0));
    }

    private void setupGUI() {
        JFrame frame = new JFrame("Hotel Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));

        JButton viewRoomsButton = new JButton("View Available Rooms");
        JButton makeReservationButton = new JButton("Make a Reservation");
        JButton viewBookingsButton = new JButton("View Bookings");
        JButton exitButton = new JButton("Exit");

        viewRoomsButton.addActionListener(e -> viewRooms());
        makeReservationButton.addActionListener(e -> makeReservation());
        viewBookingsButton.addActionListener(e -> viewBookings());
        exitButton.addActionListener(e -> System.exit(0));

        panel.add(viewRoomsButton);
        panel.add(makeReservationButton);
        panel.add(viewBookingsButton);
        panel.add(exitButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void viewRooms() {
        StringBuilder message = new StringBuilder("Available Rooms:\n");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                message.append(room).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, message.toString());
    }

    private void makeReservation() {
        String guestName = JOptionPane.showInputDialog("Enter guest name:");
        if (guestName == null || guestName.isEmpty()) {
            return;
        }

        String roomNumberInput = JOptionPane.showInputDialog("Enter room number to reserve:");
        if (roomNumberInput == null || roomNumberInput.isEmpty()) {
            return;
        }

        int roomNumber;
        try {
            roomNumber = Integer.parseInt(roomNumberInput);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid room number.");
            return;
        }

        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            JOptionPane.showMessageDialog(null, "Room not available or invalid room number.");
            return;
        }

        String nightsInput = JOptionPane.showInputDialog("Enter number of nights:");
        if (nightsInput == null || nightsInput.isEmpty()) {
            return;
        }

        int numberOfNights;
        try {
            numberOfNights = Integer.parseInt(nightsInput);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number of nights.");
            return;
        }

        selectedRoom.reserve();
        Booking booking = new Booking(guestName, selectedRoom, numberOfNights);
        bookings.add(booking);

        JOptionPane.showMessageDialog(null, "Reservation successful!\n" + booking.getDetails());
    }

    private void viewBookings() {
        if (bookings.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No bookings found.");
            return;
        }

        StringBuilder message = new StringBuilder("Bookings:\n");
        for (Booking booking : bookings) {
            message.append(booking.getDetails()).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, message.toString());
    }

    public static void main(String[] args) {
        new HotelReservationSystemGUI();
    }
}
