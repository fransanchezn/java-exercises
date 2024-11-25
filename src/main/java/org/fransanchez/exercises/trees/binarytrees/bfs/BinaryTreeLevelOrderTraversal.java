package org.fransanchez.exercises.trees.binarytrees.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.fransanchez.exercises.trees.TreeNode;

// 102. Binary Tree Level Order Traversal
public class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(final TreeNode root) {
        final Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }

        final var levels = new ArrayList<List<Integer>>();

        while (!queue.isEmpty()) {
            final var levelSize = queue.size();

            final var level = new ArrayList<Integer>();
            for (int i = 0; i < levelSize; i++) {
                final var node = queue.poll();
                level.add(node.val);

                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            levels.add(level);
        }

        return levels;
    }


}
