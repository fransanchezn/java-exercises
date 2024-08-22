package exercises.trees;

import java.util.LinkedList;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(final int val) {
        this.val = val;
    }

    public static TreeNode of(final Integer[] values) {
        final var root = new TreeNode(values[0]);
        final var queue = new LinkedList<TreeNode>();
        queue.add(root);

        var i = 1;
        while (!queue.isEmpty()) {
            final var node = queue.poll();

            if (i < values.length && values[i] != null) {
                node.left = new TreeNode(values[i]);
                queue.offer(node.left);
            }
            i++;

            if (i < values.length && values[i] != null) {
                node.right = new TreeNode(values[i]);
                queue.offer(node.right);
            }
            i++;
        }

        return root;
    }
}