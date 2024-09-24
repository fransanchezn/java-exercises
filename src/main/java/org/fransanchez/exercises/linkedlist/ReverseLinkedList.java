package org.fransanchez.exercises.linkedlist;

import org.fransanchez.exercises.linkedlist.core.SinglyListNode;
import java.util.List;

// 206. Reverse Linked List
public class ReverseLinkedList {
    public SinglyListNode<Integer> reverseList(final SinglyListNode<Integer> head) {
        SinglyListNode<Integer> previous = null;
        var current = head;
        while(current != null ) {
            var next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        return previous;
    }

    public static void main(String[] args) {
        final var sut = new ReverseLinkedList();


        final var headTail = SinglyListNode.build(List.of(1,2,3,4));
        final var head = sut.reverseList(headTail.get(0));


        SinglyListNode.printList(head);
    }
}
