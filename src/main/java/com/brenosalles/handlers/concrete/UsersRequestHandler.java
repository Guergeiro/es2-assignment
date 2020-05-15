package com.brenosalles.handlers.concrete;

import java.util.ArrayList;

import com.brenosalles.handlers.AbstractHandler;
import com.brenosalles.reqres.api.IReqresUser;
import com.brenosalles.users.User;

public class UsersRequestHandler extends AbstractHandler {
    // Attributes
    private IReqresUser apiUser;

    // Constructor
    public UsersRequestHandler(IReqresUser apiUser) {
        this.apiUser = apiUser;
    }

    @Override
    public User createUser(User resource) {
        User res = apiUser.createUser(resource);
        return res != null ? res : super.createUser(resource);
    }

    @Override
    public ArrayList<User> readUsers() {
        ArrayList<User> resources = apiUser.readUsers();
        if (resources.size() != 0) {
            return resources;
        }
        return super.readUsers();
    }

    @Override
    public User readUser(Integer id) {
        User resource = apiUser.readUser(id);

        return resource != null ? resource : super.readUser(id);
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