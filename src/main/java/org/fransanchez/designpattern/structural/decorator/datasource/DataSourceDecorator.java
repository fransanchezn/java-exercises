package org.fransanchez.designpattern.structural.decorator.datasource;

public abstract class DataSourceDecorator implements DataSource {
    private final DataSource dataSource;

    public DataSourceDecorator(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void writeData(final String data) {
        dataSource.writeData(data);
    }

    @Override
    public String readData() {
        return dataSource.readData();
    }
}
