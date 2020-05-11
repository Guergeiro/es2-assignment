package com.brenosalles.reqres.api;

import com.brenosalles.reqres.http.Response;
import com.brenosalles.users.User;

public interface IReqresUser {
    Response readUsers();

    Response readUser(Integer id);

    Response createUser(User user);

    Response updateUser(Integer id, User user);

    Response deleteUser(Integer id);
}