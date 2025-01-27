package org.fransanchez.usecases.webcrawler.application;

import java.net.URI;
import java.util.Set;

public interface WebStore {
    boolean add(final URI uri);
    Set<String> getAll();
    boolean contains(final URI uri);
}
