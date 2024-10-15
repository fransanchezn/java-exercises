package org.fransanchez.designpattern.behavioral.observer;

public class CustomerEmailListener implements EventListener {
    private final String email;

    public CustomerEmailListener(final String email) {
        this.email = email;
    }

    @Override
    public void onEvent(final String product) {
        System.out.println("Sending email to: " + email + " for product: " + product);
    }
}
