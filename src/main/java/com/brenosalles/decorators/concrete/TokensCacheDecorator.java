package com.brenosalles.decorators.concrete;

import com.brenosalles.decorators.Decorator;
import com.brenosalles.decorators.IComponent;
import com.brenosalles.reqres.cache.ITokensCache;
import com.brenosalles.reqres.cache.exceptions.InvalidTokenException;
import com.brenosalles.tokens.Token;
import com.brenosalles.users.User;

public class TokensCacheDecorator extends Decorator {
    // Attributes
    private ITokensCache cache;

    // Constructor
    public TokensCacheDecorator(IComponent component, ITokensCache cache) {
        super(component);
        this.cache = cache;
    }

    @Override
    public Token register(User user, String password) {
        Token token = super.register(user, password);

        try {
            cache.addToken(token);
        } catch (InvalidTokenException e) {
            System.out.println(e.getMessage());
        }

        return token;
    }

    @Override
    public Token login(User user, String password) {
        Token token = super.login(user, password);
        try {
            cache.addToken(token);
        } catch (InvalidTokenException e) {
            System.out.println(e.getMessage());
        }
        return token;
    }
}