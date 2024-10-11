package org.fransanchez.designpattern.behavioral.chainresposibility;

public class ChainResponsibilityMain {
    public static void main(String[] args) {
        final var authService = new AuthorizationService();
        final var chain = new UsernameLoginHandler(authService)
                .withNext(new PasswordLoginHandler(authService));

        final var loginRequest = new LoginRequest("admin_username", "1234");
        System.out.println("Successful: " + chain.handle(loginRequest));
    }
}
