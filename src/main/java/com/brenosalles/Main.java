package com.brenosalles;

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

public class Main {
    public static void main(String[] args) throws InvalidUserException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        IReqresUser apiUser = new ReqresUserStub();
        IHandler handler1 = new UsersValidatorHandler();
        IHandler handler2 = new UsersRequestHandler(apiUser);

        handler1.setNext(handler2);

        IComponent finalImplementation = new UsersCacheDecorator(handler1, new UsersCacheRepository());

        User originalUser = UserFactory.createUser(null, "email@email.com", "firstName", "lastName", "avatar");

        User createdUser = finalImplementation.createUser(originalUser);

        // Access private cache
        Field f1 = finalImplementation.getClass().getDeclaredField("cache");
        f1.setAccessible(true);
        UsersCacheRepository cache = (UsersCacheRepository) f1.get(finalImplementation);

        // Access private cache ArrayList
        Field f2 = cache.getClass().getDeclaredField("users");
        f2.setAccessible(true);
        ArrayList<User> users = (ArrayList<User>) f2.get(cache);
        System.out.println(users.get(0));
    }
}