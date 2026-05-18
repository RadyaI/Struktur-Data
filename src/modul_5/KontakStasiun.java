package modul_5;

import java.util.HashMap;
import java.util.Map;

public class KontakStasiun {
    public static void main(String[] args) {

        HashMap<String, String> kontakStasiun = new HashMap<>();

        // Tambah
        kontakStasiun.put("MUHAMMAD - Kepala Stasiun", "081234567890");
        kontakStasiun.put("RADYA - Customer Service", "081298765432");
        kontakStasiun.put("IFTIKHAR - Keamanan", "081255555555");

        // Tampilkan
        System.out.println("=== Daftar Kontak Stasiun ===");

        for (Map.Entry<String, String> entry : kontakStasiun.entrySet()) {
            System.out.println(
                    "Nama: " + entry.getKey() +
                            " | Telepon: " + entry.getValue()
            );
        }

        // Cari
        String namaDicari = "MUHAMMAD - Kepala Stasiun";
        String nomorTelepon = kontakStasiun.get(namaDicari);
        if (nomorTelepon != null) {
            System.out.println(
                    "\nNomor telepon " + namaDicari + ": " + nomorTelepon
            );
        } else {
            System.out.println(
                    "\nKontak " + namaDicari + " tidak ditemukan."
            );
        }

        // Cek
        String namaPengecekan = "Keamanan";
        if (kontakStasiun.containsKey(namaPengecekan)) {
            System.out.println(
                    "Kontak " + namaPengecekan + " tersedia di sistem."
            );
        } else {
            System.out.println(
                    "Kontak " + namaPengecekan + " tidak tersedia."
            );
        }

        // Hapus
        String namaHapus = "IFTIKHAR - Keamanan";
        kontakStasiun.remove(namaHapus);
        System.out.println(
                "\nIsi kontak setelah menghapus " + namaHapus + ":"
        );
        System.out.println(kontakStasiun);
    }
}