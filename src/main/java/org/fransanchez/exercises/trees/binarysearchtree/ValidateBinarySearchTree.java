package org.fransanchez.exercises.trees.binarysearchtree;

import org.fransanchez.exercises.trees.TreeNode;

// 98. Validate Binary Search Tree
public class ValidateBinarySearchTree {
    public boolean isValidBST(final TreeNode root) {
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isValidBST(final TreeNode node, final int min, final int max) {
        if (node == null) {
            return true;
        }

        if (min >= node.val || node.val >= max) {
            return false;
        }

        final var left = isValidBST(node.left, min, node.val);
        final var right = isValidBST(node.right, node.val, max);

        return left && right;
    }

    public static void main(String[] args) {
        final var root = TreeNode.of(new Integer[] { 1,1 });

        final var sut = new ValidateBinarySearchTree();
        final var result = sut.isValidBST(root);

        System.out.println(result);
    }
}
