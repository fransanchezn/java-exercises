package org.fransanchez.exercises.backtracking;

import org.fransanchez.exercises.trees.TreeNode;

// Can we reach a leaf node in which the path does NOT contain zeros
public class ReachNonZeroLeaf {
    public boolean canReachLeaf(final TreeNode root) {
        if (root == null || root.val == 0) {
            return true;
        }

        // Leaf node
        if (root.left == null && root.right == null) {
            return true;
        }

        if (canReachLeaf(root.left)) {
            return true;
        }

        if (canReachLeaf(root.right)) {
            return true;
        }

        return false;
    }
}
