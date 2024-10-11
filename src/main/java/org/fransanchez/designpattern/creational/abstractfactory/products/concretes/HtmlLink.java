package org.fransanchez.designpattern.creational.abstractfactory.products.concretes;

import org.fransanchez.designpattern.creational.abstractfactory.products.abstracts.Link;

// Concrete product
public class HtmlLink extends Link {

    public HtmlLink(final String text, final String url) {
        super(text, url);
    }

    @Override
    public String render() {
        return String.format("<a href=\"%s\">%s</a>", url, text);
    }
}
