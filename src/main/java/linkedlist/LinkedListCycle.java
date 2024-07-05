package linkedlist;

public class LinkedListCycle {

    public static void main(String[] args) {
        final var head = new ListNode(new int[]{ 3,2,0,-4 });
        System.out.println(hasCycle(head));
    }

    // Floyd's Cycle Detection
    public static boolean hasCycle(final ListNode head) {
        var slowPointer = head;
        var fastPointer = head;
        while (slowPointer != null && fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;

            if (slowPointer == fastPointer) {
                return true;
            }
        }

        return false;
    }
}