package org.fransanchez.designpattern.behavioral.chainresposibility;

import java.util.HashMap;
import java.util.Map;

public class AuthorizationService {
    private final Map<String, String> users;

    public AuthorizationService() {
        this.users = new HashMap<>();

        // Initialize
        this.users.put("admin_username", "1234");
        this.users.put("user_username", "1111");
    }

    public boolean isValidUser(final String username) {
        return users.containsKey(username);
    }

    public boolean isValidPassword(final String username, final String password) {
        return users.get(username).equals(password);
    }
}
