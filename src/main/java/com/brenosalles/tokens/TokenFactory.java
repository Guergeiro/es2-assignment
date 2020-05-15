package com.brenosalles.tokens;

import com.brenosalles.users.User;

public abstract class TokenFactory {
    public static Token createToken(User user, String token) {
        return new Token(user, token);
    }
}