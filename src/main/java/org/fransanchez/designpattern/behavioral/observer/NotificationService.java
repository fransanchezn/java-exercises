package org.fransanchez.designpattern.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    private final List<EventListener> eventListeners;

    public NotificationService() {
        this.eventListeners = new ArrayList<>();
    }

    public boolean add(final EventListener eventListener) {
        return eventListeners.add(eventListener);
    }

    public boolean remove(final EventListener eventListener) {
        return eventListeners.remove(eventListener);
    }

    public void notifyListeners(final String product) {
        eventListeners.forEach(i -> i.onEvent(product));
    }
}
