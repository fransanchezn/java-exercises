package org.fransanchez.deprecated.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class AverageLevelsBinaryTree {
    public static void main(String[] args) {

//        final var node2 = new TreeNode(9);
//
//        final var node4 = new TreeNode(15);
//        final var node5 = new TreeNode(7);
//        final var node3 = new TreeNode(20, node4, node5);
//
//        final var node1 = new TreeNode(3, node2, node3);


        final var node2 = new TreeNode(2147483647);
        final var node3 = new TreeNode(2147483647);
        final var node1 = new TreeNode(2147483647, node2, node3);

        averageOfLevels(node1).forEach(System.out::println);
    }

    public static List<Double> averageOfLevels(final TreeNode root) {
        final var averagePerLevel = new ArrayList<Double>();

        final var nodes = new ArrayDeque<TreeNode>();
        nodes.push(root);

        while(!nodes.isEmpty()) {

            int size = nodes.size();
            int levelNodeCount = 0;
            double levelNodeSum = 0.0d;
            for (int i = 0; i < size; i++) {
                final var node = nodes.pop();
                levelNodeCount++;
                levelNodeSum += node.val;

                if (node.left != null) {
                    nodes.add(node.left);
                }

                if (node.right != null) {
                    nodes.add(node.right);
                }
            }

            averagePerLevel.add(levelNodeSum/(double) levelNodeCount);
        }

        return averagePerLevel;
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
