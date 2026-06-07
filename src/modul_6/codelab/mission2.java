package modul_6.codelab;

import java.util.*;

public class mission2 {

    private Map<String, List<String>> adjVertices;

    public mission2() {
        this.adjVertices = new HashMap<>();
    }

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
        mission2 railway = new mission2();

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

        railway.bfs("Surabaya");
        railway.dfs("Surabaya");
    }
}