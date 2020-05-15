package com.brenosalles.tokens;

import com.brenosalles.users.User;

public class Token {
    // Attributes
    private User user;
    private String token;

    // Constructor
    protected Token(User user, String token) {
        this.user = user;
        this.token = token;
    }

    // Getters
    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }
}