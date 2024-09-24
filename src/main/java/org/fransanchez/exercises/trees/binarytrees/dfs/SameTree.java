package org.fransanchez.exercises.trees.binarytrees.dfs;

import org.fransanchez.exercises.trees.TreeNode;

// 100. Same Tree
public class SameTree {
    public boolean isSameTree(final TreeNode p, final TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        }

        final var left = isSameTree(p.left, q.left);
        final var right = isSameTree(p.right, q.right);

        return left && right;
    }

    public static void main(String[] args) {
        final var sut = new SameTree();

        final var root = new TreeNode(3);
        final var nodeOne = new TreeNode(1);
        final var nodeTwo = new TreeNode(4);
        final var nodeThree = new TreeNode(3);
        final var nodeFour = new TreeNode(1);
        final var nodeFive = new TreeNode(5);

        root.left = nodeOne;
        root.right = nodeTwo;

        nodeOne.left = nodeThree;

        nodeTwo.left = nodeFour;
        nodeTwo.right = nodeFive;

        final var result = sut.isSameTree(root, root);
        System.out.println(result);
    }
}
