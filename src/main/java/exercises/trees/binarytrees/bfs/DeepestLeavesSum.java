package exercises.trees.binarytrees.bfs;

import exercises.trees.TreeNode;
import java.util.LinkedList;

// 1302. Deepest Leaves Sum
public class DeepestLeavesSum {
    public int deepestLeavesSum(final TreeNode root) {
        final var queue = new LinkedList<TreeNode>();
        queue.offer(root);

        var levelSum = 0;
        while (!queue.isEmpty()) {
            final var levelSize = queue.size();

            levelSum = 0;
            for (int i = 0; i < levelSize; i++) {
                final var node = queue.poll();

                levelSum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return levelSum;
    }

    public static void main(String[] args) {
        final var root = TreeNode.of(new Integer[] { 1,2,3,4,5,null,6,7,null,null,null,null,8 });

        final var sut = new DeepestLeavesSum();
        final var result = sut.deepestLeavesSum(root);

        System.out.println(result);
    }
}
