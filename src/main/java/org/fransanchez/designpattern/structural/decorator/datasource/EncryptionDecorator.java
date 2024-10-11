package org.fransanchez.designpattern.structural.decorator.datasource;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptionDecorator extends DataSourceDecorator {

    public EncryptionDecorator(final DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public String readData() {
        final var data = super.readData();
        return decode(data);
    }

    @Override
    public void writeData(final String data) {
        final var encoded = encode(data);
        super.writeData(encoded);
    }

    private String decode(final String encoded) {
        return new String(Base64.getDecoder().decode(encoded));
    }

    private String encode(final String plain) {
        return Base64.getEncoder().encodeToString(plain.getBytes(StandardCharsets.UTF_8));
    }
}
