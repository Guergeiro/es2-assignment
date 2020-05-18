package com.brenosalles.handlers.concrete.authentication;

import com.brenosalles.handlers.AbstractHandler;
import com.brenosalles.tokens.Token;
import com.brenosalles.users.User;

public class AuthenticationValidatorHandler extends AbstractHandler {
    @Override
    public Token register(User user, String password) {
        if (user == null) {
            return null;
        }
        if (password == null) {
            return null;
        }
        if (password.length() < 8) {
            return null;
        }
        if (password.length() > 128) {
            return null;
        }
        return super.register(user, password);
    }

    @Override
    public Token login(User user, String password) {
        if (user == null) {
            return null;
        }
        if (password == null) {
            return null;
        }
        if (password.length() < 8) {
            return null;
        }
        if (password.length() > 128) {
            return null;
        }
        return super.register(user, password);
    }
}