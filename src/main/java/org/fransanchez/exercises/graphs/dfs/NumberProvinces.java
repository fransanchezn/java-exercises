package org.fransanchez.exercises.graphs.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// 547. Number of Provinces
public class NumberProvinces {
    Map<Integer, List<Integer>> graph;
    final Set<Integer> seen = new HashSet<>();

    private Map<Integer, List<Integer>> buildMap(final int[][] isConnected) {
        final Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0 ; i < isConnected.length; i++) {
            final var iNodes = new ArrayList<Integer>();
            for (int j = 0; j < isConnected[i].length; j++) {
                final var jNodes = graph.getOrDefault(j, new ArrayList<>());

                if (isConnected[i][j] == 1) {
                    iNodes.add(j);
                    jNodes.add(i);
                    graph.put(j, jNodes);
                }
            }
            graph.put(i, iNodes);
        }

        return graph;
    }

    public int findCircleNum(final int[][] isConnected) {
        graph = buildMap(isConnected);

        var ans = 0;
        for (var entry : graph.entrySet()) {
            final var node = entry.getKey();
            if (!seen.contains(node)) {
                ans++;
                seen.add(node);
                dfs(node);
            }
        }

        return ans;
    }

    private void dfs(final int node) {
        final var neighbours = graph.get(node);
        for (var neighbour : neighbours) {
            if (!seen.contains(neighbour)) {
                seen.add(neighbour);
                dfs(neighbour);
            }
        }
    }

    public static void main(String[] args) {
        // [[1,1,0],[1,1,0],[0,0,1]]
        final var edges = new int[][] {{1,1,0}, {1,1,0},{0,0,1}};

        final var sut = new NumberProvinces();
        final var result = sut.findCircleNum(edges);

        System.out.println(result);
    }
}
