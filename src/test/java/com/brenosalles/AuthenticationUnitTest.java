package com.brenosalles;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.brenosalles.handlers.IHandler;
import com.brenosalles.handlers.concrete.authentication.AuthenticationRequestHandler;
import com.brenosalles.handlers.concrete.authentication.AuthenticationValidatorHandler;
import com.brenosalles.reqres.api.IReqresAuthentication;
import com.brenosalles.reqres.api.stubs.ReqresAuthenticationStub;
import com.brenosalles.users.InvalidUserException;
import com.brenosalles.users.User;
import com.brenosalles.users.UserFactory;

import org.junit.jupiter.api.Test;

public class AuthenticationUnitTest {
    @Test
    public void registerWithNullUser() {
        IReqresAuthentication apiAuthentication = new ReqresAuthenticationStub();
        IHandler handler1 = new AuthenticationValidatorHandler();
        IHandler handler2 = new AuthenticationRequestHandler(apiAuthentication);

        handler1.setNext(handler2);

        User user = null;
        assertNull(handler1.register(user, "random password"));
    }

    @Test
    public void registerWithNullPassword() throws InvalidUserException {
        IReqresAuthentication apiAuthentication = new ReqresAuthenticationStub();
        IHandler handler1 = new AuthenticationValidatorHandler();
        IHandler handler2 = new AuthenticationRequestHandler(apiAuthentication);

        handler1.setNext(handler2);

        User user = UserFactory.createUser(1, "email@email.com", "firstName", "lastName", "avatar");
        assertNull(handler1.register(user, null));
    }

    @Test
    public void registerWithLowerPassword() throws InvalidUserException {
        IReqresAuthentication apiAuthentication = new ReqresAuthenticationStub();
        IHandler handler1 = new AuthenticationValidatorHandler();
        IHandler handler2 = new AuthenticationRequestHandler(apiAuthentication);

        handler1.setNext(handler2);

        User user = UserFactory.createUser(1, "email@email.com", "firstName", "lastName", "avatar");
        String password = "";
        for (int i = 0; i < 7; i++) {
            password += "a";
        }

        assertNull(handler1.register(user, password));
    }

    @Test
    public void registerWithHigherPassword() throws InvalidUserException {
        IReqresAuthentication apiAuthentication = new ReqresAuthenticationStub();
        IHandler handler1 = new AuthenticationValidatorHandler();
        IHandler handler2 = new AuthenticationRequestHandler(apiAuthentication);

        handler1.setNext(handler2);

        User user = UserFactory.createUser(1, "email@email.com", "firstName", "lastName", "avatar");
        String password = "";
        for (int i = 0; i < 129; i++) {
            password += "a";
        }

        assertNull(handler1.register(user, password));
    }

    @Test
    public void registerOk() throws InvalidUserException {
        IReqresAuthentication apiAuthentication = new ReqresAuthenticationStub();
        IHandler handler1 = new AuthenticationValidatorHandler();
        IHandler handler2 = new AuthenticationRequestHandler(apiAuthentication);

        handler1.setNext(handler2);

        User user = UserFactory.createUser(1, "email@email.com", "firstName", "lastName", "avatar");
        String password = "";
        for (int i = 0; i < 64; i++) {
            password += "a";
        }

        assertNotNull(handler1.register(user, password));
    }

    @Test
    public void loginWithNullUser() {
        IReqresAuthentication apiAuthentication = new ReqresAuthenticationStub();
        IHandler handler1 = new AuthenticationValidatorHandler();
        IHandler handler2 = new AuthenticationRequestHandler(apiAuthentication);

        handler1.setNext(handler2);

        User user = null;
        assertNull(handler1.login(user, "random password"));
    }

    @Test
    public void loginWithNullPassword() throws InvalidUserException {
        IReqresAuthentication apiAuthentication = new ReqresAuthenticationStub();
        IHandler handler1 = new AuthenticationValidatorHandler();
        IHandler handler2 = new AuthenticationRequestHandler(apiAuthentication);

        handler1.setNext(handler2);

        User user = UserFactory.createUser(1, "email@email.com", "firstName", "lastName", "avatar");
        assertNull(handler1.login(user, null));
    }

    @Test
    public void loginWithLowerPassword() throws InvalidUserException {
        IReqresAuthentication apiAuthentication = new ReqresAuthenticationStub();
        IHandler handler1 = new AuthenticationValidatorHandler();
        IHandler handler2 = new AuthenticationRequestHandler(apiAuthentication);

        handler1.setNext(handler2);

        User user = UserFactory.createUser(1, "email@email.com", "firstName", "lastName", "avatar");
        String password = "";
        for (int i = 0; i < 7; i++) {
            password += "a";
        }

        assertNull(handler1.login(user, password));
    }

    @Test
    public void loginWithHigherPassword() throws InvalidUserException {
        IReqresAuthentication apiAuthentication = new ReqresAuthenticationStub();
        IHandler handler1 = new AuthenticationValidatorHandler();
        IHandler handler2 = new AuthenticationRequestHandler(apiAuthentication);

        handler1.setNext(handler2);

        User user = UserFactory.createUser(1, "email@email.com", "firstName", "lastName", "avatar");
        String password = "";
        for (int i = 0; i < 129; i++) {
            password += "a";
        }

        assertNull(handler1.login(user, password));
    }

    @Test
    public void loginOk() throws InvalidUserException {
        IReqresAuthentication apiAuthentication = new ReqresAuthenticationStub();
        IHandler handler1 = new AuthenticationValidatorHandler();
        IHandler handler2 = new AuthenticationRequestHandler(apiAuthentication);

        handler1.setNext(handler2);

        User user = UserFactory.createUser(1, "email@email.com", "firstName", "lastName", "avatar");
        String password = "";
        for (int i = 0; i < 64; i++) {
            password += "a";
        }

        assertNotNull(handler1.login(user, password));
    }
}
