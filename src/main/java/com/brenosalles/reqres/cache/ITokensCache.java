package com.brenosalles.reqres.cache;

import com.brenosalles.reqres.cache.exceptions.TokenNotFound;
import com.brenosalles.tokens.Token;
import com.brenosalles.users.User;

public interface ITokensCache {
    void addToken(Token token);

    Token getToken(User user) throws TokenNotFound;
}