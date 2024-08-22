package exercises.linkedlist.core;

public class DoublyListNode<T> {
    public DoublyListNode<T> next;
    public DoublyListNode<T> prev;
    public T value;

    public DoublyListNode(final T value) {
        this.next = null;
        this.prev = null;
        this.value = value;
    }

    public static <T> void addNode(final DoublyListNode<T> prevNode, final  DoublyListNode<T> nodeToAdd) {
        nodeToAdd.next = prevNode.next;
        nodeToAdd.prev = prevNode;

        prevNode.next.prev = nodeToAdd;
        prevNode.next = nodeToAdd;
    }

    public static <T> void deleteNode(final DoublyListNode<T> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
