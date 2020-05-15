package com.brenosalles.decorators.concrete;

import java.util.ArrayList;

import com.brenosalles.decorators.Decorator;
import com.brenosalles.decorators.IComponent;
import com.brenosalles.reqres.cache.UsersCacheRepository;
import com.brenosalles.reqres.cache.exceptions.UserNotFound;
import com.brenosalles.users.User;

public class UsersCacheDecorator extends Decorator {
    // Attributes
    private UsersCacheRepository cache;

    // Constructor
    public UsersCacheDecorator(IComponent component, UsersCacheRepository cache) {
        super(component);
        this.cache = cache;
    }

    @Override
    public User createUser(User user) {
        cache.addUser(user);
        return super.createUser(user);
    }

    @Override
    public ArrayList<User> readUsers() {
        ArrayList<User> cacheUsers = cache.getUsers();
        if (cacheUsers.size() != 0) {
            return cacheUsers;
        }

        ArrayList<User> newUsers = super.readUsers();
        cache.addUsers(newUsers);
        return newUsers;
    }

    @Override
    public User readUser(Integer id) {
        try {
            return cache.getUser(id);
        } catch (UserNotFound e) {
            User user = super.readUser(id);
            cache.addUser(user);
            return user;
        }
    }

    @Override
    public void updateUser(Integer id, User user) {
        cache.updateUser(id, user);
        super.updateUser(id, user);
    }

    @Override
    public void deleteUser(Integer id) {
        cache.deleteUser(id);
        super.deleteUser(id);
    }
}