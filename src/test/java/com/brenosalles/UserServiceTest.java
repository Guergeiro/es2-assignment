package com.brenosalles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import com.brenosalles.handlers.IHandler;
import com.brenosalles.handlers.concrete.users.UsersRequestHandler;
import com.brenosalles.reqres.api.IReqresUser;
import com.brenosalles.reqres.api.implementation.ReqresUser;
import com.brenosalles.users.InvalidUserException;
import com.brenosalles.users.User;
import com.brenosalles.users.UserFactory;

import org.junit.jupiter.api.Test;

public class UserServiceTest {
    @Test
    public void createUserOk() throws InvalidUserException {
        IReqresUser apiUser = new ReqresUser();
        IHandler handler = new UsersRequestHandler(apiUser);
        User user = UserFactory.createUser(null, "email@email.com", "firstName", "lastName", "avatar");
        assertEquals(User.class, handler.createUser(user).getClass());
    }

    @Test
    public void updateUserOk() throws InvalidUserException {
        IReqresUser apiUser = new ReqresUser();
        IHandler handler = new UsersRequestHandler(apiUser);
        User user = UserFactory.createUser(null, "email@email.com", "firstName", "lastName", "avatar");
        assertTrue(handler.updateUser(1, user));
    }

    @Test
    public void deleteUserOk() {
        IReqresUser apiUser = new ReqresUser();
        IHandler handler = new UsersRequestHandler(apiUser);
        assertTrue(handler.deleteUser(1));
    }

    @Test
    public void getUserInvalidId() {
        IReqresUser apiUser = new ReqresUser();
        IHandler handler = new UsersRequestHandler(apiUser);
        assertNull(handler.readUser(0));
    }

    @Test
    public void getUserOk() {
        IReqresUser apiUser = new ReqresUser();
        IHandler handler = new UsersRequestHandler(apiUser);
        assertEquals(User.class, handler.readUser(1).getClass());
    }

    @Test
    public void getUsersOk() {
        IReqresUser apiUser = new ReqresUser();
        IHandler handler = new UsersRequestHandler(apiUser);
        assertEquals((new ArrayList<User>()).getClass(), handler.readUsers().getClass());
    }
}