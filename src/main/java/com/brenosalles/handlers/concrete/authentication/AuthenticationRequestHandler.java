package com.brenosalles.handlers.concrete.authentication;

import com.brenosalles.handlers.AbstractHandler;
import com.brenosalles.reqres.api.IReqresAuthentication;
import com.brenosalles.tokens.InvalidTokenException;
import com.brenosalles.tokens.Token;
import com.brenosalles.users.InvalidUserException;
import com.brenosalles.users.User;

public class AuthenticationRequestHandler extends AbstractHandler {
    // Attributes
    private IReqresAuthentication apiAuthentication;

    // Constructor
    public AuthenticationRequestHandler(IReqresAuthentication apiAuthentication) {
        this.apiAuthentication = apiAuthentication;
    }

    @Override
    public Token register(User user, String password) {
        try {
            return apiAuthentication.register(user, password);
        } catch (InvalidUserException | InvalidTokenException e) {
            return super.register(user, password);
        }
    }

    @Override
    public Token login(User user, String password) {
        try {
            return apiAuthentication.login(user, password);
        } catch (InvalidUserException | InvalidTokenException e) {
            return super.login(user, password);
        }
    }
}