package com.brenosalles.reqres.cache;

import java.util.ArrayList;

import com.brenosalles.reqres.cache.exceptions.UserNotFoundException;
import com.brenosalles.users.User;

public interface IUsersCache {
    Boolean addUser(User user);

    Boolean addUsers(ArrayList<User> users);

    User getUser(Integer id) throws UserNotFoundException;

    ArrayList<User> getUsers();

    Boolean updateUser(Integer id, User user);

    Boolean deleteUser(Integer id);
}