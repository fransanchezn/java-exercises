package org.fransanchez.designpattern.creational.abstractfactory.products.abstracts;

// Abstract Product
public abstract class Link implements Element {
    protected String text;
    protected String url;

    protected Link(final String text, final String url) {
        this.text = text;
        this.url = url;
    }
}
