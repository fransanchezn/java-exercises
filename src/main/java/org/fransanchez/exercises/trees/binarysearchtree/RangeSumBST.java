package org.fransanchez.exercises.trees.binarysearchtree;

import org.fransanchez.exercises.trees.TreeNode;

// 938. Range Sum of BST
public class RangeSumBST {
    public int rangeSumBST(final TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }

        int result = 0;
        if (root.val >= low && root.val <= high) {
            result += root.val;
        }

        if (root.val > low) {
            result += rangeSumBST(root.left, low, high);
        }

        if (root.val < high) {
            result += rangeSumBST(root.right, low, high);
        }

        return result;
    }

    public static void main(String[] args) {
        final var root = TreeNode.of(new Integer[] { 10,5,15,3,7,null,18 });

        final var sut = new RangeSumBST();
        final var result = sut.rangeSumBST(root, 7, 15);

        System.out.println(result);
    }
}
