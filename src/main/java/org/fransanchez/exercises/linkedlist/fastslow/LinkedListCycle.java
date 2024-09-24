package org.fransanchez.exercises.linkedlist.fastslow;

import org.fransanchez.exercises.linkedlist.core.SinglyListNode;
import java.util.List;

// 141. Linked List Cycle
public class LinkedListCycle {

    public boolean hasCycle(final SinglyListNode<Integer> head) {
        var slow = head;
        var fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        final var headTail = SinglyListNode.build(List.of(3,2,0,-4));

        // Build cycle
        final var tail = headTail.get(1);
        tail.next = headTail.get(0).next;

        final var sut = new LinkedListCycle();
        final var result = sut.hasCycle(headTail.get(0));

        System.out.println(result);
    }
}
