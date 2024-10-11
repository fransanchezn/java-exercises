package org.fransanchez.designpattern.behavioral.chainresposibility;

public abstract class LoginHandler {
    protected LoginHandler next;

    public LoginHandler withNext(final LoginHandler handler) {
        this.next = handler;
        return this;
    }

    protected abstract boolean handle(final LoginRequest request);

    protected boolean handleNext(final LoginRequest request) {
        if (next == null) {
            return true;
        }

        return next.handle(request);
    }
}
