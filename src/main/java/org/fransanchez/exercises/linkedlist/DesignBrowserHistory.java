package org.fransanchez.exercises.linkedlist;

// 1472. Design Browser History
public class DesignBrowserHistory {
    private Node current;

    public DesignBrowserHistory(final String homepage) {
        current = new Node(null, homepage, null);
    }

    public void visit(final String url) {
        final var node = new Node(current, url, null);
        current.next = node;
        current = node;
    }

    public String back(final int steps) {
        var counter = steps;
        while (current.previous != null && counter > 0) {
            current = current.previous;
            counter--;
        }

        return current.value;
    }

    public String forward(final int steps) {
        var counter = steps;
        while (current.next != null && counter > 0) {
            current = current.next;
            counter--;
        }

        return current.value;
    }

    private static class Node {
        Node previous;
        String value;
        Node next;

        public Node(final Node previous, final String value, final Node next) {
            this.previous = previous;
            this.value = value;
            this.next = next;
        }
    }
}
