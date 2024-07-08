package tree;

public class PathSumBinaryTree {
    public static void main(String[] args) {

        final var node7 = new TreeNode(7);
        final var node8 = new TreeNode(2);

        final var node4 = new TreeNode(11, node7, node8);

        final var node2 = new TreeNode(4, node4 , null);

        final var node5 = new TreeNode(13);

        final var node9 = new TreeNode(1);
        final var node6 = new TreeNode(4, null, node9);

        final var node3 = new TreeNode(8, node5, node6);

        final var node1 = new TreeNode(5, node2, node3);

        System.out.println(hasPathSum(node1, 22));

    }

    public static boolean hasPathSum(final TreeNode root, final int targetSum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        final var leftSum = hasPathSum(root.left, targetSum - root.val);
        final var rightSum = hasPathSum(root.right, targetSum - root.val);

        return leftSum || rightSum;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
