package modul_2.demo.task2;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ScheduleManager manager = new ScheduleManager();

        System.out.println("1. Menambahkan Jadwal Kereta");
        manager.addLast(new Schedule("TRN002", "Argo Bromo", "Jakarta", "Surabaya", "09:00", 500000));
        manager.addLast(new Schedule("TRN003", "Turangga", "Bandung", "Surabaya", "18:30", 450000));

        manager.addFirst(new Schedule("TRN001", "Argo Parahyangan", "Jakarta", "Bandung", "06:00", 150000));
        manager.displayAll();

        System.out.println("\n2. Mencari Rute   ");
        List<Schedule> searchResults = manager.searchRoutes("Bandung");
        for (Schedule s : searchResults) {
            s.displayInfo();
        }

        System.out.println("\n3. Menghapus Jadwal Paling Awal (Keberangkatan Terdahulu)");
        manager.removeFirst();
        manager.displayAll();

        System.out.println("\n4. Menghapus Jadwal Paling Akhir");
        manager.removeLast();
        manager.displayAll();

        System.out.println("\n5. Menambahkan beberapa jadwal untuk uji coba Cleanup");
        manager.addLast(new Schedule("TRN004", "Gajayana", "Jakarta", "Malang", "17:00", 600000));
        manager.addLast(new Schedule("TRN005", "Bima", "Jakarta", "Surabaya", "19:00", 550000));
        manager.displayAll();

        System.out.println("\n6. Cleanup Jadwal: Menghapus TRN004 menggunakan Iterator");
        boolean isCleaned = manager.cleanupSchedule("TRN004");
        boolean isCleaned2 = manager.cleanupSchedule("TRN005");
        if (isCleaned && isCleaned2) {
            System.out.println("Jadwal TRN004 berhasil dihapus.");
        } else {
            System.out.println("Jadwal TRN004 tidak ditemukan.");
        }
        manager.displayAll();
    }
}
