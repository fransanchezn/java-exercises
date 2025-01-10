package org.fransanchez.exercises.trees.binarytrees.dfs;

import org.fransanchez.exercises.trees.TreeNode;

// 230. Kth Smallest Element in a BST
public class KthSmallestBSTII {
    private int counter = 0;
    private TreeNode kSmallest = null;

    public int kthSmallest(final TreeNode root, int k) {
        dfs(root, k);
        return kSmallest.val;
    }

    private void dfs(final TreeNode node, int k) {
        if (node == null) {
            return;
        }

        dfs(node.left, k);
        counter++;
        if (counter == k) {
            kSmallest = node;
            return;
        }
        dfs(node.right, k);
    }
}
