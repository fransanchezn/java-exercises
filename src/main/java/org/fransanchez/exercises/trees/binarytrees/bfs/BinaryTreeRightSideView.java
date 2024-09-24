package org.fransanchez.exercises.trees.binarytrees.bfs;

import org.fransanchez.exercises.trees.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// 199. Binary Tree Right Side View
public class BinaryTreeRightSideView {

    public List<Integer> rightSideView(final TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        final var queue = new LinkedList<TreeNode>();
        queue.offer(root);

        final var result = new ArrayList<Integer>();
        while(!queue.isEmpty()) {
            final var levelSize = queue.size();

            TreeNode node = null;
            for (int i = 0; i < levelSize; i++) {
                node = queue.poll();

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result.add(node.val);
        }

        return result;
    }

    public static void main(String[] args) {
        final var root = TreeNode.of(new Integer[] { 1,2,3,null,5,null,4 });

        final var sut = new BinaryTreeRightSideView();
        final var result = sut.rightSideView(root);

        System.out.println(result);
    }
}
