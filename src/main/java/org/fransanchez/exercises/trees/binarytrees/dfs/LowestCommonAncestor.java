package org.fransanchez.exercises.trees.binarytrees.dfs;

import org.fransanchez.exercises.trees.TreeNode;

// 236. Lowest Common Ancestor of a Binary Tree
public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(final TreeNode root, final TreeNode p, final TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root.val == p.val || root.val == q.val) {
            return root;
        }

        final var left = lowestCommonAncestor(root.left, p , q);
        final var right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        } else if (left != null ) {
            return left;
        } else {
            return right;
        }
    }

    public static void main(final String[] args) {
        final var sut = new LowestCommonAncestor();

        final var node1 = new TreeNode(3);
        final var node2 = new TreeNode(5);
        final var node3 = new TreeNode(1);
        final var node4 = new TreeNode(6);
        final var node5 = new TreeNode(2);
        final var node6 = new TreeNode(0);
        final var node7 = new TreeNode(8);
        final var node8 = new TreeNode(7);
        final var node9 = new TreeNode(4);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;

        node5.left = node8;
        node5.right = node9;

        final var result = sut.lowestCommonAncestor(node1, node2, node8);
        System.out.println(result.val);
    }
}
