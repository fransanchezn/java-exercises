package org.fransanchez.exercises.trees.binarytrees.dfs;

import org.fransanchez.exercises.trees.TreeNode;

// 1448. Count Good Nodes in Binary Tree
public class CountGoodNodesBinaryTree {
    public int goodNodes(final TreeNode root) {
        return dfs(root, root.val);
    }

    private int dfs(final TreeNode node, final int maxValue) {
        if (node == null) {
            return 0;
        }

        // PreOrder Traverse
        var result = (node.val >= maxValue) ? 1 : 0;

        final var currentMaxValue = Math.max(maxValue, node.val);
        result += dfs(node.left, currentMaxValue);
        result += dfs(node.right, currentMaxValue);

        return result;
    }

    public static void main(final String[] args) {
        final var sut = new CountGoodNodesBinaryTree();

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

        final var result = sut.goodNodes(root);
        System.out.println(result);
    }
}
