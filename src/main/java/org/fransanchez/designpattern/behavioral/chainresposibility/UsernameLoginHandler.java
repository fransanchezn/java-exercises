package org.fransanchez.designpattern.behavioral.chainresposibility;

public class UsernameLoginHandler extends LoginHandler {
    private final AuthorizationService authorizationService;

    protected UsernameLoginHandler(final AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    protected boolean handle(final LoginRequest request) {
        if (!authorizationService.isValidUser(request.username())) {
            return false;
        }

        return handleNext(request);
    }
}
