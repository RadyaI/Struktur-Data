package modul_6.demo;

/**
 * CLASS STATION (VERTEX)
 * ---------------------
 * Di dunia graph, Station ini adalah "Node" atau "Vertex"-nya.
 * Setiap stasiun punya identitas unik (code) dan info tambahannya.
 *
 * Analogi: Bayangin peta MRT. Setiap titik/lingkaran di peta = Station.
 */
public class Station {

    // Kode unik stasiun, ini yang jadi KEY di HashMap nanti
    // contoh: "JKT", "SBY", "BDG"
    private String code;

    // Nama lengkap stasiun
    private String name;

    // Kota tempat stasiun berada
    private String city;

    // Constructor: cara bikin objek Station baru
    public Station(String code, String name, String city) {
        this.code = code;
        this.name = name;
        this.city = city;
    }

    // --- GETTERS ---
    public String getCode() { return code; }
    public String getName() { return name; }
    public String getCity() { return city; }

    // toString biar gampang di-print
    @Override
    public String toString() {
        return String.format("[%s] %s (%s)", code, name, city);
    }
}