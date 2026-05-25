package modul_5.TASK2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AuthManager auth = new AuthManager();
        int choice;

        do {
            if (!auth.isLoggedIn()) {
                System.out.println("\n==============================");
                System.out.println("  Welcome to Railway System   ");
                System.out.println("==============================");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("99. Exit");
                System.out.print("Choose menu: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1 -> auth.register(sc);
                    case 2 -> auth.login(sc);
                    case 99 -> System.out.println("Goodbye!");
                    default -> System.out.println("Invalid choice!");
                }

            } else {
                System.out.println("\n==============================");
                System.out.println("  Railway Inventory System    ");
                System.out.println("  Logged in as: " + auth.getLoggedInUser());
                System.out.println("==============================");
                System.out.println("1. Add New Item");
                System.out.println("2. View Inventory List");
                System.out.println("3. Search Item");
                System.out.println("4. Add Item Stock");
                System.out.println("5. Remove Item");
                System.out.println("6. Update Item Price");
                System.out.println("7. View Inventory Report");
                System.out.println("8. Logout");
                System.out.println("9. Exit");
                System.out.print("Choose menu: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1 -> InventoryManager.addItem(sc);
                    case 2 -> InventoryManager.viewInventory();
                    case 3 -> InventoryManager.searchItem(sc);
                    case 4 -> InventoryManager.addStock(sc);
                    case 5 -> InventoryManager.removeItem(sc);
                    case 6 -> InventoryManager.updatePrice(sc);
                    case 7 -> InventoryManager.viewReport(auth.getLoggedInUser(), auth);
                    case 8 -> {
                        auth.logout();
                        choice = 0;
                    }
                    case 9 -> {
                        System.out.println("Goodbye!");
                        choice = 99;
                    }
                    default -> System.out.println("Invalid choice!");
                }
            }

        } while (choice != 99);
    }
}