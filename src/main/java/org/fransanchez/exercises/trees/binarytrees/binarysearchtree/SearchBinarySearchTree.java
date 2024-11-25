package org.fransanchez.exercises.trees.binarytrees.binarysearchtree;

import org.fransanchez.exercises.trees.TreeNode;

// 700. Search in a Binary Search Tree
// Search Balance tree = O(log n)
// Search Non-Balance tree = O(n)
// Insert/delete = O(log n) - improvement vs sorted arrays
public class SearchBinarySearchTree {
    public TreeNode searchBST(final TreeNode root, final int val) {
        if (root == null) {
            return null;
        }

        if (root.val > val) {
            return searchBST(root.left, val);
        } else if (root.val < val) {
            return searchBST(root.right, val);
        } else {
            return root;
        }
    }
}
