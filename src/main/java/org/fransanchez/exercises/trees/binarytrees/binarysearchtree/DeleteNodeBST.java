package org.fransanchez.exercises.trees.binarytrees.binarysearchtree;

import org.fransanchez.exercises.trees.TreeNode;

// 450. Delete Node in a BST
// Find node to delete O(log n)
// Delete node O(log n)
// Delete left node O(log n)
public class DeleteNodeBST {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else {
            // Perform deletion
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                final var minNode = minNode(root.right);
                root.val = minNode.val;
                root.right = deleteNode(root.right, minNode.val);
            }
        }

        return root;
    }

    private TreeNode minNode(final TreeNode root) {
        var current = root;
        while (current != null && current.left != null){
            current = current.left;
        }

        return current;
    }
}
