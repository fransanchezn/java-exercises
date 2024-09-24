package org.fransanchez.exercises.trees.binarytrees.dfs;

import org.fransanchez.exercises.trees.TreeNode;

// 543. Diameter of Binary Tree
public class DiameterBinaryTree {
    private int diameter = 0;

    public int diameterOfBinaryTree(final TreeNode root) {
        longestPath(root);
        return diameter;
    }

    private int longestPath(final TreeNode node) {
        if (node == null) {
            return 0;
        }

        final var left = longestPath(node.left);
        final var right = longestPath(node.right);

        diameter = Math.max(diameter, left + right);

        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        final var root = TreeNode.of(new Integer[] { 4,2,null,3,1,null,null,5 });

        final var sut = new DiameterBinaryTree();
        final var result = sut.diameterOfBinaryTree(root);

        System.out.println(result);
    }
}
