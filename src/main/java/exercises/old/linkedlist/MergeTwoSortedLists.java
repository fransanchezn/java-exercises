package exercises.old.linkedlist;

public class MergeTwoSortedLists {
    public static void main(String[] args) {
        System.out.println(mergeTwoLists(new ListNode(new int[] {1,2,4}), new ListNode(new int[] {1,3,4})));
    }

    // Input: list1 = [1,2,4], list2 = [1,3,4]
    // Output: [1,1,2,3,4,4]
    public static ListNode mergeTwoLists(final ListNode list1, final ListNode list2) {
        var node1 = list1;
        var node2 = list2;

        var head = new ListNode(-1);
        var sorted = head;
        while (node1 != null && node2 != null) {
            if (node1.val < node2.val) {
                sorted.next= node1;
                node1 = node1.next;
            } else {
                sorted.next = node2;
                node2 = node2.next;
            }

            sorted = sorted.next;
        }

        sorted.next = node1 == null ? node2 : node1;

        return head.next;
    }
}
