package org.fransanchez.exercises.backtracking;

import org.fransanchez.exercises.trees.TreeNode;

// 112. Path Sum
public class PathSum {
    public boolean hasPathSum(final TreeNode root, final int targetSum) {
        if (root == null) {
            return false;
        }

        final var value = targetSum - root.val;
        if (root.left == null && root.right == null && value == 0) {
            return true;
        }

        if (hasPathSum(root.left, value)) {
            return  true;
        }

        if (hasPathSum(root.right, value)) {
            return true;
        }

        return false;
    }
}
