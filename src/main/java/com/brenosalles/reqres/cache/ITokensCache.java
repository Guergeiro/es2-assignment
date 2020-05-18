package com.brenosalles.reqres.cache;

import com.brenosalles.reqres.cache.exceptions.InvalidToken;
import com.brenosalles.reqres.cache.exceptions.TokenNotFound;
import com.brenosalles.tokens.Token;
import com.brenosalles.users.User;

public interface ITokensCache {
    void addToken(Token token) throws InvalidToken;

    Token getToken(User user) throws TokenNotFound;
}