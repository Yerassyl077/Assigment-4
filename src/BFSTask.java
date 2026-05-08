import java.util.*;

public class BFSTask {
    private Map<String, List<String>> adj = new LinkedHashMap<>();

    public void addEdge(String u, String... neighbors) {
        adj.put(u, Arrays.asList(neighbors));
    }

    public void solveBFS(String start) {
        Set<String> visited = new LinkedHashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            String v = queue.poll();
            System.out.print(v + " ");

            for (String neighbor : adj.getOrDefault(v, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {
        BFSTask graph = new BFSTask();
        graph.addEdge("A", "C", "B", "D");
        graph.addEdge("B", "A", "C", "E", "G");
        graph.addEdge("C", "A", "B", "D");
        graph.addEdge("D", "C", "A");
        graph.addEdge("E", "G", "F", "B");
        graph.addEdge("F", "G", "E");
        graph.addEdge("G", "F", "B");

        graph.solveBFS("A");
    }
}