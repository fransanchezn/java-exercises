package org.fransanchez.designpattern.behavioral.observer;

public class ObserverMain {

    public static void main(final String[] args) {
        final var store = new Store(new NotificationService());
        store.notificationService().add(new CustomerEmailListener("fran@test.com"));
        store.notificationService().add(new CustomerMobileNotificationListener("412523512"));

        store.addProduct("Banana");
    }
}
