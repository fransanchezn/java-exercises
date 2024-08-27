package exercises.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildGraph {
    public Map<Integer, List<Integer>> buildGraphFromArrayOfEdges(final int[][] edges) {
        final var graph = new HashMap<Integer, List<Integer>>();

        for (int[] edge : edges) {
            final var node = edge[0];
            final var neighbors = graph.getOrDefault(node, new ArrayList<>());
            neighbors.add(edge[1]);
            graph.put(node, neighbors);
        }

        return graph;
    }

    public Map<Integer, List<Integer>> buildGraphFromAdjacencyList(final int[][] input) {
        final var graph = new HashMap<Integer, List<Integer>>();

        for (int i = 0 ; i < input.length; i++) {

            final var adjacency = new ArrayList<Integer>();
            for (int j = 0; j < input[i].length; j++) {
                adjacency.add(input[i][j]);
            }

            graph.put(i, adjacency);
        }

        return graph;
    }

    public Map<Integer, List<Integer>> buildGraphFromAdjacencyMatrix(final int[][] input) {
        final var graph = new HashMap<Integer, List<Integer>>();

        for (int i = 0; i < input.length; i++) {
            final var neighbours = new ArrayList<Integer>();
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j] == 1) {
                    neighbours.add(j);
                }
            }
            graph.put(i, neighbours);
        }

        return graph;
    }

    public static void main(String[] args) {
        // Array of Edgers
        final var arrayOfEdges = new int[][] {{0, 1}, {1, 2}, {2, 0}, {2, 3}};
        final var sut = new BuildGraph();
        final var graph1 = sut.buildGraphFromArrayOfEdges(arrayOfEdges);

        System.out.println(graph1);

        // Adjacency
        final var adjacencyList = new int[][] {{1}, {2},{0, 3}, {}};

        final var graph2 = sut.buildGraphFromAdjacencyList(adjacencyList);
        System.out.println(graph2);

        // Adjacency matrix
        final var adjacencyMatrix = new int[][] {
                {0,1,0,0},
                {0,0,1,0},
                {1,0,0,1},
                {0,0,0,0}
        };

        final var graph3 = sut.buildGraphFromAdjacencyMatrix(adjacencyMatrix);
        System.out.println(graph3);
    }
}
