package com.brenosalles.handlers.concrete;

import java.util.ArrayList;

import com.brenosalles.handlers.AbstractHandler;
import com.brenosalles.reqres.api.IReqresUser;
import com.brenosalles.users.InvalidUserException;
import com.brenosalles.users.User;

public class UsersRequestHandler extends AbstractHandler {
    // Attributes
    private IReqresUser apiUser;

    // Constructor
    public UsersRequestHandler(IReqresUser apiUser) {
        this.apiUser = apiUser;
    }

    @Override
    public User createUser(User user) {
        try {
            return apiUser.createUser(user);
        } catch (InvalidUserException e) {
            return super.createUser(user);
        }
    }

    @Override
    public ArrayList<User> readUsers() {
        try {
            return apiUser.readUsers();
        } catch (InvalidUserException e) {
            return super.readUsers();
        }
    }

    @Override
    public User readUser(Integer id) {
        try {
            return apiUser.readUser(id);
        } catch (InvalidUserException e) {
            return super.readUser(id);
        }
    }

    @Override
    public void updateUser(Integer id, User resource) {
        if (apiUser.updateUser(id, resource) == false) {
            super.updateUser(id, resource);
        }
    }

    @Override
    public void deleteUser(Integer id) {
        if (apiUser.deleteUser(id) == false) {
            super.deleteUser(id);
        }
    }
}