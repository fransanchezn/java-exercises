package org.fransanchez.designpattern.structural.composite;

import java.util.List;

public class CompositeBox implements Box {
    private final List<Box> boxes;

    public CompositeBox(final List<Box> boxes) {
        this.boxes = boxes;
    }

    @Override
    public double price() {
        return boxes.stream().mapToDouble(Box::price).sum();
    }
}
