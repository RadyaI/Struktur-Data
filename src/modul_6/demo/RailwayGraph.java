package modul_6.demo;

import java.util.*;

/**
 * CLASS RAILWAYGRAPH (GRAPH - ADJACENCY LIST)
 * --------------------------------------------
 * Ini inti dari semuanya. Graph diimplementasi pakai ADJACENCY LIST
 * menggunakan dua HashMap:
 *
 * 1. stations  -> HashMap<String, Station>
 *    KEY: kode stasiun (ex: "JKT")
 *    VALUE: objek Station-nya
 *    Fungsi: nyimpen semua vertex/node
 *
 * 2. adjList   -> HashMap<String, ArrayList<Route>>
 *    KEY: kode stasiun ASAL (ex: "JKT")
 *    VALUE: list semua Route yang keluar dari stasiun itu
 *    Fungsi: ini ADJACENCY LIST-nya! Nyimpen semua edge
 *
 * Kenapa Adjacency List lebih efisien dari Adjacency Matrix?
 * -> Adjacency Matrix: selalu O(V²) space
 * -> Adjacency List: O(V + E) space — hemat buat graph yang jarang terhubung
 *
 * Graph ini UNDIRECTED: kalau JKT-SBY ada, maka SBY-JKT juga ada secara otomatis.
 */
public class RailwayGraph {

    // Menyimpan semua vertex (station) berdasarkan kodenya
    private HashMap<String, Station> stations;

    // Adjacency List: untuk setiap stasiun, simpan list route yang keluar
    private HashMap<String, ArrayList<Route>> adjList;

    // Constructor: inisialisasi kedua HashMap kosong
    public RailwayGraph() {
        this.stations  = new HashMap<>();
        this.adjList   = new HashMap<>();
    }

    // =========================================================
    // OPERASI 1: ADD STATION (tambah vertex)
    // =========================================================
    /**
     * Menambahkan stasiun baru ke graph.
     * Juga langsung inisialisasi list kosong di adjList untuk stasiun ini.
     */
    public void addStation(Station station) {
        String code = station.getCode();

        // Cegah duplikasi
        if (stations.containsKey(code)) {
            System.out.println("⚠ Stasiun " + code + " sudah ada!");
            return;
        }

        stations.put(code, station);                    // daftarkan vertex
        adjList.put(code, new ArrayList<>());           // siapkan list edge-nya (kosong dulu)
        System.out.println("✔ Stasiun ditambahkan: " + station);
    }

    // =========================================================
    // OPERASI 2: ADD ROUTE (tambah edge)
    // =========================================================
    /**
     * Menambahkan jalur (edge) antara dua stasiun.
     * Karena UNDIRECTED, kita tambahkan dua arah: A->B dan B->A.
     */
    public void addRoute(String fromCode, String toCode,
                         int distance, int travelTime, double fare) {

        // Validasi: pastikan kedua stasiun ada
        if (!stations.containsKey(fromCode) || !stations.containsKey(toCode)) {
            System.out.println("✖ Salah satu stasiun tidak ditemukan!");
            return;
        }

        // Tambah edge A -> B
        adjList.get(fromCode).add(new Route(toCode, distance, travelTime, fare));

        // Tambah edge B -> A (undirected graph!)
        adjList.get(toCode).add(new Route(fromCode, distance, travelTime, fare));

        System.out.println("✔ Rute ditambahkan: " + fromCode + " <-> " + toCode);
    }

    // =========================================================
    // OPERASI 3: GET CONNECTIONS (ambil semua tetangga)
    // =========================================================
    /**
     * Mengambil semua route yang keluar dari suatu stasiun.
     * Ini inti dari Adjacency List: langsung O(1) lookup pakai HashMap.
     */
    public ArrayList<Route> getConnections(String stationCode) {
        if (!adjList.containsKey(stationCode)) {
            System.out.println("✖ Stasiun " + stationCode + " tidak ditemukan!");
            return new ArrayList<>();
        }
        return adjList.get(stationCode);
    }

    // =========================================================
    // OPERASI 4A: REMOVE STATION (hapus vertex + semua edge-nya)
    // =========================================================
    /**
     * Menghapus stasiun beserta SEMUA rute yang terhubung dengannya.
     * Ini lebih kompleks: harus bersihkan referensi dari stasiun lain juga.
     */
    public void removeStation(String code) {
        if (!stations.containsKey(code)) {
            System.out.println("✖ Stasiun " + code + " tidak ditemukan!");
            return;
        }

        // Hapus semua rute dari stasiun LAIN yang menuju ke stasiun ini
        // Iterasi setiap entry di adjacency list
        for (Map.Entry<String, ArrayList<Route>> entry : adjList.entrySet()) {
            // removeIf: hapus route yang destinasinya = stasiun yang mau dihapus
            entry.getValue().removeIf(route -> route.getDestination().equals(code));
        }

        // Hapus entry-nya sendiri dari kedua HashMap
        adjList.remove(code);
        stations.remove(code);

        System.out.println("✔ Stasiun " + code + " dan semua rutenya dihapus.");
    }

    // =========================================================
    // OPERASI 4B: REMOVE ROUTE (hapus edge spesifik)
    // =========================================================
    /**
     * Menghapus rute SPESIFIK antara dua stasiun.
     * Karena undirected, hapus dari kedua sisi.
     */
    public void removeRoute(String fromCode, String toCode) {
        if (!adjList.containsKey(fromCode) || !adjList.containsKey(toCode)) {
            System.out.println("✖ Stasiun tidak ditemukan!");
            return;
        }

        // Hapus edge dari A -> B
        adjList.get(fromCode).removeIf(r -> r.getDestination().equals(toCode));

        // Hapus edge dari B -> A
        adjList.get(toCode).removeIf(r -> r.getDestination().equals(fromCode));

        System.out.println("✔ Rute " + fromCode + " <-> " + toCode + " dihapus.");
    }

    // =========================================================
    // UTILITY: Print seluruh adjacency list
    // =========================================================
    public void printGraph() {
        System.out.println("\n========== RAILWAY GRAPH ==========");
        for (Map.Entry<String, ArrayList<Route>> entry : adjList.entrySet()) {
            String stationCode = entry.getKey();
            Station station = stations.get(stationCode);
            System.out.println("\n" + station);

            ArrayList<Route> routes = entry.getValue();
            if (routes.isEmpty()) {
                System.out.println("   (tidak ada rute)");
            } else {
                for (Route r : routes) {
                    System.out.println("   " + r);
                }
            }
        }
        System.out.println("====================================\n");
    }

    // Getter untuk stations (dipakai oleh traversal)
    public HashMap<String, Station> getStations() { return stations; }
    public HashMap<String, ArrayList<Route>> getAdjList() { return adjList; }
}