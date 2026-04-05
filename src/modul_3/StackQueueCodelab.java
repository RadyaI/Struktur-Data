package modul_3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackQueueCodelab {
    public static void main(String[] args) {


        // TODO 1
        Queue<String> serviceQueue = new LinkedList<>();

        // TODO 2
        serviceQueue.add("Muhammad");
        serviceQueue.add("Radya");
        serviceQueue.add("Iftikhar");

        System.out.println("Initial Service Queue:");
        System.out.println(serviceQueue);

        // TODO 3
        String servedPassenger = serviceQueue.poll();
        System.out.println("Serving passenger: " + servedPassenger);

        System.out.println("Queue after serving:");
        System.out.println(serviceQueue);

        // TODO 4
        String nextPassenger = serviceQueue.peek();
        System.out.println("Next passenger to serve: " + nextPassenger);

        System.out.println();

        // TODO 5
        Stack<String> transactionHistory = new Stack<>();

        // TODO 6
        transactionHistory.push("Transaction-1");
        transactionHistory.push("Transaction-2");
        transactionHistory.push("Transaction-3");

        System.out.println("Transaction History:");
        System.out.println(transactionHistory);

        // TODO 7
        String lastTransaction = transactionHistory.pop();
        System.out.println("Undo last transaction: " + lastTransaction);

        System.out.println("Transaction History after undo:");
        System.out.println(transactionHistory);

        // TODO 8
        String topTransaction = transactionHistory.peek();
        System.out.println("Current top transaction: " + topTransaction);

        // TODO 9
        System.out.println();
        if (transactionHistory.isEmpty()) {
            System.out.println("No transaction history.");
        } else {
            System.out.println("Transaction history is not empty. Size: " + transactionHistory.size());
        }
    }
}