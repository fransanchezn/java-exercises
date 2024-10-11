package org.fransanchez.designpattern.creational.abstractfactory.products.abstracts;

// Abstract Product
public abstract class Heading implements Element {
    protected int level;
    protected String text;

    protected Heading(final int level, final String text) {
        this.level = level;
        this.text = text;
    }
}
