package com.brenosalles.reqres.cache.mocks;

import java.util.ArrayList;

import com.brenosalles.reqres.cache.IUsersCache;
import com.brenosalles.reqres.cache.exceptions.UserNotFound;
import com.brenosalles.users.User;

public class UsersCacheRepository implements IUsersCache {
    private ArrayList<User> users = new ArrayList<User>();

    @Override
    public Boolean addUser(User user) {
        return users.add(user);
    }

    @Override
    public Boolean addUsers(ArrayList<User> users) {
        return this.users.addAll(users);
    }

    @Override
    public User getUser(Integer id) throws UserNotFound {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        throw new UserNotFound();
    }

    @Override
    public ArrayList<User> getUsers() {
        return users;
    }

    @Override
    public Boolean updateUser(Integer id, User user) {
        for (User u : users) {
            if (u.getId() == id) {
                u.setAvatar(user.getAvatar());
                u.setEmail(user.getEmail());
                u.setFirstName(user.getFirstName());
                u.setLastName(user.getLastName());
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean deleteUser(Integer id) {
        return users.removeIf(user -> user.getId() == id);
    }
}