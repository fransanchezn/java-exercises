package exercises.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<String, List<String>> adjacentList;

    public Graph() {
        this.adjacentList = new HashMap<>();
    }

    public void addNode(final String node) {
        adjacentList.computeIfAbsent(node, c -> new ArrayList<>());
    }

    public void addEdge(final String node1, final String node2) {
        adjacentList.get(node1).add(node2);
        adjacentList.get(node2).add(node1);
    }

    public Map<String, List<String>> getGraph() {
        return adjacentList;
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        g.addNode("1");
        g.addNode("2");
        g.addEdge("1", "2");
        System.out.println(g.getGraph());
    }
}
