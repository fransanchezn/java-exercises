package exercises.trees.binarytrees.dfs;

import exercises.trees.TreeNode;

// 104. Maximum Depth of Binary Tree
public class MaximumDepthBinaryTree {
    public int maxDepth(final TreeNode root) {
        if (root == null) {
            return 0;
        }

        final var leftDepth = maxDepth(root.left);
        final var rightDepth = maxDepth(root.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        final var sut = new MaximumDepthBinaryTree();

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

        final var result = sut.maxDepth(root);
        System.out.println(result);
    }
}
