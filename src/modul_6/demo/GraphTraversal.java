package modul_6.demo;

import java.util.*;

/**
 * CLASS GRAPHTRAVERSAL
 * ---------------------
 * Berisi implementasi dua algoritma traversal graph klasik:
 * - BFS (Breadth-First Search): pakai Queue (FIFO)
 * - DFS (Depth-First Search): pakai Stack (LIFO) / Rekursi
 *
 * Kelas ini mengambil referensi RailwayGraph yang sudah ada
 * dan bekerja di atasnya.
 */
public class GraphTraversal {

    private RailwayGraph graph;

    public GraphTraversal(RailwayGraph graph) {
        this.graph = graph;
    }

    // =========================================================
    // BFS - BREADTH FIRST SEARCH
    // =========================================================
    /**
     * CARA KERJA BFS:
     * Ibaratnya kamu lagi nyebar brosur dari satu titik.
     * Kamu kasih brosur ke semua tetangga terdekat dulu (level 1),
     * baru mereka kasih ke tetangga mereka (level 2), dst.
     *
     * Struktur data: QUEUE (antrian, FIFO = First In First Out)
     * -> Yang pertama masuk, pertama diproses
     *
     * Kompleksitas: O(V + E) - V=vertex, E=edge
     */
    public void bfsTraversal(String startCode) {
        // Validasi stasiun awal
        if (!graph.getAdjList().containsKey(startCode)) {
            System.out.println("✖ Stasiun " + startCode + " tidak ditemukan!");
            return;
        }

        System.out.println("\n===== BFS TRAVERSAL dari " + startCode + " =====");

        // Set untuk nyimpen stasiun yang sudah dikunjungi
        // Ini WAJIB buat cegah infinite loop di graph yang ada cycle
        Set<String> visited = new HashSet<>();

        // Queue adalah jantungnya BFS
        Queue<String> queue = new LinkedList<>();

        // Langkah awal: masukkan stasiun pertama ke queue dan tandai visited
        queue.offer(startCode);
        visited.add(startCode);

        // Selama queue tidak kosong, terus proses
        while (!queue.isEmpty()) {
            // Ambil elemen paling depan (FIFO)
            String current = queue.poll();
            Station station = graph.getStations().get(current);
            System.out.println("  Mengunjungi: " + station);

            // Lihat semua tetangga dari stasiun saat ini
            for (Route route : graph.getConnections(current)) {
                String neighbor = route.getDestination();

                // Kalau tetangga belum dikunjungi, masukkan ke queue
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);      // tandai dulu biar ga double
                    queue.offer(neighbor);      // masukkan ke antrian
                }
            }
        }

        System.out.println("===========================================\n");
    }

    // =========================================================
    // BFS - FIND PATH (Minimum Stops)
    // =========================================================
    /**
     * BFS juga bisa dipakai buat cari jalur dengan JUMLAH PEMBERHENTIAN MINIMUM.
     * Kenapa BFS? Karena BFS eksplorasi level by level, jadi jalur yang
     * pertama ditemukan PASTI yang paling pendek (dalam konteks jumlah edge/stop).
     *
     * Ini pakai teknik "parent tracking": setiap node nyimpen "dari mana dia datang"
     * supaya bisa reconstruct jalurnya di akhir.
     */
    public void findPathBFS(String startCode, String endCode) {
        if (!graph.getAdjList().containsKey(startCode) ||
                !graph.getAdjList().containsKey(endCode)) {
            System.out.println("✖ Stasiun tidak ditemukan!");
            return;
        }

        System.out.println("\n===== BFS PATH: " + startCode + " -> " + endCode + " =====");

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        // Map untuk nyimpen: node ini datang dari mana?
        // KEY: stasiun saat ini, VALUE: stasiun yang mengantarnya ke sini
        Map<String, String> parent = new HashMap<>();

        queue.offer(startCode);
        visited.add(startCode);
        parent.put(startCode, null); // stasiun awal tidak punya parent

        boolean found = false;

        while (!queue.isEmpty()) {
            String current = queue.poll();

            // Kalau sudah sampai tujuan, stop!
            if (current.equals(endCode)) {
                found = true;
                break;
            }

            for (Route route : graph.getConnections(current)) {
                String neighbor = route.getDestination();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, current); // catat: neighbor datang dari current
                    queue.offer(neighbor);
                }
            }
        }

        if (!found) {
            System.out.println("Tidak ada jalur dari " + startCode + " ke " + endCode);
            return;
        }

        // Rekonstruksi jalur dengan cara jalan mundur dari tujuan ke awal
        List<String> path = new ArrayList<>();
        String step = endCode;
        while (step != null) {
            path.add(step);
            step = parent.get(step); // mundur ke parent
        }

        // Balik urutannya (tadi dari belakang ke depan)
        Collections.reverse(path);

        System.out.print("Jalur terpendek (" + (path.size() - 1) + " stop): ");
        System.out.println(String.join(" -> ", path));

        // Hitung total jarak, waktu, dan fare sepanjang jalur ini
        int totalDist = 0, totalTime = 0;
        double totalFare = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            String from = path.get(i);
            String to   = path.get(i + 1);
            for (Route r : graph.getConnections(from)) {
                if (r.getDestination().equals(to)) {
                    totalDist += r.getDistance();
                    totalTime += r.getTravelTime();
                    totalFare += r.getFare();
                    break;
                }
            }
        }
        System.out.printf("Total: %d km | %d menit | Rp%.0f%n", totalDist, totalTime, totalFare);
        System.out.println("===========================================\n");
    }

    // =========================================================
    // DFS - DEPTH FIRST SEARCH (Rekursif)
    // =========================================================
    /**
     * CARA KERJA DFS:
     * Ibaratnya kamu lagi jelajah labirin. Kamu pilih satu lorong,
     * masuk sedalam-dalamnya sampai mentok (dead end), baru balik
     * (backtrack) dan coba lorong lain.
     *
     * Implementasi: REKURSIF (call stack bertindak sebagai Stack)
     * Bisa juga pakai Stack eksplisit, tapi rekursif lebih elegan.
     *
     * Kegunaan: deteksi cycle, cek konektivitas, topological sort
     */
    public void dfsTraversal(String startCode) {
        if (!graph.getAdjList().containsKey(startCode)) {
            System.out.println("✖ Stasiun " + startCode + " tidak ditemukan!");
            return;
        }

        System.out.println("\n===== DFS TRAVERSAL dari " + startCode + " =====");
        Set<String> visited = new HashSet<>();
        dfsHelper(startCode, visited, 0); // depth=0 untuk indentasi visual
        System.out.println("==========================================\n");
    }

    /**
     * Helper rekursif untuk DFS.
     * Setiap kali method ini dipanggil, kita "menukik" satu level lebih dalam.
     *
     * @param current  stasiun yang sedang dikunjungi sekarang
     * @param visited  set stasiun yang sudah dikunjungi
     * @param depth    kedalaman sekarang (untuk indentasi visual)
     */
    private void dfsHelper(String current, Set<String> visited, int depth) {
        // Tandai sebagai visited
        visited.add(current);

        // Buat indentasi berdasarkan kedalaman (biar keliatan strukturnya)
        String indent = "  ".repeat(depth);
        Station station = graph.getStations().get(current);
        System.out.println(indent + (depth == 0 ? "★ " : "└─ ") + station);

        // Kunjungi semua tetangga yang belum dikunjungi
        for (Route route : graph.getConnections(current)) {
            String neighbor = route.getDestination();
            if (!visited.contains(neighbor)) {
                // Rekursif! Masuk lebih dalam ke tetangga ini
                dfsHelper(neighbor, visited, depth + 1);
                // Kalau dari rekursi balik ke sini = BACKTRACK
            }
        }
    }
}