package org.fransanchez.exercises.linkedlist;

import org.fransanchez.exercises.linkedlist.core.ListNode;

// 21. Merge Two Sorted Lists
public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(final ListNode list1, final ListNode list2) {
        ListNode head = new ListNode(-1);;
        ListNode tail = head;
        var current1 = list1;
        var current2 = list2;
        while (current1 != null && current2 != null) {
            if (current1.val < current2.val) {
                tail.next = current1;
                current1 = current1.next;
            } else {
                tail.next = current2;
                current2 = current2.next;
            }

            tail = tail.next;
        }

        tail.next = current1 != null ? current1 : current2;

        return head.next;
    }
}
