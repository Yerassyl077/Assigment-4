import java.util.*;

/*
 * TASK 1: Detailed Trace of Depth First Search (DFS)
 * Source node: A
 * Graph: A: C B D; B: A C E G; C: A B D; D: C A; E: G F B; F: G E; G: F B
 * * Trace:
 * 1. Visit A. Stack: [A]. Visited: {A}
 * 2. From A, move to C. Stack: [A, C]. Visited: {A, C}
 * 3. From C, move to B. Stack: [A, C, B]. Visited: {A, C, B}
 * 4. From B, move to E. Stack: [A, C, B, E]. Visited: {A, C, B, E}
 * 5. From E, move to G. Stack: [A, C, B, E, G]. Visited: {A, C, B, E, G}
 * 6. From G, move to F. Stack: [A, C, B, E, G, F]. Visited: {A, C, B, E, G, F}
 * 7. From F, all neighbors (G, E) visited. Backtrack to G -> E -> B.
 * 8. Backtrack to C. Move to D (unvisited). Stack: [A, C, D]. Visited: {A, C, B, E, G, F, D}
 * 9. All nodes visited.
 * Order: A -> C -> B -> E -> G -> F -> D
 * * TASK 2: Detailed Trace of Breadth First Search (BFS)
 * Source node: A
 * * Trace:
 * 1. Initialize Queue: [A]. Visited: {A}
 * 2. Dequeue A. Enqueue neighbors C, B, D. Queue: [C, B, D]. Visited: {A, C, B, D}
 * 3. Dequeue C. Neighbors (A, B, D) already visited or in queue.
 * 4. Dequeue B. Enqueue new neighbors E, G. Queue: [D, E, G]. Visited: {A, C, B, D, E, G}
 * 5. Dequeue D. Neighbors visited.
 * 6. Dequeue E. Enqueue new neighbor F. Queue: [G, F]. Visited: {A, C, B, D, E, G, F}
 * 7. Dequeue G. Neighbors visited.
 * 8. Dequeue F. Neighbors visited.
 * Order: A -> C -> B -> D -> E -> G -> F
 */

public class GraphTraversal {
    private Map<String, List<String>> adj = new LinkedHashMap<>();

    public void addEdge(String u, String... neighbors) {
        adj.put(u, Arrays.asList(neighbors));
    }

    public void dfs(String v, Set<String> visited) {
        visited.add(v);
        System.out.print(v + " ");
        for (String neighbor : adj.getOrDefault(v, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, visited);
            }
        }
    }

    public void bfs(String start) {
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
        GraphTraversal g = new GraphTraversal();
        g.addEdge("A", "C", "B", "D");
        g.addEdge("B", "A", "C", "E", "G");
        g.addEdge("C", "A", "B", "D");
        g.addEdge("D", "C", "A");
        g.addEdge("E", "G", "F", "B");
        g.addEdge("F", "G", "E");
        g.addEdge("G", "F", "B");

        System.out.print("Task 3 - DFS Output: ");
        g.dfs("A", new HashSet<>());
        System.out.print("\nTask 3 - BFS Output: ");
        g.bfs("A");
    }
}