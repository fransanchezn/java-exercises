package org.fransanchez.designpattern.creational.abstractfactory.factory.concretes;

import org.fransanchez.designpattern.creational.abstractfactory.factory.abstracts.ElementFactory;
import org.fransanchez.designpattern.creational.abstractfactory.products.abstracts.Element;
import org.fransanchez.designpattern.creational.abstractfactory.products.concretes.HtmlHeading;
import org.fransanchez.designpattern.creational.abstractfactory.products.concretes.HtmlLink;
import org.fransanchez.designpattern.creational.abstractfactory.products.concretes.HtmlParagraph;

public class HtmlElementFactory implements ElementFactory {
    @Override
    public Element createHeading(final int level, final String text) {
        return new HtmlHeading(level, text);
    }

    @Override
    public Element createParagraph(final String text) {
        return new HtmlParagraph(text);
    }

    @Override
    public Element createLink(final String text, final String url) {
        return new HtmlLink(text, url);
    }
}
