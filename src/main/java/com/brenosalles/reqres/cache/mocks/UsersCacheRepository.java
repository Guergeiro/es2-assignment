package com.brenosalles.reqres.cache.mocks;

import java.util.ArrayList;

import com.brenosalles.reqres.cache.IUsersCache;
import com.brenosalles.reqres.cache.exceptions.UserNotFound;
import com.brenosalles.users.User;

public class UsersCacheRepository implements IUsersCache {
    private ArrayList<User> users = new ArrayList<User>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void addUsers(ArrayList<User> users) {
        this.users.addAll(users);
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
    public void updateUser(Integer id, User user) {
        for (User u : users) {
            if (u.getId() == id) {
                u.setAvatar(user.getAvatar());
                u.setEmail(user.getEmail());
                u.setFirstName(user.getFirstName());
                u.setLastName(user.getLastName());
                return;
            }
        }
    }

    @Override
    public void deleteUser(Integer id) {
        users.removeIf(user -> user.getId() == id);
    }
}