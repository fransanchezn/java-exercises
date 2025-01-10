package org.fransanchez.exercises.trees.binarytrees.dfs;

import org.fransanchez.exercises.trees.TreeNode;

// 110. Balanced Binary Tree
public class BalancedBinaryTree {
    public boolean isBalanced(final TreeNode root) {
        return dfsDepth(root).isBalance;
    }

    public Balance dfsDepth(final TreeNode node) {
        if (node == null) {
            return new Balance(0, true);
        }

        final var left = dfsDepth(node.left);
        final var right = dfsDepth(node.right);
        var balanced = Balance.isBalanced(left, right);

        final var depth = 1 + Math.max(left.depth, right.depth);

        return new Balance(depth,  balanced);
    }

    public record Balance(int depth, boolean isBalance) {
        public static boolean isBalanced(final Balance left, final Balance right) {
            return Math.abs(left.depth - right.depth) <= 1 && left.isBalance && right.isBalance;
        }
    }
}
