package org.fransanchez.exercises.graphs.adjmatrix;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<>();
    }

    public Node(final int val) {
        this.val = val;
        this.neighbors = new ArrayList<>();
    }

    public Node(final int val, final List<Node> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }
}
