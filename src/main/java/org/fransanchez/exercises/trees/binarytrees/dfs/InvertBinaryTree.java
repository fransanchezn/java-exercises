package org.fransanchez.exercises.trees.binarytrees.dfs;

import org.fransanchez.exercises.trees.TreeNode;

// 226. Invert Binary Tree
public class InvertBinaryTree {
    public TreeNode invertTree(final TreeNode root) {
        return dfs(root);
    }

    private TreeNode dfs(final TreeNode node) {
        if (node == null) {
            return null;
        }

        // Swap children
        final var tmp = node.left;
        node.left = node.right;
        node.right = tmp;

        // Recursive call in subtree
        dfs(node.left);
        dfs(node.right);

        return node;
    }
}
