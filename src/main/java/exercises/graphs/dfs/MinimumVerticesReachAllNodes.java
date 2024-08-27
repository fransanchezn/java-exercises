package exercises.graphs.dfs;

import java.util.ArrayList;
import java.util.List;

// 1557. Minimum Number of Vertices to Reach All Nodes
public class MinimumVerticesReachAllNodes {
    public List<Integer> findSmallestSetOfVertices(int n, final List<List<Integer>> edges) {
        final var indegree = new int[n];
        for (final List<Integer> edge : edges) {
            ++indegree[edge.get(1)];
        }

        final var result = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                result.add(i);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        final var edges = new ArrayList<List<Integer>>() {{
            add(List.of(0,1));
            add(List.of(0,2));
            add(List.of(2,5));
            add(List.of(3,4));
            add(List.of(4,2));
        }};

        final var sut = new MinimumVerticesReachAllNodes();
        final var result = sut.findSmallestSetOfVertices(6, edges);

        System.out.println(result);
    }
}
