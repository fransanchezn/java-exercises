package org.fransanchez.designpattern.creational.abstractfactory.products.abstracts;

public abstract class Paragraph implements Element {
    protected String text;

    protected Paragraph(final String text) {
        this.text = text;
    }
}
