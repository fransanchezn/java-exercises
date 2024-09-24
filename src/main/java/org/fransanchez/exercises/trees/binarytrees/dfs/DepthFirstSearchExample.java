package org.fransanchez.exercises.trees.binarytrees.dfs;

import org.fransanchez.exercises.trees.TreeNode;

public class DepthFirstSearchExample {
    public void preorderDfs(TreeNode node) {
        if (node == null) {
            return;
        }

        System.out.println("(Do work here) Node.val: " + node.val);

        preorderDfs(node.left);
        preorderDfs(node.right);
    }

    public void inorderDfs(TreeNode node) {
        if (node == null) {
            return;
        }

        inorderDfs(node.left);
        System.out.println("(Do work here) Node.val: " + node.val);
        inorderDfs(node.right);
    }

    public void postorderDfs(TreeNode node) {
        if (node == null) {
            return;
        }

        postorderDfs(node.left);
        postorderDfs(node.right);
        System.out.println("(Do work here) Node.val: " + node.val);
    }

    public static void main(final String[] args) {
        final var sut = new DepthFirstSearchExample();

        final var root = new TreeNode(0);
        final var nodeOne = new TreeNode(1);
        final var nodeTwo = new TreeNode(2);
        final var nodeThree = new TreeNode(3);
        final var nodeFour = new TreeNode(4);
        final var nodeFive = new TreeNode(5);
        final var nodeSix = new TreeNode(6);

        root.left = nodeOne;
        root.right = nodeTwo;

        nodeOne.left = nodeThree;
        nodeOne.right = nodeFour;

        nodeTwo.left = nodeFive;
        nodeTwo.right = nodeSix;

        System.out.println("## PREORDER DFS ");
        sut.preorderDfs(root);

        System.out.println("## INORDER DFS ");
        sut.inorderDfs(root);

        System.out.println("## POSTORDER DFS ");
        sut.postorderDfs(root);
    }
}
