package org.fransanchez.designpattern.creational.abstractfactory.products.abstracts;

public abstract class Link implements Element {
    protected String text;
    protected String url;

    protected Link(final String text, final String url) {
        this.text = text;
        this.url = url;
    }
}
