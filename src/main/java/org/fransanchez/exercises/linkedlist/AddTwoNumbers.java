package org.fransanchez.exercises.linkedlist;

import org.fransanchez.deprecated.linkedlist.ListNode;

// 2. Add Two Numbers
public class AddTwoNumbers {
    public ListNode addTwoNumbers(final ListNode l1, final ListNode l2) {
        var node1 = l1;
        var node2 = l2;

        ListNode result = new ListNode(0);
        ListNode curr = result;
        int carry = 0;
        while (node1 != null || node2 != null) {
            final var node1Val = node1 == null ? 0 : node1.val;
            final var node2Val = node2 == null ? 0 : node2.val;

            final var value = node1Val + node2Val + carry;
            carry = value / 10;

            curr.next = new ListNode(value % 10);
            curr = curr.next;

            node1 = node1 != null ? node1.next : null;
            node2 = node2 != null ? node2.next : null;
        }

        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        return result.next;
    }
}