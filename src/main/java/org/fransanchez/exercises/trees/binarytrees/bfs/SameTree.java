package org.fransanchez.exercises.trees.binarytrees.bfs;

import org.fransanchez.exercises.trees.TreeNode;

import java.util.LinkedList;

// 100. Same Tree
public class SameTree {
    public boolean isSameTree(final TreeNode p, final TreeNode q) {
        return bfs(p, q);
    }

    private boolean bfs(final TreeNode p, final TreeNode q) {
        final var pQueue = new LinkedList<TreeNode>();
        final var qQueue = new LinkedList<TreeNode>();

        pQueue.add(p);
        qQueue.add(q);

        while (!pQueue.isEmpty() && !qQueue.isEmpty()) {
            final var pNode = pQueue.pop();
            final var qNode = qQueue.poll();

            if (!isEqual(pNode, qNode)) {
                return false;
            }

            if (pNode != null) {
                pQueue.add(pNode.left);
                pQueue.add(pNode.right);
            }

            if (qNode != null) {
                qQueue.add(qNode.left);
                qQueue.add(qNode.right);
            }
        }

        return true;
    }

    private boolean isEqual(final TreeNode p, final TreeNode q) {
        return (p == null && q == null) || (p != null && q != null && p.val == q.val);
    }
}
