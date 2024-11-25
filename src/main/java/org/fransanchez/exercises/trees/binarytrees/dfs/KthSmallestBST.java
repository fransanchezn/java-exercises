package org.fransanchez.exercises.trees.binarytrees.dfs;

import java.util.ArrayList;
import java.util.List;
import org.fransanchez.exercises.trees.TreeNode;

public class KthSmallestBST {
    public int kthSmallest(final TreeNode root, final int k) {
        final var order = new ArrayList<Integer>();
        dfs(root, order);
        return order.get(k - 1);
    }

    private void dfs(final TreeNode node, final List<Integer> order) {
        if (node == null) {
            return;
        }

        dfs(node.left, order);
        order.add(node.val);
        dfs(node.right, order);
    }
}
