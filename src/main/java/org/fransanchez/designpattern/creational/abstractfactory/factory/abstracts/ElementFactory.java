package org.fransanchez.designpattern.creational.abstractfactory.factory.abstracts;

import org.fransanchez.designpattern.creational.abstractfactory.products.abstracts.Element;

public interface ElementFactory {
    Element createHeading(final int level, final String text);
    Element createParagraph(final String text);
    Element createLink(final String text, final String url);
}
