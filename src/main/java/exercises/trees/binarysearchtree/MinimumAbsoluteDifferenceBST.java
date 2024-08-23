package exercises.trees.binarysearchtree;

import exercises.trees.TreeNode;
import java.util.ArrayList;
import java.util.List;

// 530. Minimum Absolute Difference in BST
public class MinimumAbsoluteDifferenceBST {
    public int getMinimumDifference(TreeNode root) {
        final var values = new ArrayList<Integer>();
        dfs(root, values);

        var ans = Integer.MAX_VALUE;
        for (int i = 1; i < values.size(); i++) {
            ans = Math.min(ans, values.get(i) - values.get(i - 1));
        }

        return ans;
    }

    // inorder DFS on a BST = sorted array
    public void dfs(final TreeNode node, final List<Integer> values) {
        if (node == null) {
            return;
        }

        dfs(node.left, values);
        values.add(node.val);
        dfs(node.right, values);
    }

    public static void main(String[] args) {
        final var root = TreeNode.of(new Integer[] { 4,2,6,1,3 });

        final var sut = new MinimumAbsoluteDifferenceBST();
        final var result = sut.getMinimumDifference(root);

        System.out.println(result);
    }
}
