package com.brenosalles.reqres.api;

import com.brenosalles.tokens.InvalidTokenException;
import com.brenosalles.tokens.Token;
import com.brenosalles.users.InvalidUserException;
import com.brenosalles.users.User;

public interface IReqresAuthentication {
    Token register(User user, String password) throws InvalidUserException, InvalidTokenException;

    Token login(User user, String password) throws InvalidUserException, InvalidTokenException;
}