package org.fransanchez.exercises.trees.binarysearchtree;

import org.fransanchez.exercises.trees.TreeNode;

// Closest Binary Search Tree Value
public class ClosestBinarySearchTreeValue {
    private double difference = Integer.MAX_VALUE;
    private TreeNode closestNode = null;

    public int closestValue(final TreeNode root, final double target) {
        if (root == null) {
            return -1;
        }

        closestValueCalculation(root, target);
        return closestNode.val;
    }

    private void closestValueCalculation(final TreeNode root, final double target) {
        var currentDiff = root.val - target;
        var currentDiffAbs = Math.abs(currentDiff);
        if (closestNode == null || currentDiffAbs < difference) {
            difference = currentDiffAbs;
            closestNode = root;
        }

        if (currentDiff < 0) {
            closestValue(root.right, target);
        } else {
            closestValue(root.left, target);
        }
    }

    public static void main(String[] args) {
        final var root = TreeNode.of(new Integer[] { 4,2,5,1,3 });

        final var sut = new ClosestBinarySearchTreeValue();
        final var result = sut.closestValue(root, 3.714286d);

        System.out.println(result);
    }
}
