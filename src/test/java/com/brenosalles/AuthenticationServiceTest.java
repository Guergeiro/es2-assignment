package com.brenosalles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.brenosalles.handlers.IHandler;
import com.brenosalles.handlers.concrete.authentication.AuthenticationRequestHandler;
import com.brenosalles.reqres.api.IReqresAuthentication;
import com.brenosalles.reqres.api.implementation.ReqresAuthentication;
import com.brenosalles.tokens.Token;
import com.brenosalles.users.InvalidUserException;
import com.brenosalles.users.User;
import com.brenosalles.users.UserFactory;

import org.junit.jupiter.api.Test;

public class AuthenticationServiceTest {
    @Test
    public void registerWithNullPassword() throws InvalidUserException {
        IReqresAuthentication apiAuthentication = new ReqresAuthentication();
        IHandler handler = new AuthenticationRequestHandler(apiAuthentication);

        User user = UserFactory.createUser(null, "email@email.com", "firstName", "lastName", "avatar");
        assertNull(handler.register(user, null));
    }

    @Test
    public void registerWithNotDefinedUser() throws InvalidUserException {
        IReqresAuthentication apiAuthentication = new ReqresAuthentication();
        IHandler handler = new AuthenticationRequestHandler(apiAuthentication);

        User user = UserFactory.createUser(null, "email@email.com", "firstName", "lastName", "avatar");
        assertNull(handler.register(user, "random password"));
    }

    @Test
    public void registerOk() throws InvalidUserException {
        IReqresAuthentication apiAuthentication = new ReqresAuthentication();
        IHandler handler = new AuthenticationRequestHandler(apiAuthentication);

        User user = UserFactory.createUser(null, "eve.holt@reqres.in", "firstName", "lastName", "avatar");
        assertEquals(Token.class, handler.register(user, "Random password").getClass());
    }

    @Test
    public void loginWithNullPassword() throws InvalidUserException {
        IReqresAuthentication apiAuthentication = new ReqresAuthentication();
        IHandler handler = new AuthenticationRequestHandler(apiAuthentication);

        User user = UserFactory.createUser(null, "email@email.com", "firstName", "lastName", "avatar");
        assertNull(handler.login(user, null));
    }

    @Test
    public void loginWithNotDefinedUser() throws InvalidUserException {
        IReqresAuthentication apiAuthentication = new ReqresAuthentication();
        IHandler handler = new AuthenticationRequestHandler(apiAuthentication);

        User user = UserFactory.createUser(null, "email@email.com", "firstName", "lastName", "avatar");
        assertNull(handler.login(user, "random password"));
    }

    @Test
    public void loginOk() throws InvalidUserException {
        IReqresAuthentication apiAuthentication = new ReqresAuthentication();
        IHandler handler = new AuthenticationRequestHandler(apiAuthentication);

        User user = UserFactory.createUser(null, "eve.holt@reqres.in", "firstName", "lastName", "avatar");
        assertEquals(Token.class, handler.login(user, "Random password").getClass());
    }
}