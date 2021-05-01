package tree.leet;

public class MaximumDepthBt {

    public static void main(String[] args) {
        final var edge15 = new TreeNode(15);
        final var edge6 = new TreeNode(7);
        final var edge20 = new TreeNode(20, edge15, edge6);

        final var edge9 = new TreeNode(9);
        final var edge3 = new TreeNode(3, edge9, edge20);

        final var result = maxDepth(edge3);
        System.out.println(result);
    }

    public static int maxDepth(TreeNode root) {
        return maxDepthRec(root, 0);
    }

    public static int maxDepthRec(TreeNode root, int counter) {
        if (root == null) {
            return counter;
        }

        return Math.max(maxDepthRec(root.left, counter + 1), maxDepthRec(root.right, counter + 1));
    }
}
