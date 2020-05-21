package com.brenosalles.handlers.concrete.users;

import com.brenosalles.handlers.AbstractHandler;
import com.brenosalles.users.User;

public class UsersValidatorHandler extends AbstractHandler {
    @Override
    public User createUser(User user) {
        if (user == null) {
            return null;
        }
        return super.createUser(user);
    }

    @Override
    public User readUser(Integer id) {
        if (id == null) {
            return null;
        }
        return super.readUser(id);
    }

    @Override
    public Boolean updateUser(Integer id, User user) {
        if (id == null) {
            return false;
        }
        if (user == null) {
            return false;
        }
        return super.updateUser(id, user);
    }

    @Override
    public Boolean deleteUser(Integer id) {
        if (id == null) {
            return false;
        }
        return super.deleteUser(id);
    }
}