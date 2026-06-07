package modul_6.codelab;

import java.util.*;

public class GraphExercise {

    private Map<String, List<String>> adjVertices;

    public GraphExercise() {
        this.adjVertices = new HashMap<>();
    }

    // ==================================================================================
    // MISSION 1: BUILD THE RAILWAY (Adjacency List)
    // ==================================================================================

    public void addVertex(String label) {
        adjVertices.putIfAbsent(label, new ArrayList<>());
    }

    public void addEdge(String label1, String label2) {
        adjVertices.get(label1).add(label2);
        adjVertices.get(label2).add(label1);
    }

    public void printGraph() {
        for (String vertex : adjVertices.keySet()) {
            System.out.print("Station " + vertex + " connects to: ");
            System.out.println(adjVertices.get(vertex));
        }
    }

    // ==================================================================================
    // MISSION 2: ROUTE PLANNING (BFS & DFS)
    // ==================================================================================

    public void bfs(String root) {
        System.out.println("\n[BFS Traversal starting from " + root + "]");
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(root);
        queue.add(root);

        while (!queue.isEmpty()) {
            String vertex = queue.poll();
            System.out.print(vertex + " -> ");

            for (String neighbor : adjVertices.get(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println("END");
    }

    public void dfs(String root) {
        System.out.println("\n[DFS Traversal starting from " + root + "]");
        Set<String> visited = new HashSet<>();
        dfsRecursive(root, visited);
        System.out.println("END");
    }

    private void dfsRecursive(String vertex, Set<String> visited) {
        visited.add(vertex);
        System.out.print(vertex + " -> ");

        for (String neighbor : adjVertices.get(vertex)) {
            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited);
            }
        }
    }

    public static void main(String[] args) {
        GraphExercise railway = new GraphExercise();

        // 1. Create Stations (Vertices)
        railway.addVertex("Surabaya");
        railway.addVertex("Malang");
        railway.addVertex("Madiun");
        railway.addVertex("Solo");
        railway.addVertex("Jogja");
        railway.addVertex("Bandung");
        railway.addVertex("Jakarta");

        // 2. Lay Tracks (Edges)
        railway.addEdge("Jakarta", "Bandung");
        railway.addEdge("Bandung", "Jogja");
        railway.addEdge("Jogja", "Solo");
        railway.addEdge("Solo", "Madiun");
        railway.addEdge("Madiun", "Surabaya");
        railway.addEdge("Madiun", "Malang");
        railway.addEdge("Malang", "Surabaya");

        // 3. Visualize
        System.out.println(">>> TRANS-JAVA RAILWAY NETWORK <<<");
        railway.printGraph();

        // 4. Test Traversals
        railway.bfs("Surabaya");
        railway.dfs("Surabaya");
    }
}