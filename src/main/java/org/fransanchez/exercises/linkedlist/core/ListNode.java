package org.fransanchez.exercises.linkedlist.core;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(final int val) {
        this.val = val;
    }

    public ListNode(final int val, final ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode(final int[] vals) {
        ListNode nextListNode = null;
        for (int i = vals.length - 1; i >= 0; i--) {
            nextListNode = new ListNode(vals[i], nextListNode);
        }
        this.val = nextListNode.val;
        this.next = nextListNode.next;
    }
}
