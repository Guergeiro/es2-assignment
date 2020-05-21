package com.brenosalles.tokens;

import com.brenosalles.users.InvalidUserException;
import com.brenosalles.users.User;

public abstract class TokenFactory {
    public static Token createToken(User user, String token) throws InvalidUserException, InvalidTokenException {
        if (validateUser(user) == false) {
            throw new InvalidUserException();
        }
        if (validateToken(token) == false) {
            throw new InvalidTokenException();
        }
        return new Token(user, token);
    }

    private static Boolean validateUser(User user) {
        return user != null;
    }

    private static Boolean validateToken(String token) {
        return token != null;
    }
}