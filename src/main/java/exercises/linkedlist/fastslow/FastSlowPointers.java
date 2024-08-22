package exercises.linkedlist.fastslow;

import exercises.linkedlist.core.SinglyListNode;
import java.util.List;

public class FastSlowPointers {

    int getMiddle(final SinglyListNode<Integer> head) {
        var slow = head;
        var fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow.value;
    }

    public static void main(String[] args) {

        final var headTail = SinglyListNode.build(List.of(1,2,3,4,5));

        final var sut = new FastSlowPointers();
        final var result = sut.getMiddle(headTail.get(0));

        System.out.println(result);
    }
}
