package exercises.linkedlist;

// 707. Design Linked List
public class MyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int get(final int index) {
        if (index < 0 || index > size - 1) {
            return -1;
        }

        final var node = getNode(index);
        return node.val;
    }

    public void addAtHead(final int val) {
        var newNode = new Node(val);
        newNode.next = head;
        head = newNode;
        if (size == 0) {
            tail = head;
        }
        size++;
    }

    public void addAtTail(final int val) {
        if (size == 0) {
            addAtHead(val);
        } else {
            var newNode = new Node(val);
            tail.next = newNode;
            tail = newNode;
            size++;
        }
    }

    public void addAtIndex(final int index, final int val) {
        if (index < 0 || index > size) {
            return;
        } else if (index == 0) {
            addAtHead(val);
        } else if (index == size) {
            addAtTail(val);
        } else {
            var newNode = new Node(val);
            var node = getNode(index - 1);
            newNode.next = node.next;
            node.next = newNode;
            size++;
        }
    }

    public void deleteAtIndex(final int index) {
        if (index < 0 || index > size - 1) {
            return;
        } else if (index == 0) {
            head = head.next;
        } else if (index == size - 1) {
            var prevNode = getNode(index - 1);
            prevNode.next = null;
            tail = prevNode;
        } else {
            var prevNode = getNode(index - 1);
            prevNode.next = prevNode.next.next;
        }

        size--;
    }

    private Node getNode(final int index) {
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node;
    }

    public static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtTail(1); // 1
        myLinkedList.addAtTail(3); // 1 -> 3
        myLinkedList.addAtIndex(1, 2); // 1 -> 2 -> 3
        System.out.println("List[0] " + myLinkedList.get(0));
        System.out.println("List[1] " + myLinkedList.get(1));
        System.out.println("List[2] " + myLinkedList.get(2));
        System.out.println();
        myLinkedList.get(1);
        System.out.println("List[1] " + myLinkedList.get(1));
        myLinkedList.deleteAtIndex(1);
        System.out.println("List[0] " + myLinkedList.get(0));
        System.out.println("List[1] " + myLinkedList.get(1));
        System.out.println("List[2] " + myLinkedList.get(2));
        myLinkedList.get(2);
    }
}
