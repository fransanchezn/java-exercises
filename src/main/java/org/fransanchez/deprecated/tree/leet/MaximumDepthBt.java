package org.fransanchez.deprecated.tree.leet;

public class MaximumDepthBt {

    public static void main(String[] args) {
        final var edge15 = new LeetTreeNode(15);
        final var edge6 = new LeetTreeNode(7);
        final var edge20 = new LeetTreeNode(20, edge15, edge6);

        final var edge9 = new LeetTreeNode(9);
        final var edge3 = new LeetTreeNode(3, edge9, edge20);

        final var result = maxDepth(edge3);
        System.out.println(result);
    }

    public static int maxDepth(LeetTreeNode root) {
        return maxDepthRec(root, 0);
    }

    public static int maxDepthRec(LeetTreeNode root, int counter) {
        if (root == null) {
            return counter;
        }

        return Math.max(maxDepthRec(root.left, counter + 1), maxDepthRec(root.right, counter + 1));
    }
}
