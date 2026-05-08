import java.util.*;

public class GraphTraversal {
    private Map<String, List<String>> adj = new LinkedHashMap<>();

    public void addEdge(String u, String v) {
        adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
    }

    public void dfs(String start) {
        Set<String> visited = new HashSet<>();
        dfsHelper(start, visited);
        System.out.println();
    }

    private void dfsHelper(String v, Set<String> visited) {
        visited.add(v);
        System.out.print(v + " ");
        for (String neighbor : adj.getOrDefault(v, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited);
            }
        }
    }

    public void bfs(String start) {
        Set<String> visited = new HashSet<>();
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
        System.out.println();
    }

    public static void main(String[] args) {
        GraphTraversal g = new GraphTraversal();
        g.addEdge("A", "C"); g.addEdge("A", "B"); g.addEdge("A", "D");
        g.addEdge("B", "A"); g.addEdge("B", "C"); g.addEdge("B", "E"); g.addEdge("B", "G");
        g.addEdge("C", "A"); g.addEdge("C", "B"); g.addEdge("C", "D");
        g.addEdge("D", "C"); g.addEdge("D", "A");
        g.addEdge("E", "G"); g.addEdge("E", "F"); g.addEdge("E", "B");
        g.addEdge("F", "G"); g.addEdge("F", "E");
        g.addEdge("G", "F"); g.addEdge("G", "B");

        System.out.print("DFS: ");
        g.dfs("A");
        System.out.print("BFS: ");
        g.bfs("A");
    }
}