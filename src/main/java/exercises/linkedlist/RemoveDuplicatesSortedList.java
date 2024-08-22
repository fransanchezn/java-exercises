package exercises.linkedlist;

import exercises.linkedlist.core.SinglyListNode;
import java.util.List;
import java.util.Objects;

public class RemoveDuplicatesSortedList {
    public SinglyListNode<Integer> deleteDuplicates(final SinglyListNode<Integer> head) {
        var current = head;
        while (current != null && current.next != null) {
            if (Objects.equals(current.next.value, current.value)) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        final var sut = new RemoveDuplicatesSortedList();


        final var headTail = SinglyListNode.build(List.of(1,2,2,3,3,3,4,5,5));
        final var head = sut.deleteDuplicates(headTail.get(0));


        SinglyListNode.printList(head);
    }
}
