package exercises.trees.binarytrees.dfs;

import exercises.trees.TreeNode;

// 1026. Maximum Difference Between Node and Ancestor
public class MaximumDifferenceNodeAncestor {
    int result = 0;

    public int maxAncestorDiff(final TreeNode root) {
        if (root == null) {
            return 0;
        }

        result = 0;
        maxAncestorDiff(root, root.val, root.val);

        return result;
    }

    public void maxAncestorDiff(final TreeNode node, final int ancestorMaxValue, final int ancestorMinValue) {
        if (node == null) {
            return;
        }

        final var maxValue = Math.max(ancestorMaxValue, node.val);
        final var minValue = Math.min(ancestorMinValue, node.val);

        final var potentialResult = Math.max(Math.abs(ancestorMaxValue - node.val), Math.abs(ancestorMinValue - node.val));
        result = Math.max(result, potentialResult);

        maxAncestorDiff(node.left, maxValue, minValue);
        maxAncestorDiff(node.right, maxValue, minValue);
    }
}
