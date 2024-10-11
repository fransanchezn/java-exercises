package org.fransanchez.designpattern.structural.decorator.datasource;

public interface DataSource {
    void writeData(final String data);
    String readData();
}
