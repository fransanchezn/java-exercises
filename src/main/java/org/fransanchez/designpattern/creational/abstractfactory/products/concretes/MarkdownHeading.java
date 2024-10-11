package org.fransanchez.designpattern.creational.abstractfactory.products.concretes;

import org.fransanchez.designpattern.creational.abstractfactory.products.abstracts.Heading;

public class MarkdownHeading extends Heading {

    public MarkdownHeading(final int level, final String text) {
        super(level, text);
    }

    @Override
    public String render() {
        return "#".repeat(Math.max(0, level)) + " " + text;
    }
}
