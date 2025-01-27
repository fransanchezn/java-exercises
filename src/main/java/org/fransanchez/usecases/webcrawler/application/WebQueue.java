package org.fransanchez.usecases.webcrawler.application;

public interface WebQueue {
    boolean add(final Web web);
    Web poll();
}
