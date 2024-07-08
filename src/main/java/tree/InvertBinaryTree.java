package tree;

public class InvertBinaryTree {
    public static void main(final String[] args) {

    }

    public TreeNode invertTree(final TreeNode root) {
        if(root == null) {
            return null;
        }

        var left = invertTree(root.left);
        var right = invertTree(root.right);
        root.left = right;
        root.right = left;

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
