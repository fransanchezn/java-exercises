package linkedlist;

public class ReverseList {
    public static void main(String[] args) {
        final var five = new ListNode(5);
        final var four = new ListNode(4, five);
        final var three = new ListNode(3, four);
        final var two = new ListNode(2, three);
        final var one = new ListNode(1, two);

        final var result = reverseList(one);

        var current = result;
        while(current.next != null) {
            System.out.println(current.val);
            current = current.next;
        }

        System.out.println(current.val);
    }

    public static ListNode reverseList(ListNode head) {

        if (head == null) {
            return null;
        }

        ListNode newHead = new ListNode(head.val, null);
        var current = head;
        while(current.next != null) {
            var next = current.next;
            newHead = new ListNode(next.val, newHead);

            current = next;
        }

        return newHead;
    }
}
