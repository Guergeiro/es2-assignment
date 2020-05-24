package com.brenosalles.reqres.api.stubs;

import com.brenosalles.reqres.api.IReqresAuthentication;
import com.brenosalles.tokens.InvalidTokenException;
import com.brenosalles.tokens.Token;
import com.brenosalles.tokens.TokenFactory;
import com.brenosalles.users.InvalidUserException;
import com.brenosalles.users.User;

public class ReqresAuthenticationStub implements IReqresAuthentication {
    @Override
    public Token register(User user, String password) throws InvalidUserException, InvalidTokenException {
        return TokenFactory.createToken(user, "this is a random token");
    }

    @Override
    public Token login(User user, String password) throws InvalidUserException, InvalidTokenException {
        return TokenFactory.createToken(user, "this is a random token");
    }
}