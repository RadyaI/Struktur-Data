package modul_6.demo;

/**
 * CLASS MAIN
 * ----------
 * Entry point program. Di sini kita:
 * 1. Build graph dengan data stasiun kereta Indonesia (hardcoded)
 * 2. Demonstrasikan semua operasi CRUD graph
 * 3. Jalankan BFS dan DFS traversal
 * 4. Cari jalur minimum dengan BFS
 *
 * Jaringan yang dipakai: 8 Stasiun utama Jawa (simulasi KA antar kota)
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║     RAILWAY GRAPH - JAVA DEMO          ║");
        System.out.println("║     Struktur Data - Adjacency List     ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        // =====================================================
        // TASK 1: BUILD THE RAILWAY GRAPH
        // =====================================================

        RailwayGraph graph = new RailwayGraph();

        // --- Tambah Stasiun (Vertices) ---
        System.out.println(">>> MENAMBAHKAN STASIUN...");
        graph.addStation(new Station("JKT", "Stasiun Gambir", "Jakarta"));
        graph.addStation(new Station("BDG", "Stasiun Bandung", "Bandung"));
        graph.addStation(new Station("CRB", "Stasiun Cirebon", "Cirebon"));
        graph.addStation(new Station("PWK", "Stasiun Purwokerto", "Purwokerto"));
        graph.addStation(new Station("YOG", "Stasiun Tugu", "Yogyakarta"));
        graph.addStation(new Station("SLO", "Stasiun Solo Balapan", "Solo"));
        graph.addStation(new Station("SBY", "Stasiun Gubeng", "Surabaya"));
        graph.addStation(new Station("MLG", "Stasiun Malang", "Malang"));

        // --- Tambah Rute (Weighted Edges) ---
        // Format: addRoute(dari, ke, jarak_km, waktu_menit, tarif_rupiah)
        System.out.println("\n>>> MENAMBAHKAN RUTE...");
        graph.addRoute("JKT", "BDG",  150,  180, 75000);   // Jakarta - Bandung
        graph.addRoute("JKT", "CRB",  240,  210, 100000);  // Jakarta - Cirebon
        graph.addRoute("BDG", "PWK",  175,  200, 80000);   // Bandung - Purwokerto
        graph.addRoute("CRB", "PWK",   85,   90, 45000);   // Cirebon - Purwokerto
        graph.addRoute("CRB", "YOG",  280,  240, 120000);  // Cirebon - Yogyakarta
        graph.addRoute("PWK", "YOG",  115,  130, 60000);   // Purwokerto - Yogyakarta
        graph.addRoute("YOG", "SLO",   65,   65, 35000);   // Yogyakarta - Solo
        graph.addRoute("SLO", "SBY",  260,  220, 110000);  // Solo - Surabaya
        graph.addRoute("SBY", "MLG",   90,   95, 50000);   // Surabaya - Malang

        // Print full graph
        graph.printGraph();

        // --- Demo: Get Connections ---
        System.out.println(">>> KONEKSI DARI JKT:");
        for (Route r : graph.getConnections("JKT")) {
            System.out.println("   " + r);
        }

        // --- Demo: Remove Route ---
        System.out.println("\n>>> HAPUS RUTE BDG <-> PWK:");
        graph.removeRoute("BDG", "PWK");

        // =====================================================
        // TASK 2: GRAPH TRAVERSAL & PATHFINDING
        // =====================================================

        GraphTraversal traversal = new GraphTraversal(graph);

        // BFS Traversal dari Jakarta
        traversal.bfsTraversal("JKT");

        // DFS Traversal (Rekursif) dari Jakarta
        traversal.dfsTraversal("JKT");

        // BFS Pathfinding: cari jalur dengan stop minimum
        traversal.findPathBFS("JKT", "SBY");   // Jakarta -> Surabaya
        traversal.findPathBFS("JKT", "MLG");   // Jakarta -> Malang
        traversal.findPathBFS("BDG", "MLG");   // Bandung -> Malang

        System.out.println(
                "  [JKT]──────────[CRB]\n" +
                        "    |              |  \\\n" +
                        "  [BDG]──────[PWK]─────[YOG]────[SLO]────[SBY]\n" +
                        "                                              |\n" +
                        "                                           [MLG]"
        );

        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║         PROGRAM SELESAI ✓              ║");
        System.out.println("╚════════════════════════════════════════╝");
    }
}