package tree;

public class InvertBinaryTree {
    public static void main(final String[] args) {

    }

    public TreeNode invertTree(final TreeNode root) {
        return root;
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
