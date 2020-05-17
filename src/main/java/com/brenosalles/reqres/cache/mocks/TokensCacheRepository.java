package com.brenosalles.reqres.cache.mocks;

import java.util.ArrayList;

import com.brenosalles.reqres.cache.ITokensCache;
import com.brenosalles.reqres.cache.exceptions.TokenNotFound;
import com.brenosalles.tokens.Token;
import com.brenosalles.users.User;

public class TokensCacheRepository implements ITokensCache {
    private ArrayList<Token> tokens = new ArrayList<Token>();

    @Override
    public void addToken(Token token) {
        tokens.add(token);
    }

    @Override
    public Token getToken(User user) throws TokenNotFound {
        for (Token token : tokens) {
            if (token.getUser().equals(user)) {
                return token;
            }
        }
        throw new TokenNotFound();
    }
}