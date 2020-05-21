package com.brenosalles.reqres.api;

import java.util.ArrayList;

import com.brenosalles.users.InvalidUserException;
import com.brenosalles.users.User;

public interface IReqresUser {
    ArrayList<User> readUsers() throws InvalidUserException;

    User readUser(Integer id) throws InvalidUserException;

    User createUser(User user) throws InvalidUserException;

    Boolean updateUser(Integer id, User user);

    Boolean deleteUser(Integer id);
}