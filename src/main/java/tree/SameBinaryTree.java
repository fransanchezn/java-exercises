package tree;

public class SameBinaryTree {
    public static void main(String[] args) {
        final var left01 = new TreeNode(2);
        final var right01 = new TreeNode(3);
        final var root00 = new TreeNode(1, left01, right01);

        final var left11 = new TreeNode(2);
        final var right11 = new TreeNode(3);
        final var root10 = new TreeNode(1, left11, right11);

        System.out.println(isSameTree(root00, root10));
    }

    public static boolean isSameTree(final TreeNode p, final TreeNode q) {
        // Base case end of tree
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null || p.val != q.val) {
            return false;
        }

        final var sameTreeLeft = isSameTree(p.left, q.left);
        final var sameTreeRight = isSameTree(p.right, q.right);

        return sameTreeLeft && sameTreeRight;
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
