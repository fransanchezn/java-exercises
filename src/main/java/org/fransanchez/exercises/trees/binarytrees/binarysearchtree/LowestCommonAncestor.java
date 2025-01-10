package org.fransanchez.exercises.trees.binarytrees.binarysearchtree;

import org.fransanchez.exercises.trees.TreeNode;

// 235. Lowest Common Ancestor of a Binary Search Tree
public class LowestCommonAncestor {

    public TreeNode lowestCommonAncestor(final TreeNode root, final TreeNode p, final TreeNode q) {
        return lowestCommonAncestorFinder(root, p, q);
    }

    private TreeNode lowestCommonAncestorFinder(final TreeNode node, final TreeNode p, final TreeNode q) {
        if (node == null) {
            return null;
        }

        final var pCompare = Integer.compare(node.val, p.val);
        final var qCompare = Integer.compare(node.val, q.val);

        if (pCompare != qCompare) {
            return node;
        }

        if (pCompare > 0) {
            return lowestCommonAncestorFinder(node.left, p, q);
        } else if (pCompare < 0) {
            return lowestCommonAncestorFinder(node.right, p, q);
        } else {
            return node;
        }
    }
}
