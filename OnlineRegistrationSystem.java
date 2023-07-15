import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OnlineRegistrationSystem {
    private static Map<String, String> loginCredentials = new HashMap<>();
    private static Map<String, Reservation> reservations = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    register(scanner);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (loginCredentials.containsKey(username) && loginCredentials.get(username).equals(password)) {
            System.out.println("Login successful.");
            boolean logout = false;

            while (!logout) {
                System.out.println("1. Reservation");
                System.out.println("2. Cancellation");
                System.out.println("3. Logout");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        makeReservation(scanner, username);
                        break;
                    case 2:
                        cancelReservation(scanner, username);
                        break;
                    case 3:
                        logout = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }

        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void register(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (loginCredentials.containsKey(username)) {
            System.out.println("Username already exists. Please choose a different username.");
        } else {
            loginCredentials.put(username, password);
            System.out.println("Registration successful. You can now login.");
        }
    }

    private static void makeReservation(Scanner scanner, String username) {
        System.out.print("Enter train number: ");
        String trainNumber = scanner.nextLine();
        System.out.print("Enter class type: ");
        String classType = scanner.nextLine();
        System.out.print("Enter date of journey: ");
        String journeyDate = scanner.nextLine();
        System.out.print("Enter source: ");
        String source = scanner.nextLine();
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();

        Reservation reservation = new Reservation(trainNumber, classType, journeyDate, source, destination);
        reservations.put(username, reservation);

        System.out.println("Reservation successful.");
        System.out.println(reservation);
    }

    private static void cancelReservation(Scanner scanner, String username) {
        if (reservations.containsKey(username)) {
            Reservation reservation = reservations.get(username);
            System.out.println("Your reservation details:");
            System.out.println(reservation);
            System.out.print("Confirm cancellation (yes/no): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("yes")) {
                reservations.remove(username);
                System.out.println("Reservation cancelled successfully.");
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("No reservation found for this username.");
        }
    }

    private static class Reservation {
        private String trainNumber;
        private String classType;
        private String journeyDate;
        private String source;
        private String destination;

        public Reservation(String trainNumber, String classType, String journeyDate, String source, String destination) {
            this.trainNumber = trainNumber;
            this.classType = classType;
            this.journeyDate = journeyDate;
            this.source = source;
            this.destination = destination;
        }

        @Override
        public String toString() {
            return "Train Number: " + trainNumber +
                    "\nClass Type: " + classType +
                    "\nJourney Date: " + journeyDate +
                    "\nSource: " + source +
                    "\nDestination: " + destination;
        }
    }
}