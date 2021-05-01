package linkedlist;


public class MergeList {
    public static void main(String[] args) {

        final var four = new ListNode(4);
        final var two = new ListNode(2, four);


        final var five = new ListNode(5);
        final var three = new ListNode(3, five);
        final var one = new ListNode(1, three);

        final var result = mergeTwoLists(one, two);

        var current = result;
        while(current.next != null) {
            System.out.println(current.val);
            current = current.next;
        }

        System.out.println(current.val);
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        ListNode merged;
        ListNode next1;
        ListNode next2;

        if (l1.val < l2.val) {
            merged = new ListNode(l1.val);
            next1 = l1.next;
            next2 = l2;
        } else {
            merged = new ListNode(l2.val);
            next1 = l1;
            next2 = l2.next;
        }

        var current = merged;
        while(next1 != null || next2 != null) {
            if (next1 == null) {
                current.next = new ListNode(next2.val);
                next2 = next2.next;
            } else if (next2 == null) {
                current.next = new ListNode(next1.val);
                next1 = next1.next;
            } else if (next1.val < next2.val) {
                current.next = new ListNode(next1.val);
                next1 = next1.next;
            } else {
                current.next = new ListNode(next2.val);
                next2 = next2.next;
            }

            current = current.next;
        }

        return merged;
    }
}
