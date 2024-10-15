package org.fransanchez.designpattern.behavioral.observer;

public class CustomerMobileNotificationListener implements EventListener {
    private final String phoneNumber;

    public CustomerMobileNotificationListener(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void onEvent(final String product) {
        System.out.println("Sending mobile notification to: " + phoneNumber + " for product: " + product);
    }
}
