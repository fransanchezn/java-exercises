package org.fransanchez.exercises.trees.binarytrees.dfs;

import org.fransanchez.exercises.trees.TreeNode;

// 543. Diameter of Binary Tree
public class DiameterBinaryTree {
    private int maxDiameter = 0;

    public int diameterOfBinaryTree(final TreeNode root) {
        dfs(root);
        return maxDiameter;
    }

    private int dfs(final TreeNode node) {
        if (node == null) {
            return 0;
        }

        final var left = dfs(node.left);
        final var right = dfs(node.right);
        maxDiameter = Math.max(maxDiameter, left + right);

        return 1 + Math.max(left, right);
    }
}
