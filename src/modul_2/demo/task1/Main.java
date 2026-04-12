package modul_2.demo.task1;

public class Main {
    public static void main(String[] args) {
        PassengerManager manager = new PassengerManager();

        System.out.println("1. Menambahkan Penumpang Baru");
        manager.addPassenger(new Passenger("Budi Santoso", "budi@email.com", "08123456789", 25));
        manager.addPassenger(new Passenger("Siti Aminah", "siti@email.com", "08987654321", 22));
        manager.addPassenger(new Passenger("Andi Darmawan", "andi@email.com", "08561234987", 28));
        manager.displayAll();

        System.out.println("\n2. Mengambil Penumpang di Index 1");
        Passenger p1 = manager.getPassenger(1);
        if (p1 != null) {
            p1.displayInfo();
        }

        System.out.println("\n3. Mencari Penumpang dengan Nama 'Andi Darmawan'");
        Passenger searchResult = manager.searchPassengerByName("Andi Darmawan");
        if (searchResult != null) {
            System.out.println("Ditemukan:");
            searchResult.displayInfo();
        }

        System.out.println("\n4. Mengupdate Penumpang di Index 0");
        manager.updatePassenger(0, "Budi S. (Updated)", "budis@email.com", "08123456789", 26);
        manager.displayAll();

        System.out.println("\n5. Menghapus Penumpang by Index (Index 1 - Siti)");
        manager.removeByIndex(1);
        manager.displayAll();

        System.out.println("\n6. Menghapus Penumpang by ID (ID 3 - Andi)");
        manager.removeById(3);
        manager.displayAll();
    }
}