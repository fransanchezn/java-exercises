package org.fransanchez.exercises.linkedlist.core;

import java.util.List;

public class SinglyListNode<T> {
    public SinglyListNode<T> next;
    public T value;

    public SinglyListNode(final T value) {
        this.next = null;
        this.value = value;
    }

    public SinglyListNode(final T value, final SinglyListNode<T> next) {
        this.next = next;
        this.value = value;
    }

    public static <T> void addNode(final SinglyListNode<T> prevNode, final  SinglyListNode<T> nodeToAdd) {
        nodeToAdd.next = prevNode.next;
        prevNode.next = nodeToAdd;
    }

    public static <T> void deleteNode(final SinglyListNode<T> prevNode) {
        prevNode.next = prevNode.next.next;
    }

    public static <T> List<SinglyListNode<T>> build(final List<T> input) {
        final var head = new SinglyListNode<T>(null);
        var tail = head;

        for (T v : input) {
            final var node = new SinglyListNode<>(v);
            SinglyListNode.addNode(tail, node);
            tail = node;
        }


        return List.of(head.next, tail);
    }

    public static <T> void printList(final SinglyListNode<T> head) {
        var node = head;
        while (node != null) {
            if (node.next != null) {
                System.out.print(node.value + " -> ");
            } else {
                System.out.print(node.value);
            }
            node = node.next;
        }
        System.out.println();
    }
}
