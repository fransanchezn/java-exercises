package org.fransanchez.designpattern.behavioral.chainresposibility;

public class PasswordLoginHandler extends LoginHandler {
    private final AuthorizationService authorizationService;

    protected PasswordLoginHandler(final AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    protected boolean handle(final LoginRequest request) {
        if (!authorizationService.isValidPassword(request.username(), request.password())) {
            return false;
        }

        return handleNext(request);
    }
}
