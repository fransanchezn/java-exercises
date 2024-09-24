package org.fransanchez.exercises.backtracking;

import java.util.ArrayList;
import java.util.List;

public class AllPathsFromSourceTarget {
    public List<List<Integer>> allPathsSourceTarget(final int[][] graph) {
        final var ans = new ArrayList<List<Integer>>();
        final var curr = new ArrayList<Integer>() {{
            add(0);
        }};
        backtrack(curr, 0, ans, graph);
        return ans;
    }

    private void backtrack(final List<Integer> curr, final int node, final List<List<Integer>> ans, final int[][] graph) {
        if (node == graph.length - 1) {
            ans.add(new ArrayList<>(curr));
        }

        for (int i = 0; i < graph[node].length; i++) {
            final var nextNode = graph[node][i];
            curr.add(nextNode);
            backtrack(curr, nextNode, ans, graph);
            curr.removeLast();
        }
    }
}
