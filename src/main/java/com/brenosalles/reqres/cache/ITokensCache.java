package com.brenosalles.reqres.cache;

import com.brenosalles.reqres.cache.exceptions.InvalidTokenException;
import com.brenosalles.reqres.cache.exceptions.TokenNotFoundException;
import com.brenosalles.tokens.Token;
import com.brenosalles.users.User;

public interface ITokensCache {
    void addToken(Token token) throws InvalidTokenException;

    Token getToken(User user) throws TokenNotFoundException;
}