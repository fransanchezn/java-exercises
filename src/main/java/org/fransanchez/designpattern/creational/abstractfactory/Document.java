package org.fransanchez.designpattern.creational.abstractfactory;

import java.util.ArrayList;
import java.util.List;
import org.fransanchez.designpattern.creational.abstractfactory.factory.abstracts.ElementFactory;
import org.fransanchez.designpattern.creational.abstractfactory.factory.concretes.HtmlElementFactory;
import org.fransanchez.designpattern.creational.abstractfactory.factory.concretes.MarkdownElementFactory;
import org.fransanchez.designpattern.creational.abstractfactory.products.abstracts.Element;

public class Document {
    private final ElementFactory factory;
    private final List<Element> elementList;

    private Document(final ElementFactory factory) {
        this.factory = factory;
        elementList = new ArrayList<>();
    }

    public static Document createHtmlDocument() {
        return new Document(new HtmlElementFactory());
    }

    public static Document createMarkdownDocument() {
        return new Document(new MarkdownElementFactory());
    }

    public void addHeading(final int level, final String text) {
        elementList.add(factory.createHeading(level, text));
    }

    public void addParagraph(final String text) {
        elementList.add(factory.createParagraph(text));
    }

    public void addLink(final String text, final String url) {
        elementList.add(factory.createLink(text, url));
    }

    public void render() {
        elementList.forEach(i -> System.out.println(i.render()));
    }

    public static void main(String[] args) {
        final var htmlDoc = Document.createHtmlDocument();
        htmlDoc.addHeading(1, "Heading 1");
        htmlDoc.addParagraph("Paragraph 1");
        htmlDoc.addLink("test", "https://test.com");
        htmlDoc.render();

        System.out.println();
        System.out.println("----------------");
        System.out.println();

        final var mdDoc = Document.createMarkdownDocument();
        mdDoc.addHeading(1, "Heading 1");
        mdDoc.addParagraph("Paragraph 1");
        mdDoc.addLink("test", "https://test.com");
        mdDoc.render();
    }
}
