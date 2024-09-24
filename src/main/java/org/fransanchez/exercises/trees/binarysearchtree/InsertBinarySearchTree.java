package org.fransanchez.exercises.trees.binarysearchtree;

import org.fransanchez.exercises.trees.TreeNode;

// 701. Insert into a Binary Search Tree
public class InsertBinarySearchTree {
    public TreeNode insertIntoBST(final TreeNode root, final int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (val > root.val) {
            // insert into the right subtree
            root.right = insertIntoBST(root.right, val);
        } else {
            // insert into the left subtree
            root.left = insertIntoBST(root.left, val);
        }

        return root;
    }

    public TreeNode insertIntoBST2(final TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        final var leaf = search(root, val);
        if (leaf.val > val) {
            leaf.left = new TreeNode(val);
        } else {
            leaf.right = new TreeNode(val);
        }

        return root;
    }

    private TreeNode search(final TreeNode node, int val) {
        if (node == null) {
            return null;
        }

        if (node.val > val) {
            return node.left == null? node : search(node.left, val);
        } else {
            return node.right == null? node : search(node.right, val);
        }
    }

    public static void main(String[] args) {
        final var root = TreeNode.of(new Integer[] { 2,1,3 });

        final var sut = new InsertBinarySearchTree();
        final var result = sut.insertIntoBST(root, 4);

        System.out.println(result);
    }
}
