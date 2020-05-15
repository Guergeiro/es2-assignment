package com.brenosalles.reqres.cache;

import java.util.ArrayList;

import com.brenosalles.reqres.cache.exceptions.UserNotFound;
import com.brenosalles.users.User;

public class UsersCacheRepository {
    private ArrayList<User> users = new ArrayList<User>();

    public void addUser(User user) {
        users.add(user);
    }

    public void addUsers(ArrayList<User> users) {
        this.users.addAll(users);
    }

    public User getUser(Integer id) throws UserNotFound {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        throw new UserNotFound();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

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

    public void deleteUser(Integer id) {
        users.removeIf(user -> user.getId() == id);
    }

}