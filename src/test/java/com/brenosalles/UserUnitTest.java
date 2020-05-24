package com.brenosalles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.brenosalles.decorators.IComponent;
import com.brenosalles.decorators.concrete.UsersCacheDecorator;
import com.brenosalles.handlers.IHandler;
import com.brenosalles.handlers.concrete.users.UsersRequestHandler;
import com.brenosalles.handlers.concrete.users.UsersValidatorHandler;
import com.brenosalles.reqres.api.IReqresUser;
import com.brenosalles.reqres.api.stubs.ReqresUserStub;
import com.brenosalles.reqres.cache.implementation.UsersCacheRepository;
import com.brenosalles.users.InvalidUserException;
import com.brenosalles.users.User;
import com.brenosalles.users.UserFactory;

import org.junit.jupiter.api.Test;

public class UserUnitTest {
    @Test
    public void createUserWithNullEmail() {
        assertThrows(InvalidUserException.class, () -> {
            UserFactory.createUser(null, null, "firstName", "lastName", "avatar");
        });
    }

    @Test
    public void createUserWithInvalidEmail() {
        assertThrows(InvalidUserException.class, () -> {
            UserFactory.createUser(null, "email", "firstName", "lastName", "avatar");
        });
    }

    @Test
    public void createUserWithNullFirstName() {
        assertThrows(InvalidUserException.class, () -> {
            UserFactory.createUser(null, "email@email.com", null, "lastName", "avatar");
        });
    }

    @Test
    public void createUserWithLowerFirstName() {
        assertThrows(InvalidUserException.class, () -> {
            String firstName = "";
            for (int i = 0; i < 2; i++) {
                firstName += "a";
            }
            UserFactory.createUser(null, "email@email.com", firstName, "lastName", "avatar");
        });
    }

    @Test
    public void createUserWithHigherFirstName() {
        assertThrows(InvalidUserException.class, () -> {
            String firstName = "";
            for (int i = 0; i < 17; i++) {
                firstName += "a";
            }
            UserFactory.createUser(null, "email@email.com", firstName, "lastName", "avatar");
        });
    }

    @Test
    public void createUserWithNullLastName() {
        assertThrows(InvalidUserException.class, () -> {
            UserFactory.createUser(null, "email@email.com", "firstName", null, "avatar");
        });
    }

    @Test
    public void createUserWithLowerLastName() {
        assertThrows(InvalidUserException.class, () -> {
            String lastName = "";
            for (int i = 0; i < 2; i++) {
                lastName += "a";
            }
            UserFactory.createUser(null, "email@email.com", "firstName", lastName, "avatar");
        });
    }

    @Test
    public void createUserWithHigherLastName() {
        assertThrows(InvalidUserException.class, () -> {
            String lastName = "";
            for (int i = 0; i < 17; i++) {
                lastName += "a";
            }
            UserFactory.createUser(null, "email@email.com", "firstName", lastName, "avatar");
        });
    }

    @Test
    public void createUserWithNullAvatar() {
        assertThrows(InvalidUserException.class, () -> {
            UserFactory.createUser(null, "email@email.com", "firstName", "lastName", null);
        });
    }

    @Test
    public void createUserWithLowerAvatar() {
        assertThrows(InvalidUserException.class, () -> {
            String avatar = "";
            for (int i = 0; i < 3; i++) {
                avatar += "a";
            }
            UserFactory.createUser(null, "email@email.com", "firstName", "lastName", avatar);
        });
    }

    @Test
    public void createUserWithHigherAvatar() {
        assertThrows(InvalidUserException.class, () -> {
            String avatar = "";
            for (int i = 0; i < 65; i++) {
                avatar += "a";
            }
            UserFactory.createUser(null, "email@email.com", "firstName", "lastName", avatar);
        });
    }

    @Test
    public void createUserOk() throws InvalidUserException {
        assertEquals(User.class,
                UserFactory.createUser(null, "email@email.com", "firstName", "lastName", "avatar").getClass());

    }

    @Test
    public void readUsersOk() {
        IReqresUser apiUser = new ReqresUserStub();
        IHandler handler1 = new UsersValidatorHandler();
        IHandler handler2 = new UsersRequestHandler(apiUser);

        handler1.setNext(handler2);

        assertEquals((new ArrayList<User>()).getClass(), handler1.readUsers().getClass());
    }

    @Test
    public void readUserWithNullId() {
        IReqresUser apiUser = new ReqresUserStub();
        IHandler handler1 = new UsersValidatorHandler();
        IHandler handler2 = new UsersRequestHandler(apiUser);

        handler1.setNext(handler2);

        assertNull(handler1.readUser(null));
    }

    @Test
    public void readUserWithInvalidId() {
        IReqresUser apiUser = new ReqresUserStub();
        IHandler handler1 = new UsersValidatorHandler();
        IHandler handler2 = new UsersRequestHandler(apiUser);

        handler1.setNext(handler2);

        assertNull(handler1.readUser(0));
    }

    @Test
    public void readUserOk() {
        IReqresUser apiUser = new ReqresUserStub();
        IHandler handler1 = new UsersValidatorHandler();
        IHandler handler2 = new UsersRequestHandler(apiUser);

        handler1.setNext(handler2);

        assertEquals(User.class, handler1.readUser(1).getClass());
    }

    @Test
    public void updateWithNullId() throws InvalidUserException {
        IReqresUser apiUser = new ReqresUserStub();
        IHandler handler1 = new UsersValidatorHandler();
        IHandler handler2 = new UsersRequestHandler(apiUser);

        handler1.setNext(handler2);

        User user = UserFactory.createUser(null, "email@email.com", "firstName", "lastName", "avatar");

        assertFalse(handler1.updateUser(null, user));
    }

    @Test
    public void updateWithInvalidId() throws InvalidUserException {
        IReqresUser apiUser = new ReqresUserStub();
        IHandler handler1 = new UsersValidatorHandler();
        IHandler handler2 = new UsersRequestHandler(apiUser);

        handler1.setNext(handler2);

        User user = UserFactory.createUser(null, "email@email.com", "firstName", "lastName", "avatar");
        assertFalse(handler1.updateUser(0, user));
    }

    @Test
    public void updateWithNullUser() throws InvalidUserException {
        IReqresUser apiUser = new ReqresUserStub();
        IHandler handler1 = new UsersValidatorHandler();
        IHandler handler2 = new UsersRequestHandler(apiUser);

        handler1.setNext(handler2);

        assertFalse(handler1.updateUser(1, null));
    }

    @Test
    public void updateUserOk() throws InvalidUserException {
        IReqresUser apiUser = new ReqresUserStub();
        IHandler handler1 = new UsersValidatorHandler();
        IHandler handler2 = new UsersRequestHandler(apiUser);

        handler1.setNext(handler2);

        User user = UserFactory.createUser(null, "email@email.com", "firstName", "lastName", "avatar");
        assertTrue(handler1.updateUser(1, user));
    }

    @Test
    public void deleteWithNullId() throws InvalidUserException {
        IReqresUser apiUser = new ReqresUserStub();
        IHandler handler1 = new UsersValidatorHandler();
        IHandler handler2 = new UsersRequestHandler(apiUser);

        handler1.setNext(handler2);

        assertFalse(handler1.deleteUser(null));
    }

    @Test
    public void deleteWithInvalidId() throws InvalidUserException {
        IReqresUser apiUser = new ReqresUserStub();
        IHandler handler1 = new UsersValidatorHandler();
        IHandler handler2 = new UsersRequestHandler(apiUser);

        handler1.setNext(handler2);

        assertFalse(handler1.deleteUser(0));
    }

    @Test
    public void deleteUserOk() throws InvalidUserException {
        IReqresUser apiUser = new ReqresUserStub();
        IHandler handler1 = new UsersValidatorHandler();
        IHandler handler2 = new UsersRequestHandler(apiUser);

        handler1.setNext(handler2);

        assertTrue(handler1.deleteUser(1));
    }

    @Test
    public void readUserInCacheInvalidId() throws InvalidUserException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        IReqresUser apiUser = new ReqresUserStub();
        IHandler handler1 = new UsersValidatorHandler();
        IHandler handler2 = new UsersRequestHandler(apiUser);

        handler1.setNext(handler2);

        IComponent finalImplementation = new UsersCacheDecorator(handler1, new UsersCacheRepository());

        // Access private cache
        Field f1 = finalImplementation.getClass().getDeclaredField("cache");
        f1.setAccessible(true);
        UsersCacheRepository cache = (UsersCacheRepository) f1.get(finalImplementation);

        // Access private cache ArrayList
        Field f2 = cache.getClass().getDeclaredField("users");
        f2.setAccessible(true);
        ArrayList<User> users = (ArrayList<User>) f2.get(cache);

        User originalUser = UserFactory.createUser(1, "email@email.com", "firstName", "lastName", "avatar");
        users.add(originalUser);

        assertNull(finalImplementation.readUser(0));
    }

    @Test
    public void readUserInCacheOk() throws InvalidUserException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        IReqresUser apiUser = new ReqresUserStub();
        IHandler handler1 = new UsersValidatorHandler();
        IHandler handler2 = new UsersRequestHandler(apiUser);

        handler1.setNext(handler2);

        IComponent finalImplementation = new UsersCacheDecorator(handler1, new UsersCacheRepository());

        // Access private cache
        Field f1 = finalImplementation.getClass().getDeclaredField("cache");
        f1.setAccessible(true);
        UsersCacheRepository cache = (UsersCacheRepository) f1.get(finalImplementation);

        // Access private cache ArrayList
        Field f2 = cache.getClass().getDeclaredField("users");
        f2.setAccessible(true);
        ArrayList<User> users = (ArrayList<User>) f2.get(cache);

        User originalUser = UserFactory.createUser(1, "email@email.com", "firstName", "lastName", "avatar");
        users.add(originalUser);

        assertEquals(originalUser, finalImplementation.readUser(1));
    }

    @Test
    public void createUserInCacheOk() throws InvalidUserException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        IReqresUser apiUser = new ReqresUserStub();
        IHandler handler1 = new UsersValidatorHandler();
        IHandler handler2 = new UsersRequestHandler(apiUser);

        handler1.setNext(handler2);

        IComponent finalImplementation = new UsersCacheDecorator(handler1, new UsersCacheRepository());

        // Access private cache
        Field f1 = finalImplementation.getClass().getDeclaredField("cache");
        f1.setAccessible(true);
        UsersCacheRepository cache = (UsersCacheRepository) f1.get(finalImplementation);

        // Access private cache ArrayList
        Field f2 = cache.getClass().getDeclaredField("users");
        f2.setAccessible(true);
        ArrayList<User> users = (ArrayList<User>) f2.get(cache);
        Integer originalSize = users.size();

        User originalUser = UserFactory.createUser(null, "email@email.com", "firstName", "lastName", "avatar");
        originalUser.setId(finalImplementation.createUser(originalUser).getId());

        assertTrue(users.contains(originalUser));
        assertEquals(originalSize + 1, finalImplementation.readUsers().size());
    }

    @Test
    public void updateUserInCacheInvalidId() throws InvalidUserException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        IReqresUser apiUser = new ReqresUserStub();
        IHandler handler1 = new UsersValidatorHandler();
        IHandler handler2 = new UsersRequestHandler(apiUser);

        handler1.setNext(handler2);

        IComponent finalImplementation = new UsersCacheDecorator(handler1, new UsersCacheRepository());

        // Access private cache
        Field f1 = finalImplementation.getClass().getDeclaredField("cache");
        f1.setAccessible(true);
        UsersCacheRepository cache = (UsersCacheRepository) f1.get(finalImplementation);

        // Access private cache ArrayList
        Field f2 = cache.getClass().getDeclaredField("users");
        f2.setAccessible(true);
        ArrayList<User> users = (ArrayList<User>) f2.get(cache);

        // Create a user for update
        User originalUser = UserFactory.createUser(1, "email@email.com", "firstName", "lastName", "avatar");
        users.add(originalUser);

        User newUser = UserFactory.createUser(null, "breno@breno.com", "firstName", "lastName", "avatar");
        assertFalse(finalImplementation.updateUser(0, newUser));
    }

    @Test
    public void updateUserInCacheOk() throws InvalidUserException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        IReqresUser apiUser = new ReqresUserStub();
        IHandler handler1 = new UsersValidatorHandler();
        IHandler handler2 = new UsersRequestHandler(apiUser);

        handler1.setNext(handler2);

        IComponent finalImplementation = new UsersCacheDecorator(handler1, new UsersCacheRepository());

        // Access private cache
        Field f1 = finalImplementation.getClass().getDeclaredField("cache");
        f1.setAccessible(true);
        UsersCacheRepository cache = (UsersCacheRepository) f1.get(finalImplementation);

        // Access private cache ArrayList
        Field f2 = cache.getClass().getDeclaredField("users");
        f2.setAccessible(true);
        ArrayList<User> users = (ArrayList<User>) f2.get(cache);

        // Create a user for update
        User originalUser = UserFactory.createUser(1, "email@email.com", "firstName", "lastName", "avatar");
        users.add(originalUser);

        User newUser = UserFactory.createUser(null, "breno@breno.com", "firstName", "lastName", "avatar");
        assertTrue(finalImplementation.updateUser(originalUser.getId(), newUser));
        assertTrue(users.contains(originalUser));
    }

    @Test
    public void deleteUserInCacheInvalidId() throws InvalidUserException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        IReqresUser apiUser = new ReqresUserStub();
        IHandler handler1 = new UsersValidatorHandler();
        IHandler handler2 = new UsersRequestHandler(apiUser);

        handler1.setNext(handler2);

        IComponent finalImplementation = new UsersCacheDecorator(handler1, new UsersCacheRepository());

        // Access private cache
        Field f1 = finalImplementation.getClass().getDeclaredField("cache");
        f1.setAccessible(true);
        UsersCacheRepository cache = (UsersCacheRepository) f1.get(finalImplementation);

        // Access private cache ArrayList
        Field f2 = cache.getClass().getDeclaredField("users");
        f2.setAccessible(true);
        ArrayList<User> users = (ArrayList<User>) f2.get(cache);

        // Create a user for delete
        User originalUser = UserFactory.createUser(1, "email@email.com", "firstName", "lastName", "avatar");
        users.add(originalUser);

        assertFalse(finalImplementation.deleteUser(0));
    }

    @Test
    public void deleteUserInCacheOk() throws InvalidUserException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        IReqresUser apiUser = new ReqresUserStub();
        IHandler handler1 = new UsersValidatorHandler();
        IHandler handler2 = new UsersRequestHandler(apiUser);

        handler1.setNext(handler2);

        IComponent finalImplementation = new UsersCacheDecorator(handler1, new UsersCacheRepository());

        // Access private cache
        Field f1 = finalImplementation.getClass().getDeclaredField("cache");
        f1.setAccessible(true);
        UsersCacheRepository cache = (UsersCacheRepository) f1.get(finalImplementation);

        // Access private cache ArrayList
        Field f2 = cache.getClass().getDeclaredField("users");
        f2.setAccessible(true);
        ArrayList<User> users = (ArrayList<User>) f2.get(cache);

        // Create a user for update
        User originalUser = UserFactory.createUser(1, "email@email.com", "firstName", "lastName", "avatar");
        users.add(originalUser);

        assertTrue(finalImplementation.deleteUser(originalUser.getId()));
        assertFalse(users.contains(originalUser));
    }
}