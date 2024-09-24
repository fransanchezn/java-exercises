package org.fransanchez.exercises.trees.binarytrees.bfs;

import org.fransanchez.exercises.trees.TreeNode;
import java.util.LinkedList;

public class BreadthFirstSearchExample {

    public void printAllNodes(final TreeNode root) {
        final var queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            final var queueSize = queue.size();

            for (int i = 0; i < queueSize; i++) {
                final var node = queue.poll();
                System.out.println(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
    }

    public static void main(final String[] args) {
        final var root = TreeNode.of(new Integer[] { 1,2,3,4,5,6,7 });

        final var sut = new BreadthFirstSearchExample();
        sut.printAllNodes(root);
    }
}
