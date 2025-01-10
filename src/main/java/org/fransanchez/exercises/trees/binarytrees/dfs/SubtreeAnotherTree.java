package org.fransanchez.exercises.trees.binarytrees.dfs;

import org.fransanchez.exercises.trees.TreeNode;

// 572. Subtree of Another Tree
public class SubtreeAnotherTree {
    public boolean isSubtree(final TreeNode root, final TreeNode subRoot) {
        if (subRoot == null) {
            return true;
        }

        if (root == null) {
            return false;
        }

        if (isSameTree(root, subRoot)) {
            return true;
        }

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean isSameTree(final TreeNode nodeA, final TreeNode nodeB) {
        if (nodeA == null && nodeB == null) {
            return true;
        } else if (nodeA != null && nodeB != null && nodeA.val == nodeB.val) {
            return isSameTree(nodeA.left, nodeB.left) && isSameTree(nodeA.right, nodeB.right);
        }

        return false;
    }
}
