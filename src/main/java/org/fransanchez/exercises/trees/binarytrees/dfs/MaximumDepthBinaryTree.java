package org.fransanchez.exercises.trees.binarytrees.dfs;

import org.fransanchez.exercises.trees.TreeNode;

// 104. Maximum Depth of Binary Tree
public class MaximumDepthBinaryTree {
    public int maxDepth(final TreeNode root) {
        return dfs(root);
    }

    private int dfs(final TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(dfs(root.left), dfs(root.right));
    }
}
