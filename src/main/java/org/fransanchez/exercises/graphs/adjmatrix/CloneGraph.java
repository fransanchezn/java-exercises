package org.fransanchez.exercises.graphs.adjmatrix;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

// 133. Clone Graph
public class CloneGraph {
    public Node cloneGraph(final Node node) {
        if (node == null) {
            return null;
        }

        final var nodeMap = new HashMap<Integer, Node>();

        final var queue = new LinkedList<Node>();
        queue.add(node);
        nodeMap.put(node.val, new Node(node.val));

        while (!queue.isEmpty()) {
            final var popNode = queue.remove();

            for (var neighbor : popNode.neighbors) {
                if (!nodeMap.containsKey(neighbor.val)) {
                    nodeMap.put(neighbor.val, new Node(neighbor.val));
                    queue.add(neighbor);
                }

                nodeMap.get(popNode.val).neighbors.add(nodeMap.get(neighbor.val));
            }
        }

        return nodeMap.get(node.val);
    }

    public static void main(String[] args) {
        final var sut = new CloneGraph();
        final var node1 = new Node(1);
        final var node2 = new Node(2);
        final var node3 = new Node(3);
        final var node4 = new Node(4);

        node1.neighbors = List.of(node2, node4);
        node2.neighbors = List.of(node1, node3);
        node3.neighbors = List.of(node2, node4);
        node4.neighbors = List.of(node1, node3);


        final var result = sut.cloneGraph(node1);
        System.out.println(result.neighbors);
    }
}
