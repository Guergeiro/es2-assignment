package com.brenosalles.handlers.concrete;

import com.brenosalles.handlers.AbstractHandler;
import com.brenosalles.reqres.api.IReqresAuthentication;
import com.brenosalles.tokens.Token;
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
        Token token = apiAuthentication.register(user, password);
        if (token == null) {
            return super.register(user, password);
        }
        return token;
    }

    @Override
    public Token login(User user, String password) {
        Token token = apiAuthentication.login(user, password);
        if (token == null) {
            return super.login(user, password);
        }
        return token;
    }
}