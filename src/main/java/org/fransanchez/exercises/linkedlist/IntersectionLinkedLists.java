package org.fransanchez.exercises.linkedlist;

import org.fransanchez.deprecated.linkedlist.ListNode;

// 160. Intersection of Two Linked Lists
// This is kind of fast/slow pointers
public class IntersectionLinkedLists {
    public ListNode getIntersectionNode(final ListNode headA, final ListNode headB) {
        if(headA == null || headB == null) {
            return null;
        }

        var a = headA;
        var b = headB;

        while(a != b){
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }

    public static void main(String[] args) {
        final var intersection = new ListNode(8, new ListNode(4, new ListNode(5)));

        // 4,1,8,4,5
        final var listA = new ListNode(4, new ListNode(1, intersection));

        // 5,6,1,8,4,5
        final var listB = new ListNode(5, new ListNode(6, new ListNode(1, intersection)));

        System.out.println(new IntersectionLinkedLists().getIntersectionNode(listA, listB).val);
    }
}
