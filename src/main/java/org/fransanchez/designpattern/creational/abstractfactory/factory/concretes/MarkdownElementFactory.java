package org.fransanchez.designpattern.creational.abstractfactory.factory.concretes;

import org.fransanchez.designpattern.creational.abstractfactory.factory.abstracts.ElementFactory;
import org.fransanchez.designpattern.creational.abstractfactory.products.abstracts.Element;
import org.fransanchez.designpattern.creational.abstractfactory.products.concretes.MarkdownHeading;
import org.fransanchez.designpattern.creational.abstractfactory.products.concretes.MarkdownLink;
import org.fransanchez.designpattern.creational.abstractfactory.products.concretes.MarkdownParagraph;

// Concrete factory
public class MarkdownElementFactory implements ElementFactory {

    @Override
    public Element createHeading(int level, String text) {
        return new MarkdownHeading(level, text);
    }

    @Override
    public Element createParagraph(String text) {
        return new MarkdownParagraph(text);
    }

    @Override
    public Element createLink(String text, String url) {
        return new MarkdownLink(text, url);
    }
}
