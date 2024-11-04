package org.fransanchez.exercises.linkedlist;

import org.fransanchez.exercises.linkedlist.core.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MergeTwoSortedListsTest {

    private final MergeTwoSortedLists sut = new MergeTwoSortedLists();

    @Test
    public void givenTwoSortedLinkedList_whenMergingThem_returnSortedList() {
        final var list1 = new ListNode(1, new ListNode(2, new ListNode(4, null)));
        final var list2 = new ListNode(1, new ListNode(3, new ListNode(4, null)));

        final var result = sut.mergeTwoLists(list1, list2);

        final var expected = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(4))))));
        assertEqualsLinkedList(expected, result);
    }

    private void assertEqualsLinkedList(final ListNode expected, final ListNode actual) {
        var expectedCurrent = expected;
        var actualCurrent = actual;
        while(expectedCurrent != null) {
            assertEquals(expectedCurrent.val, actualCurrent.val);
            expectedCurrent = expectedCurrent.next;
            actualCurrent = actualCurrent.next;
        }
    }

}