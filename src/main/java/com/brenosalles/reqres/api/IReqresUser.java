package com.brenosalles.reqres.api;

import java.util.ArrayList;

import com.brenosalles.users.User;

public interface IReqresUser {
    ArrayList<User> readUsers();

    User readUser(Integer id);

    User createUser(User user);

    Boolean updateUser(Integer id, User user);

    Boolean deleteUser(Integer id);
}