package modul_2.demo.task1;

public class Main {
    public static void main(String[] args) {
        PassengerManager manager = new PassengerManager();

        System.out.println("1. Menambahkan Penumpang Baru");
        manager.addPassenger(new Passenger("Muhammad Radya", "Radya@email.com", "08123456789", 25));
        manager.addPassenger(new Passenger("Muhammad Iftikhar", "Iftikhar@email.com", "08987654321", 22));
        manager.addPassenger(new Passenger("Fulan", "Fulan@email.com", "08561234987", 28));
        manager.displayAll();

        System.out.println("\n2. Mengambil Penumpang di Index 1");
        Passenger p1 = manager.getPassenger(1);
        if (p1 != null) {
            p1.displayInfo();
        }

        System.out.println("\n4. Mengupdate Penumpang di Index 0");
        manager.updatePassenger(0, "Ini Radya", "radyaa@email.com", "08123456789", 26);
        manager.updatePassenger(1, "kucing", "meng@gmail.com", "098765", 1);
        manager.displayAll();

        System.out.println("\n3. Mencari Penumpang dengan Nama 'Muhammad Radya'");
        Passenger searchResult = manager.searchPassengerByName("kucing");
        if (searchResult != null) {
            System.out.println("Ditemukan:");
            searchResult.displayInfo();
        }

        System.out.println("\n5. Menghapus Penumpang by Index");
        manager.removeByIndex(1);
        manager.displayAll();

        System.out.println("\n6. Menghapus Penumpang by ID");
        manager.removeById(3);
        manager.displayAll();
    }
}