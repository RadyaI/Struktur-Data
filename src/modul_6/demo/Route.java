package modul_6.demo;

/**
 * CLASS ROUTE (EDGE / WEIGHTED EDGE)
 * -----------------------------------
 * Di dunia graph, Route ini adalah "Edge" atau "Sisi"-nya.
 * Yang bikin ini "weighted edge" adalah karena dia nyimpen
 * TIGA bobot sekaligus: distance, travelTime, dan fare.
 *
 * Analogi: Garis yang nyambungin dua titik di peta MRT = Route.
 * Tapi garis ini juga nyimpen info: "jarak berapa km?",
 * "perjalanan berapa menit?", "tiketnya berapa rupiah?"
 */
public class Route {

    // Kode stasiun TUJUAN dari edge ini
    // (stasiun asal disimpen di adjacency list sebagai KEY)
    private String destination;

    // BOBOT 1: Jarak fisik dalam kilometer
    private int distance;

    // BOBOT 2: Waktu perjalanan dalam menit
    private int travelTime;

    // BOBOT 3: Harga tiket dalam satuan mata uang
    private double fare;

    // Constructor
    public Route(String destination, int distance, int travelTime, double fare) {
        this.destination = destination;
        this.distance = distance;
        this.travelTime = travelTime;
        this.fare = fare;
    }

    // --- GETTERS ---
    public String getDestination() { return destination; }
    public int getDistance()       { return distance; }
    public int getTravelTime()     { return travelTime; }
    public double getFare()        { return fare; }

    // toString buat debugging / print info edge
    @Override
    public String toString() {
        return String.format("-> %s | Jarak: %d km | Waktu: %d mnt | Tarif: Rp%.0f",
                destination, distance, travelTime, fare);
    }
}