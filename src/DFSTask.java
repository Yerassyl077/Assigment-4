import java.util.*;

public class DFSTask {
    private Map<String, List<String>> adj = new LinkedHashMap<>();
    private Set<String> visited = new LinkedHashSet<>();

    public void addEdge(String u, String... neighbors) {
        adj.put(u, Arrays.asList(neighbors));
    }

    public void solveDFS(String start) {
        dfs(start);
    }

    private void dfs(String v) {
        visited.add(v);
        System.out.print(v + " ");
        for (String neighbor : adj.getOrDefault(v, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor);
            }
        }
    }

    public static void main(String[] args) {
        DFSTask graph = new DFSTask();
        graph.addEdge("A", "C", "B", "D");
        graph.addEdge("B", "A", "C", "E", "G");
        graph.addEdge("C", "A", "B", "D");
        graph.addEdge("D", "C", "A");
        graph.addEdge("E", "G", "F", "B");
        graph.addEdge("F", "G", "E");
        graph.addEdge("G", "F", "B");

        graph.solveDFS("A");
    }
}