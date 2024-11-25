package org.fransanchez.exercises.trees.binarytrees.dfs;

import java.util.ArrayList;
import java.util.List;
import org.fransanchez.exercises.trees.TreeNode;

// 94. Binary Tree Inorder Traversal
// Is the tree is a BST (Binary Search Tree) this will return the ordered array.
// Building BST from array = O(n * log n)
// inorder traverse = O(n)
// populate + traverse (sort an array) = O(n * n * log n) = O(2n * log n) = O(n * log n)
public class BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(final TreeNode root) {
        final var nodes = new ArrayList<Integer>();
        inorder(root, nodes);
        return nodes;
    }

    private void inorder(final TreeNode root, final List<Integer> order) {
        if (root == null) {
            return;
        }

        inorder(root.left, order);
        order.add(root.val);
        inorder(root.right, order);
    }

    public static void main(String[] args) {
        final var root = TreeNode.of(new Integer[] { 4,3,6,2,null,5,7,null,null,null,null,null, null });
        System.out.println(new BinaryTreeInorderTraversal().inorderTraversal(root));
    }
}
