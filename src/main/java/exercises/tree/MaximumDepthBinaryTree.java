package exercises.tree;

public class MaximumDepthBinaryTree {
    public static void main(final String[] args) {

        final var left3 = new TreeNode(15);
        final var right4 = new TreeNode(7);

        final var left1 = new TreeNode(9);
        final var right1 = new TreeNode(20, left3, right4);

        final var root = new TreeNode(3, left1, right1);

        System.out.println(maxDepth(root));
    }

    public static int maxDepth(final TreeNode root) {
        if (root == null) {
            return 0;
        }

        System.out.println(root.val);

        int right = maxDepth(root.right);
        int left = maxDepth(root.left);

        return 1 + Math.max(right, left);
    }

    // Inner class for TreeNode
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
