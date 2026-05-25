package modul_5.TASK1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static HashMap<String, InventoryItem> inventory = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;

        do {
            tampilkanMenu();
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1 -> tambahItem();
                case 2 -> lihatInventory();
                case 3 -> cariItem();
                case 4 -> tambahStok();
                case 5 -> hapusItem();
                case 6 -> updateHarga();
                case 0 -> System.out.println("Keluar dari program. Sampai jumpa!");
                default -> System.out.println("Menu tidak valid, coba lagi.");
            }

        } while (pilihan != 0);
    }

    static void tampilkanMenu() {
        System.out.println("\n========================================");
        System.out.println("   Railway Inventory Management System  ");
        System.out.println("========================================");
        System.out.println("1. Tambah Item Inventori Baru");
        System.out.println("2. Lihat Daftar Inventori");
        System.out.println("3. Cari Item Inventori");
        System.out.println("4. Tambah Stok Item");
        System.out.println("5. Hapus Item Inventori");
        System.out.println("6. Update Harga Item");
        System.out.println("0. Keluar");
        System.out.println("========================================");
    }

    static void tambahItem() {
        System.out.print("Nama item: ");
        String nama = scanner.nextLine();

        if (inventory.containsKey(nama)) {
            System.out.println("Item \"" + nama + "\" sudah ada di inventori!");
            return;
        }

        System.out.print("Stok awal: ");
        int stok = scanner.nextInt();

        System.out.print("Harga: ");
        double harga = scanner.nextDouble();
        scanner.nextLine();

        inventory.put(nama, new InventoryItem(stok, harga));
        System.out.println("Item \"" + nama + "\" berhasil ditambahkan!");
    }

    static void lihatInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventori masih kosong.");
            return;
        }

        System.out.println("\n--- Daftar Inventori ---");
        for (Map.Entry<String, InventoryItem> entry : inventory.entrySet()) {
            System.out.print("Item  : " + entry.getKey());
            System.out.println(" " + entry.getValue());
            System.out.println("------------------------");
        }
    }

    static void cariItem() {
        System.out.print("Nama item yang dicari: ");
        String nama = scanner.nextLine();

        if (inventory.containsKey(nama)) {
            System.out.println("Item ditemukan!");
            System.out.print("Nama  : " + nama);
            System.out.println(" " + inventory.get(nama));
        } else {
            System.out.println("Item \"" + nama + "\" tidak ditemukan di inventori.");
        }
    }

    static void tambahStok() {
        System.out.print("Nama item yang ingin ditambah stoknya: ");
        String nama = scanner.nextLine();

        if (!inventory.containsKey(nama)) {
            System.out.println("Item \"" + nama + "\" tidak ditemukan.");
            return;
        }

        System.out.print("Jumlah stok yang ditambahkan: ");
        int tambahan = scanner.nextInt();
        scanner.nextLine();

        InventoryItem item = inventory.get(nama);
        int stokBaru = item.getStock() + tambahan;
        item.setStock(stokBaru);

        System.out.println("Stok \"" + nama + "\" berhasil diperbarui menjadi " + stokBaru + ".");
    }

    static void hapusItem() {
        System.out.print("Nama item yang ingin dihapus: ");
        String nama = scanner.nextLine();

        if (!inventory.containsKey(nama)) {
            System.out.println("Item \"" + nama + "\" tidak ditemukan.");
            return;
        }

        inventory.remove(nama);
        System.out.println("Item \"" + nama + "\" berhasil dihapus dari inventori.");
    }

    static void updateHarga() {
        System.out.print("Nama item yang ingin diupdate harganya: ");
        String nama = scanner.nextLine();

        if (!inventory.containsKey(nama)) {
            System.out.println("Item \"" + nama + "\" tidak ditemukan.");
            return;
        }

        System.out.print("Harga baru: ");
        double hargaBaru = scanner.nextDouble();
        scanner.nextLine();

        inventory.get(nama).setPrice(hargaBaru);
        System.out.println("Harga \"" + nama + "\" berhasil diupdate menjadi Rp" +
                String.format("%,.0f", hargaBaru));
    }
}