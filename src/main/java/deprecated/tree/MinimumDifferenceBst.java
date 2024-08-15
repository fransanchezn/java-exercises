package deprecated.tree;

public class MinimumDifferenceBst {
    public static int minDistance = Integer.MAX_VALUE;
    public static int prevNode = Integer.MAX_VALUE;

    public static void main(String[] args) {

//        final var node3 = new TreeNode(6);
//        final var node5 = new TreeNode(1);
//        final var node4 = new TreeNode(3);
//        final var node2 = new TreeNode(2, node4, node5);
//        final var node1 = new TreeNode(4, node2, node3);


        final var node5 = new TreeNode(911);
        final var node3 = new TreeNode(701, null, node5);
        final var node4 = new TreeNode(227);
        final var node2 = new TreeNode(104, null, node4);
        final var node1 = new TreeNode(236, node2, node3);
        System.out.println(getMinimumDifference(node1));
    }

    public static int getMinimumDifference(final TreeNode root) {
        return inOrderTransversal2(root, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    // In order transversal BST
    private static void inOrderTransversal(final TreeNode node) {
        if (node == null) {
            return;
        }

        inOrderTransversal(node.left);

        // Update variables
        minDistance = Math.min(minDistance, Math.abs(node.val - prevNode));
        prevNode = node.val;

        inOrderTransversal(node.right);
    }

    // In order transversal BST
    private static int inOrderTransversal2(final TreeNode node, final int prevVal, final int minDistance) {
        if (node == null || minDistance == 1) {
            return minDistance;
        }

        int currentMinDistance = Math.min(minDistance, Math.abs(node.val - prevVal));

        var minDistanceLeft = inOrderTransversal2(node.left, node.val, currentMinDistance);
        var minDistanceRight = inOrderTransversal2(node.right, node.val, currentMinDistance);

        return Math.min(minDistanceRight, minDistanceLeft);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}
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
