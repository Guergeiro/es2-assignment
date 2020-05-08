package com.brenosalles.reqres;

import com.brenosalles.users.User;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface IReqresUser {
    JSONArray readUsers();

    JSONObject readUser(Integer id);

    JSONObject createUser(User user);

    JSONObject updateUser(Integer id, User user);

    void deleteUser(Integer id);
}