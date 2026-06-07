package modul_6.codelab;

import java.util.*;

public class mission1 {

    private Map<String, List<String>> adjVertices;

    public mission1() {
        this.adjVertices = new HashMap<>();
    }

    public void addVertex(String label) {
        adjVertices.putIfAbsent(label, new ArrayList<>());
    }

    public void addEdge(String label1, String label2) {
        // Connect label1 -> label2
        adjVertices.get(label1).add(label2);

        // Connect label2 -> label1 (Undirected / Two-way track)
        adjVertices.get(label2).add(label1);
    }

    public void printGraph() {
        for (String vertex : adjVertices.keySet()) {
            System.out.print("Station " + vertex + " connects to: ");
            System.out.println(adjVertices.get(vertex));
        }
    }

    public static void main(String[] args) {
        mission1 railway = new mission1();

        railway.addVertex("Surabaya");
        railway.addVertex("Malang");
        railway.addVertex("Madiun");
        railway.addVertex("Solo");
        railway.addVertex("Jogja");
        railway.addVertex("Bandung");
        railway.addVertex("Jakarta");

        railway.addEdge("Jakarta", "Bandung");
        railway.addEdge("Bandung", "Jogja");
        railway.addEdge("Jogja", "Solo");
        railway.addEdge("Solo", "Madiun");
        railway.addEdge("Madiun", "Surabaya");
        railway.addEdge("Madiun", "Malang");
        railway.addEdge("Malang", "Surabaya");

        System.out.println(">>> TRANS-JAVA RAILWAY NETWORK <<<");
        railway.printGraph();
    }
}