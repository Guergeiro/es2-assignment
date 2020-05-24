package com.brenosalles.reqres.api.stubs;

import java.util.ArrayList;

import com.brenosalles.reqres.api.IReqresUser;
import com.brenosalles.users.InvalidUserException;
import com.brenosalles.users.User;
import com.brenosalles.users.UserFactory;

public class ReqresUserStub implements IReqresUser {
    @Override
    public ArrayList<User> readUsers() throws InvalidUserException {
        ArrayList<User> arr = new ArrayList<User>();
        for (Integer i = 1; i < 5; i++) {
            arr.add(UserFactory.createUser(i, i + "@email.com", i + "FName", i + "LName", "https://" + i + ".com"));
        }
        return arr;
    }

    @Override
    public User readUser(Integer id) throws InvalidUserException {
        if (id < 1) {
            return null;
        }
        return UserFactory.createUser(id, id + "@email.com", id + "FName", id + "LName", "https://" + id + ".com");
    }

    @Override
    public User createUser(User user) {
        if (user.getId() != null) {
            return null;
        }
        user.setId(1);
        return user;
    }

    @Override
    public Boolean updateUser(Integer id, User user) {
        if (id < 1) {
            return false;
        }
        if (user.getId() != null) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean deleteUser(Integer id) {
        if (id < 1) {
            return false;
        }
        return true;
    }
}