package org.fransanchez.deprecated.tree.leet;

public class LeetTreeNode {
    int val;
    LeetTreeNode left;
    LeetTreeNode right;

    LeetTreeNode() {}
    LeetTreeNode(int val) { this.val = val; }
    LeetTreeNode(int val, LeetTreeNode left, LeetTreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
