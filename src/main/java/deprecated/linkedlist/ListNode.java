package deprecated.linkedlist;

public class ListNode {
    int val;
    ListNode next;

    ListNode(final int val) {
        this.val = val;
    }

    ListNode(final int val, final ListNode next) {
        this.val = val;
        this.next = next;
    }

    ListNode(final int[] vals) {
        ListNode nextListNode = null;
        for (int i = vals.length - 1; i >= 0; i--) {
            nextListNode = new ListNode(vals[i], nextListNode);
        }
        this.val = nextListNode.val;
        this.next = nextListNode.next;
    }
}