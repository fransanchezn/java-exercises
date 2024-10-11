package org.fransanchez.designpattern.structural.composite;

public abstract class Product implements Box {
    protected final String title;
    protected final double price;

    public Product(final String title, final double price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public double price() {
        return price;
    }
}
