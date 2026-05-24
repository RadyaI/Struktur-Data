package modul_5.TASK2;

import java.util.HashMap;
import java.util.Scanner;

public class InventoryManager {

    static HashMap<String, double[]> inventory = new HashMap<>();

    static void addItem(Scanner sc) {
        System.out.println("\n--- Add New Item ---");
        System.out.print("Enter item name: ");
        String name = sc.nextLine();

        if (inventory.containsKey(name)) {
            System.out.println("Item already exists!");
            return;
        }

        System.out.print("Enter initial stock: ");
        int stock = sc.nextInt();
        System.out.print("Enter price: ");
        double price = sc.nextDouble();
        sc.nextLine();

        inventory.put(name, new double[]{stock, price});
        System.out.println("Item added successfully!");
    }

    static void viewInventory() {
        System.out.println("\n--- Inventory List ---");

        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        int i = 1;
        for (var entry : inventory.entrySet()) {
            System.out.printf("%d. %s | Stock: %.0f | Price: Rp %.0f%n",
                    i++, entry.getKey(), entry.getValue()[0], entry.getValue()[1]);
        }

        System.out.println("Total items: " + inventory.size());
    }

    static void searchItem(Scanner sc) {
        System.out.println("\n--- Search Item ---");
        System.out.print("Enter item name to search: ");
        String name = sc.nextLine();

        if (inventory.containsKey(name)) {
            double[] d = inventory.get(name);
            System.out.println("\nItem Found!");
            System.out.println("  Name  : " + name);
            System.out.printf("  Stock : %.0f%n", d[0]);
            System.out.printf("  Price : Rp %.0f%n", d[1]);
        } else {
            System.out.println("Item \"" + name + "\" not found!");
        }
    }

    static void addStock(Scanner sc) {
        System.out.println("\n--- Add Stock ---");
        System.out.print("Enter item name: ");
        String name = sc.nextLine();

        if (!inventory.containsKey(name)) {
            System.out.println("Item not found!");
            return;
        }

        System.out.print("Enter stock to add: ");
        int tambah = sc.nextInt();
        sc.nextLine();

        double oldStock = inventory.get(name)[0];
        inventory.get(name)[0] += tambah;

        System.out.println("Stock updated successfully!");
        System.out.printf("  %s: %.0f → %.0f%n", name, oldStock, inventory.get(name)[0]);
    }

    static void removeItem(Scanner sc) {
        System.out.println("\n--- Remove Item ---");
        System.out.print("Enter item name to remove: ");
        String name = sc.nextLine();

        if (!inventory.containsKey(name)) {
            System.out.println("Item not found!");
            return;
        }

        double[] d = inventory.remove(name);
        System.out.printf("Item \"%s\" removed! (Stock: %.0f, Price: Rp %.0f)%n",
                name, d[0], d[1]);
    }

    static void updatePrice(Scanner sc) {
        System.out.println("\n--- Update Price ---");
        System.out.print("Enter item name: ");
        String name = sc.nextLine();

        if (!inventory.containsKey(name)) {
            System.out.println("Item not found!");
            return;
        }

        double oldPrice = inventory.get(name)[1];
        System.out.printf("  Current price: Rp %.0f%n", oldPrice);
        System.out.print("Enter new price: ");
        double newPrice = sc.nextDouble();
        sc.nextLine();

        inventory.get(name)[1] = newPrice;
        System.out.printf("Price updated! %s: Rp %.0f → Rp %.0f%n", name, oldPrice, newPrice);
    }

    static void viewReport(String loggedInUser, AuthManager auth) {
        System.out.println("\n--- Inventory Report ---");

        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            for (var entry : inventory.entrySet()) {
                System.out.printf("- %s: Stock = %.0f, Price = %.1f%n",
                        entry.getKey(), entry.getValue()[0], entry.getValue()[1]);
            }
        }

        System.out.println("\nLogged-in User: " + auth.getFullName(loggedInUser));
    }
}