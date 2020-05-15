package com.brenosalles.reqres.api;

import com.brenosalles.tokens.Token;
import com.brenosalles.users.User;

public interface IReqresAuthentication {
    Token register(User user, String password);

    Token login(User user, String password);
}