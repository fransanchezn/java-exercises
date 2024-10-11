package org.fransanchez.designpattern.creational.abstractfactory.products.concretes;

import org.fransanchez.designpattern.creational.abstractfactory.products.abstracts.Paragraph;

// Concrete product
public class HtmlParagraph extends Paragraph {

    public HtmlParagraph(final String text) {
        super(text);
    }

    @Override
    public String render() {
        return String.format("<p>%s</p>", text);
    }
}
