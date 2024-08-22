package exercises.trees.binarytrees.dfs;

import exercises.trees.TreeNode;

// 111. Minimum Depth of Binary Tree
public class MinimumDepthBinaryTree {
    public int minDepth(final TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null) {
            return 1 + minDepth(root.right);
        } else if (root.right == null) {
            return 1 + minDepth(root.left);
        }

        final var left = minDepth(root.left);
        final var right = minDepth(root.right);

        return Math.min(left, right) + 1;
    }

    public static void main(String[] args) {
        final var sut = new MinimumDepthBinaryTree();

        // case 1
        final var node1 = new TreeNode(3);
        final var node2 = new TreeNode(9);
        final var node3 = new TreeNode(20);
        final var node4 = new TreeNode(15);
        final var node5 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        // case 2
        final var node11 = new TreeNode(2);
        final var node12 = new TreeNode(3);
        final var node13 = new TreeNode(4);
        final var node14 = new TreeNode(5);
        final var node15 = new TreeNode(6);

        node11.right = node12;
        node12.right = node13;
        node13.right = node14;
        node14.right = node15;

        final var result = sut.minDepth(node11);
        System.out.println(result);
    }
}
