package org.fransanchez.exercises.graphs.bfs;

import org.fransanchez.exercises.trees.TreeNode;
import java.util.List;

// 863. All Nodes Distance K in Binary Tree
public class AllNodesDistanceKBinaryTree {

    public List<Integer> distanceK(final TreeNode root, final TreeNode target, final int k) {
        // TODO: Implement me!
        throw new RuntimeException("Implement me!");
    }

    public static void main(final String[] args) {
        final var matrix = new int[][] {{0,0,0},{1,1,0},{1,1,0}};

        final var sut = new ShortestPathBinaryMatrix();
        final var result = sut.shortestPathBinaryMatrix(matrix);

        System.out.println(result);
    }
}
