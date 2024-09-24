package org.fransanchez.exercises.graphs.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FindPathExistsGraph {
    final Set<Integer> seen = new HashSet<>();

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        final var graph = buildMap(edges);
        if (graph.isEmpty()) {
            return source == destination;
        }

        dfs(source, graph);
        return seen.contains(destination);
    }

    private void dfs(final int node, final Map<Integer, List<Integer>> graph) {
        for (var nb : graph.get(node)) {
            if (!seen.contains(nb)) {
                seen.add(nb);
                dfs(nb, graph);
            }
        }
    }

    private Map<Integer, List<Integer>> buildMap(final int[][] edges) {
        final var map = new HashMap<Integer, List<Integer>>();
        for (int[] edge : edges) {
            final var x = edge[0];
            final var y = edge[1];
            map.computeIfAbsent(x, val -> new ArrayList<>()).add(y);
            map.computeIfAbsent(y, val -> new ArrayList<>()).add(x);
        }

        return map;
    }

    public static void main(String[] args) {
        //  n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
        final var edges = new int[][] {{0,1}, {1,2},{2,0}};

        final var sut = new FindPathExistsGraph();
        final var result = sut.validPath(3, edges, 0, 2);

        System.out.println(result);
    }
}
