package modul_1.demo;

import java.util.Scanner;

enum TicketClass {
    ECONOMY, BUSINESS, EXECUTIVE
}

class Passenger<T> {
    private String name;
    private T identityNumber;
    private String bookingCode;
    private TicketClass ticketClass;

    public Passenger(String name, T identityNumber, String bookingCode, TicketClass ticketClass) {
        this.name = name;
        this.identityNumber = identityNumber;
        this.bookingCode = bookingCode;
        this.ticketClass = ticketClass;
    }

    public String getName() { return name; }
    public T getIdentityNumber() { return identityNumber; }
    public String getBookingCode() { return bookingCode; }
    public TicketClass getTicketClass() { return ticketClass; }
}

public class task {
    public static void printTicketInfo(Passenger<?> passenger) {
        System.out.println("\n== Ticket Information ==");
        System.out.printf("%-16s : %s%n", "Booking Code", passenger.getBookingCode());
        System.out.printf("%-16s : %s%n", "Passenger Name", passenger.getName());
        System.out.printf("%-16s : %s%n", "Identity Type", passenger.getIdentityNumber().getClass().getSimpleName());
        System.out.printf("%-16s : %s%n", "Identity Number", passenger.getIdentityNumber());
        System.out.printf("%-16s : %s%n", "Ticket Class", passenger.getTicketClass());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("== Railway Ticket Booking ==");
        System.out.print("Enter Passenger Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Identity Number: ");
        String identityInput = scanner.nextLine();

        System.out.print("Enter Booking Code: ");
        String bookingCode = scanner.nextLine();

        System.out.println("\nSelect Ticket Class:");
        System.out.println("1. ECONOMY");
        System.out.println("2. BUSINESS");
        System.out.println("3. EXECUTIVE");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();

        TicketClass ticketClass;
        switch (choice) {
            case 2: ticketClass = TicketClass.BUSINESS; break;
            case 3: ticketClass = TicketClass.EXECUTIVE; break;
            default: ticketClass = TicketClass.ECONOMY; break;
        }

        try {
            Integer identityInt = Integer.parseInt(identityInput);
            Passenger<Integer> passenger = new Passenger<>(name, identityInt, bookingCode, ticketClass);
            printTicketInfo(passenger);
        } catch (NumberFormatException e) {
            Passenger<String> passenger = new Passenger<>(name, identityInput, bookingCode, ticketClass);
            printTicketInfo(passenger);
        }

        scanner.close();
    }
}