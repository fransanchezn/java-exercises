package org.fransanchez.exercises.graphs.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReachableNodesRestrictions {
    final Set<Integer> seen = new HashSet<>();

    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        final var restrictedSet = buildSet(restricted);
        final var graph = buildMap(edges, restrictedSet);

        if (restrictedSet.contains(0)) {
            return 0;
        }

        seen.add(0);
        dfs(0, graph);
        return seen.size();
    }

    private void dfs(final int node, final Map<Integer, List<Integer>> graph) {
        for (var nb : graph.getOrDefault(node, new ArrayList<>())) {
            if (!seen.contains(nb)) {
                seen.add(nb);
                dfs(nb, graph);
            }
        }
    }

    private Set<Integer> buildSet(final int[] restricted) {
        final var set = new HashSet<Integer>();
        for (var node: restricted) {
            set.add(node);
        }

        return set;
    }

    private Map<Integer, List<Integer>> buildMap(final int[][] edges, final Set<Integer> restricted) {
        final var map = new HashMap<Integer, List<Integer>>();
        for (var edge: edges) {
            if (!restricted.contains(edge[0]) && !restricted.contains(edge[1])) {
                map.computeIfAbsent(edge[0], v -> new ArrayList<>()).add(edge[1]);
                map.computeIfAbsent(edge[1], v -> new ArrayList<>()).add(edge[0]);
            }
        }

        return map;
    }

    public static void main(String[] args) {
        final int[][] edges = new int[][] {{0,1}, {1,2},{3,1},{4,0},{0,5},{5,6}};

        final var sut = new ReachableNodesRestrictions();
        final var result = sut.reachableNodes(7, edges, new int[] {4, 5});

        System.out.println(result);
    }
}
