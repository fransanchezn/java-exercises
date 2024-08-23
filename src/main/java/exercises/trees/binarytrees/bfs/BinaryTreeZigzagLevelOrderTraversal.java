package exercises.trees.binarytrees.bfs;

import exercises.trees.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// 103. Binary Tree Zigzag Level Order Traversal
public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(final TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        final var queue = new LinkedList<TreeNode>();
        queue.offer(root);
        var level = 0;
        final var result = new ArrayList<List<Integer>>();

        while (!queue.isEmpty()) {
            final var levelSize = queue.size();

            final var levelNodes = new ArrayList<Integer>();
            for (int i = 0; i < levelSize; i++) {
                final var direction = level % 2;

                var node = (TreeNode )null;
                if (direction == 1) {
                    node = queue.pollLast();
                } else {
                    node = queue.pollFirst();
                }

                levelNodes.add(node.val);

                if (direction == 1) {
                    if (node.right != null) {
                        queue.push(node.right);
                    }

                    if (node.left != null) {
                        queue.push(node.left);
                    }

                } else {
                    if (node.left != null) {
                        queue.offer(node.left);
                    }

                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }

            result.add(levelNodes);
            level++;
        }

        return result;
    }

    public static void main(String[] args) {
        final var root = TreeNode.of(new Integer[] { 1,2,3,4,null,null,5 });

        final var sut = new BinaryTreeZigzagLevelOrderTraversal();
        final var result = sut.zigzagLevelOrder(root);

        System.out.println(result);

        final var queue = new LinkedList<Integer>();

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        System.out.println(queue);
    }
}
