package org.fransanchez.exercises.linkedlist;

import java.util.Objects;
import java.util.Stack;
import org.fransanchez.exercises.linkedlist.core.SinglyListNode;

// 234. Palindrome Linked List
public class PalindromeLinkedList {
    public boolean isPalindrome(final SinglyListNode<Integer> head) {
        final var stack = new Stack<Integer>();
        var curr = head;
        while(curr != null) {
            stack.push(curr.value);
            curr = curr.next;
        }
        curr = head;
        while(curr != null && Objects.equals(curr.value, stack.pop())) {
            curr = curr.next;
        }
        return curr == null;
    }

    public static void main(String[] args) {
        final var intersection = new SinglyListNode<>(1, new SinglyListNode<>(2, new SinglyListNode<>(2, new SinglyListNode<>(1))));
        System.out.println(new PalindromeLinkedList().isPalindrome(intersection));
    }
}
