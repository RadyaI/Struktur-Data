package modul_1.codelab;

public class GenericTicket<T> {
    private T bookingCode;
    private String passengerName;

    public GenericTicket(T bookingCode, String passegerName) {
        this.bookingCode = bookingCode;
        this.passengerName = passegerName;
    }

    public T getBookingCode() {
        return bookingCode;
    }

    public String getPassegerName() {
        return passengerName;
    }

    public void displayTicket() {
        System.out.println("-- Railway Ticket Information --");
        System.out.println("Booking Code      : " + bookingCode);
        System.out.println("Passenger Name    : " + passengerName);
        System.out.println("Booking Code Type : " + bookingCode.getClass().getSimpleName());
    }
}


class Main {
    public static void main(String[] args) {
        GenericTicket<String> ticket1 =
                new GenericTicket<>("KA-001", "Radya");
        ticket1.displayTicket();

        System.out.println();

        GenericTicket<Integer> ticket2 =
                new GenericTicket<>(1002,"Muhammad");
        ticket2.displayTicket();
    }
}