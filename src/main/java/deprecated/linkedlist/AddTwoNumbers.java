package deprecated.linkedlist;

import java.math.BigInteger;

public class AddTwoNumbers {
    public static void main(String[] args) {

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        var first = l1.val + "";
        var node = l1;
        while(node.next != null) {
            first = node.next.val + first;
            node = node.next;
        }

        var second = l2.val + "";
        node = l2;
        while(node.next != null) {
            second = node.next.val + second;
            node = node.next;
        }

        final var sumString = new BigInteger(first).add(new BigInteger(second)).toString();

        ListNode prevNode = null;
        for (int i = 0; i < sumString.length(); i++) {
            prevNode = new ListNode(Integer.parseInt(sumString.charAt(i) + ""), prevNode);
        }

        return prevNode;
    }
}
