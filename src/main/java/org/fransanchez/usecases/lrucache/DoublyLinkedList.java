package org.fransanchez.usecases.lrucache;

public class DoublyLinkedList<V> {
    private final Node<V> head;
    private final Node<V> tail;

    public DoublyLinkedList() {
        head = new Node<>(null);
        tail = new Node<>(null);

        head.next = tail;
        tail.previous = head;
    }

    public synchronized Node<V> addToHead(final V value) {
        final var newNode = new Node<>(value);
        final var nextNode = head.next;
        head.next = newNode;
        nextNode.previous = newNode;

        newNode.next = nextNode;
        newNode.previous = head;

        return newNode;
    }

    public synchronized Node<V> remove(final Node<V> nodeToRemove) {
        final var previous = nodeToRemove.previous;
        final var next = nodeToRemove.next;

        previous.next = next;
        next.previous = previous;

        return nodeToRemove;
    }

    public synchronized Node<V> removeFromTail() {
        return remove(tail.previous);
    }

    public static class Node<V> {
        public final V value;
        public Node<V> next;
        public Node<V> previous;

        public Node(final V value) {
            this.value = value;
        }
    }
}
