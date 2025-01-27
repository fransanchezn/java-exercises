package org.fransanchez.usecases.webcrawler.infrastructure;

import org.fransanchez.usecases.webcrawler.application.Web;
import org.fransanchez.usecases.webcrawler.application.WebQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class InMemWebQueue implements WebQueue {
    private final LinkedBlockingQueue<Web> queue;

    public InMemWebQueue(final int capacity) {
        this.queue = new LinkedBlockingQueue<>(capacity);
    }

    @Override
    public boolean add(Web web) {
        try {
            return queue.offer(web, 1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Web poll() {
        try {
            return queue.poll(4, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
