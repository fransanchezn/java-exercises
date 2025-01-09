package org.fransanchez.exercises.linkedlist;

import org.fransanchez.deprecated.linkedlist.ListNode;
import org.junit.jupiter.api.Test;

class AddTwoNumbersTest {

    final AddTwoNumbers sut = new AddTwoNumbers();

    @Test
    void case1() {
        final var l1 = new ListNode(5, new ListNode(6, new ListNode(4)));
        final var l2 = new ListNode(2, new ListNode(4, new ListNode(3, new ListNode(3))));
        sut.addTwoNumbers(l1, l2);
    }
}