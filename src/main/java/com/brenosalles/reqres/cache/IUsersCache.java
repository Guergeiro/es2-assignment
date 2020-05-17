package com.brenosalles.reqres.cache;

import java.util.ArrayList;

import com.brenosalles.reqres.cache.exceptions.UserNotFound;
import com.brenosalles.users.User;

public interface IUsersCache {
    void addUser(User user);

    void addUsers(ArrayList<User> users);

    User getUser(Integer id) throws UserNotFound;

    ArrayList<User> getUsers();

    void updateUser(Integer id, User user);

    void deleteUser(Integer id);
}