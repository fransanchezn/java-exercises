package exercises.graphs.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//  1466. Reorder Routes to Make All Paths Lead to the City Zero
public class ReorderRoutesLeadZero {
    Set<String> roads;
    Map<Integer, List<Integer>> graph;
    final Set<Integer> seen = new HashSet<>();

    public int minReorder(final int n, final int[][] connections) {
        graph = buildMap(connections);
        roads = buildRoads(connections);

        seen.add(0);
        return dfs(0);
    }

    private int dfs(final int node) {
        var ans = 0;

        for (var nb : graph.get(node)) {
            if (!seen.contains(nb)) {
                if (roads.contains(hash(node, nb))) {
                    ans++;
                }
                seen.add(nb);
                ans += dfs(nb);
            }
        }

        return ans;
    }

    private String hash(final int node1, final int node2) {
        return node1 + " " + node2;
    }

    private Set<String> buildRoads(final int[][] connections) {
        final var set = new HashSet<String>();
        for (int[] connection : connections) {
            set.add(hash(connection[0],connection[1]));
        }

        return set;
    }

    private Map<Integer, List<Integer>> buildMap(final int[][] connections) {
        final var map = new HashMap<Integer, List<Integer>>();

        for (int[] connection : connections) {
            final var aNode = connection[0];
            final var bNode = connection[1];

            final var aNeighbours = map.getOrDefault(aNode, new ArrayList<>());
            final var bNeighbours = map.getOrDefault(bNode, new ArrayList<>());
            aNeighbours.add(bNode);
            bNeighbours.add(aNode);

            map.put(aNode, aNeighbours);
            map.put(bNode, bNeighbours);
        }

        return map;
    }

    public static void main(final String[] args) {
        final var edges = new int[][] {{0,1},{1,3}, {2,3}, {4,0}, {4,5}};

        final var sut = new ReorderRoutesLeadZero();
        final var result = sut.minReorder(6, edges);

        System.out.println(result);
    }
}
