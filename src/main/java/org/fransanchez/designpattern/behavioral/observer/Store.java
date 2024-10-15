package org.fransanchez.designpattern.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private final NotificationService notificationService;
    private final List<String> products;

    public Store(final NotificationService notificationService) {
        this.notificationService = notificationService;
        this.products = new ArrayList<>();
    }

    public boolean addProduct(final String product) {
        products.add(product);
        notificationService.notifyListeners(product);
        return true;
    }

    public NotificationService notificationService() {
        return notificationService;
    }
}
