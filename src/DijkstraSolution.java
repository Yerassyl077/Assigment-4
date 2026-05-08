import java.util.*;

/*
 * TASK 4: Shortest Path Analysis (Edinburgh to Dundee)
 * Graph weights:
 * Edin-Stirling: 37, Edin-Perth: 45, Stirling-Perth: 34, Perth-Dundee: 22, Stirling-Glasgow: 27
 * * Potential Paths:
 * 1. Edinburgh -> Perth -> Dundee
 * Distance: 45 (Edin-Perth) + 22 (Perth-Dundee) = 67
 * 2. Edinburgh -> Stirling -> Perth -> Dundee
 * Distance: 37 (Edin-Stir) + 34 (Stir-Perth) + 22 (Perth-Dundee) = 93
 * * Result:
 * The shortest path is Edinburgh -> Perth -> Dundee with a total distance of 67.
 */

public class DijkstraSolution {
    static class Edge {
        String target;
        int weight;
        Edge(String target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Map<String, List<Edge>> graph = new HashMap<>();
        addEdge(graph, "Edinburgh", "Stirling", 37);
        addEdge(graph, "Edinburgh", "Perth", 45);
        addEdge(graph, "Stirling", "Perth", 34);
        addEdge(graph, "Perth", "Dundee", 22);
        addEdge(graph, "Stirling", "Glasgow", 27);

        calculateDijkstra(graph, "Edinburgh", "Dundee");
    }

    private static void addEdge(Map<String, List<Edge>> g, String u, String v, int w) {
        g.computeIfAbsent(u, k -> new ArrayList<>()).add(new Edge(v, w));
        g.computeIfAbsent(v, k -> new ArrayList<>()).add(new Edge(u, w));
    }

    private static void calculateDijkstra(Map<String, List<Edge>> g, String start, String end) {
        Map<String, Integer> dists = new HashMap<>();
        Map<String, String> prev = new HashMap<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        for (String node : g.keySet()) dists.put(node, Integer.MAX_VALUE);
        dists.put(start, 0);
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            if (curr.weight > dists.get(curr.target)) continue;
            for (Edge e : g.getOrDefault(curr.target, new ArrayList<>())) {
                int newDist = dists.get(curr.target) + e.weight;
                if (newDist < dists.get(e.target)) {
                    dists.put(e.target, newDist);
                    prev.put(e.target, curr.target);
                    pq.add(new Edge(e.target, newDist));
                }
            }
        }

        List<String> path = new ArrayList<>();
        for (String at = end; at != null; at = prev.get(at)) path.add(at);
        Collections.reverse(path);

        System.out.println("Task 5 - Shortest distance: " + dists.get(end));
        System.out.print("Task 5 - Path: " + String.join(" -> ", path));
    }
}