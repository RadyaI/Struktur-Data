package modul_3.demo.task2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Railway Ticket Service ===");
            System.out.println("1. Add Passenger");
            System.out.println("2. Display Queue");
            System.out.println("3. Serve Passenger");
            System.out.println("4. Undo Last Transaction");
            System.out.print("Choose menu: ");

            String input = scanner.nextLine();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Pilihan tidak valid.");
                continue;
            }

            if (choice == 1) {
                System.out.print("Enter passenger name: ");
                String name = scanner.nextLine();
                queue.add(name);
                System.out.println("Passenger added to queue.");
            } else if (choice == 2) {
                System.out.println("Current Queue:");
                if (queue.isEmpty()) {
                    System.out.println("Queue is empty.");
                } else {
                    int i = 1;
                    for (String passenger : queue) {
                        System.out.println(i + ". " + passenger);
                        i++;
                    }
                }
            } else if (choice == 3) {
                if (!queue.isEmpty()) {
                    String servedPassenger = queue.poll();
                    stack.push(servedPassenger);
                    System.out.println("Serving passenger: " + servedPassenger);
                    System.out.println("Transaction saved.");
                } else {
                    System.out.println("No passengers in queue to serve.");
                }
            } else if (choice == 4) {
                if (!stack.isEmpty()) {
                    String lastTransaction = stack.pop();
                    System.out.println("Undo transaction for passenger: " + lastTransaction);
                    queue.add(lastTransaction);
                } else {
                    System.out.println("No transactions to undo.");
                }
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
    }
}