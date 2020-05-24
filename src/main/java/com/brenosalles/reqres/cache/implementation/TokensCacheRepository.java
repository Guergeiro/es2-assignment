package com.brenosalles.reqres.cache.implementation;

import java.util.ArrayList;

import com.brenosalles.reqres.cache.ITokensCache;
import com.brenosalles.reqres.cache.exceptions.InvalidTokenException;
import com.brenosalles.reqres.cache.exceptions.TokenNotFoundException;
import com.brenosalles.tokens.Token;
import com.brenosalles.users.User;

public class TokensCacheRepository implements ITokensCache {
    private ArrayList<Token> tokens = new ArrayList<Token>();

    @Override
    public void addToken(Token token) throws InvalidTokenException {
        if (token == null) {
            throw new InvalidTokenException();
        }
        tokens.add(token);
    }

    @Override
    public Token getToken(User user) throws TokenNotFoundException {
        for (Token token : tokens) {
            if (token.getUser().equals(user)) {
                return token;
            }
        }
        throw new TokenNotFoundException();
    }
}