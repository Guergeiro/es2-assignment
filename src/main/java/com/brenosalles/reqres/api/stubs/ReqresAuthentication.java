package com.brenosalles.reqres.api.stubs;

import com.brenosalles.reqres.api.IReqresAuthentication;
import com.brenosalles.tokens.Token;
import com.brenosalles.tokens.TokenFactory;
import com.brenosalles.users.User;

public class ReqresAuthentication implements IReqresAuthentication {
    @Override
    public Token register(User user, String password) {
        if (user == null) {
            return null;
        }
        if (user.getEmail() == null) {
            return null;
        }
        if (password == null) {
            return null;
        }
        return TokenFactory.createToken(user, "this is a random token");
    }

    @Override
    public Token login(User user, String password) {
        if (user == null) {
            return null;
        }
        if (user.getEmail() == null) {
            return null;
        }
        if (password == null) {
            return null;
        }
        return TokenFactory.createToken(user, "this is a random token");
    }

}