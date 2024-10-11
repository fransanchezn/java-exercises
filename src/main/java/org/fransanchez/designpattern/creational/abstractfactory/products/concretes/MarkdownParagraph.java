package org.fransanchez.designpattern.creational.abstractfactory.products.concretes;

import org.fransanchez.designpattern.creational.abstractfactory.products.abstracts.Paragraph;

// Concrete product
public class MarkdownParagraph extends Paragraph {

    public MarkdownParagraph(final String text) {
        super(text);
    }

    @Override
    public String render() {
        return text;
    }
}
