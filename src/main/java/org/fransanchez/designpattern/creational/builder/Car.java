package org.fransanchez.designpattern.creational.builder;

public record Car(
        String type,
        String brand,
        CarEngine engine
) { }
