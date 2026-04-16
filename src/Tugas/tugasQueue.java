package Tugas;

import java.util.LinkedList;
import java.util.Queue;

public class tugasQueue {

    public static void main(String[] args) {

        Queue<Integer> cc1 = new LinkedList<>();
        Queue<Integer> cc2 = new LinkedList<>();
        Queue<Integer> cc3 = new LinkedList<>();

        System.out.println("=== SIMULASI CALL CENTER ===\n");

        // CC1
        System.out.println("== CC1 ==");

        enqueue(cc1, 11001);
        enqueue(cc1, 11011);
        dequeue(cc1);
        enqueue(cc1, 11123);
        enqueue(cc1, 11015);
        dequeue(cc1);
        enqueue(cc1, 11331);

        System.out.println("Sisa antrean CC1: " + cc1 + "\n");

        // CC2
        System.out.println("== CC2 ==");

        enqueue(cc2, 11011);
        dequeue(cc2);
        enqueue(cc2, 11321);
        enqueue(cc2, 11250);
        enqueue(cc2, 11890);
        enqueue(cc2, 11765);
        dequeue(cc2);

        System.out.println("Sisa antrean CC2: " + cc2 + "\n");

        // CC3
        System.out.println("== CC3 ==");

        enqueue(cc3, 11543);
        enqueue(cc3, 11632);
        dequeue(cc3);
        dequeue(cc3);
        enqueue(cc3, 11387);
        enqueue(cc3, 11289);
        dequeue(cc3);

        System.out.println("Sisa antrean CC3: " + cc3 + "\n");

        System.out.println("=== SELESAI ===");
    }

    public static void enqueue(Queue<Integer> queue, int nomor) {
        queue.add(nomor);
        System.out.println("Masuk: " + nomor);
    }

    public static void dequeue(Queue<Integer> queue) {
        if (!queue.isEmpty()) {
            int dilayani = queue.poll();
            System.out.println("Layani: " + dilayani);
        } else {
            System.out.println("Antrean kosong, tidak bisa layani");
        }
    }
}