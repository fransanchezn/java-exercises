package exercises.trees.binarytrees.dfs;

import exercises.trees.TreeNode;

// 112. Path Sum
public class PathSum {
    public boolean hasPathSum(final TreeNode root, final int targetSum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        final var left = hasPathSum(root.left, targetSum - root.val);
        final var right = hasPathSum(root.right, targetSum - root.val);

        return left || right;
    }

    public static void main(String[] args) {
        final var sut = new PathSum();

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

        final var result = sut.hasPathSum(root, 7);
        System.out.println(result);
    }
}
