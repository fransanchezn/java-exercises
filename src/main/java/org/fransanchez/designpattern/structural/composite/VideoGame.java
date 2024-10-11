package org.fransanchez.designpattern.structural.composite;

public class VideoGame extends Product {
    private final double tax;

    public VideoGame(final String title, final double price, final double tax) {
        super(title, price);
        this.tax = tax;
    }

    @Override
    public double price() {
        return super.price() + tax;
    }
}
