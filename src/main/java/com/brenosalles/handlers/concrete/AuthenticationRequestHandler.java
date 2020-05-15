package com.brenosalles.handlers.concrete;

import com.brenosalles.handlers.AbstractHandler;
import com.brenosalles.reqres.api.IReqresAuthentication;
import com.brenosalles.reqres.http.Response;
import com.brenosalles.users.User;

public class AuthenticationRequestHandler extends AbstractHandler {
    // Attributes
    private IReqresAuthentication apiAuthentication;

    // Constructor
    public AuthenticationRequestHandler(IReqresAuthentication apiAuthentication) {
        this.apiAuthentication = apiAuthentication;
    }

    @Override
    public void register(User user, String password) {
        Response response = apiAuthentication.register(user, password);
        if (response.getStatusCode() >= 400) {
            super.register(user, password);
        }
    }

    @Override
    public void login(User user, String password) {
        Response response = apiAuthentication.login(user, password);
        if (response.getStatusCode() >= 400) {
            super.login(user, password);
        }
    }
}