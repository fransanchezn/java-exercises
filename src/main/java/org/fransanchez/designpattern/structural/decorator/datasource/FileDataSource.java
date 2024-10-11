package org.fransanchez.designpattern.structural.decorator.datasource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileDataSource implements DataSource {
    private final Path filePath;

    public FileDataSource(final String filePath) {
        this.filePath = Path.of(filePath);
    }

    @Override
    public void writeData(final String data) {
        try {
            Files.writeString(filePath, data);
        } catch (final IOException e) {
            throw new RuntimeException("Cannot write into file: " + filePath, e);
        }
    }

    @Override
    public String readData() {
        final var sb = new StringBuilder();
        try {
           return Files.readString(filePath);
        } catch (final IOException e) {
            throw new RuntimeException("Cannot read from file: " + filePath, e);
        }
    }
}
