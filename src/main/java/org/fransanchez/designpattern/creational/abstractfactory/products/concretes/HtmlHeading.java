package org.fransanchez.designpattern.creational.abstractfactory.products.concretes;

import org.fransanchez.designpattern.creational.abstractfactory.products.abstracts.Heading;

public class HtmlHeading extends Heading {

    public HtmlHeading(final int level, final String text) {
        super(level, text);
    }

    @Override
    public String render() {
        return String.format("<h%d>%s</h%d>",level, text, level);
    }
}
