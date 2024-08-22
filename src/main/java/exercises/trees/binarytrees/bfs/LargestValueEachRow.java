package exercises.trees.binarytrees.bfs;

import exercises.trees.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// 515. Find Largest Value in Each Tree Row
public class LargestValueEachRow {
    public List<Integer> largestValues(final TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        final var queue = new LinkedList<TreeNode>();
        queue.offer(root);
        final var result = new ArrayList<Integer>();

        while(!queue.isEmpty()) {
            final var levelSize = queue.size();
            var levelMaxValue = Integer.MIN_VALUE;
            for (int i = 0; i < levelSize; i++) {
                final var node = queue.poll();

                levelMaxValue = Math.max(levelMaxValue, node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result.add(levelMaxValue);
        }

        return result;
    }

    public static void main(final String[] args) {
        final var root = TreeNode.of(new Integer[] { 1,3,2,5,3,null,9 });

        final var sut = new LargestValueEachRow();
        final var result = sut.largestValues(root);

        System.out.println(result);
    }
}
