package deprecated.tree;

public class CountCompleteTreeNodesBinaryTree {
    public static void main(String[] args) {
        final var node4 = new TreeNode(4);
        final var node5 = new TreeNode(5);
        final var node2 = new TreeNode(2, node4, node5);
        final var node6 = new TreeNode(6);
        final var node3 = new TreeNode(3, node6, null);
        final var node1 = new TreeNode(1, node2, node3);

        System.out.println(countNodes(node1));
    }

    public static int countNodes(final TreeNode root) {
        if (root == null) {
            return 0;
        }

        var countLeft = countNodes(root.left);
        var countRight = countNodes(root.right);

        return countLeft + countRight + 1;
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(final int val) {
            this.val = val;
        }

        TreeNode(final int val, final TreeNode left, final TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
