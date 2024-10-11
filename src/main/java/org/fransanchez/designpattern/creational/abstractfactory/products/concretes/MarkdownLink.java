package org.fransanchez.designpattern.creational.abstractfactory.products.concretes;

import org.fransanchez.designpattern.creational.abstractfactory.products.abstracts.Link;

// Concrete product
public class MarkdownLink extends Link {

    public MarkdownLink(final String text, final String url) {
        super(text, url);
    }

    @Override
    public String render() {
        return String.format("[%s](%s)", text, url);
    }
}
