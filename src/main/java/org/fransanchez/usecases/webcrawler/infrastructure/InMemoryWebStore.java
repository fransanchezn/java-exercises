package org.fransanchez.usecases.webcrawler.infrastructure;

import org.fransanchez.usecases.webcrawler.application.WebStore;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class InMemoryWebStore implements WebStore {
    public final Set<String> store;
    public final ReadWriteLock lock;

    public InMemoryWebStore() {
        this.store = new HashSet<>();
        this.lock = new ReentrantReadWriteLock();
    }

    @Override
    public boolean add(final URI uri) {
        lock.writeLock().lock();
        try {
            return store.add(uri.toString());
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public Set<String> getAll() {
        lock.readLock().lock();
        try {
            return store;
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public boolean contains(final URI uri) {
        lock.readLock().lock();
        try {
            return store.contains(uri.toString());
        } finally {
            lock.readLock().unlock();
        }
    }
}
