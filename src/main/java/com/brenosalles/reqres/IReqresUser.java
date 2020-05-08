package com.brenosalles.reqres;

import com.brenosalles.reqres.http.BadRequestException;
import com.brenosalles.reqres.http.NotFoundException;
import com.brenosalles.users.User;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface IReqresUser {
    JSONArray readUsers();

    JSONObject readUser(Integer id) throws NotFoundException;

    JSONObject createUser(User user) throws BadRequestException;

    JSONObject updateUser(Integer id, User user) throws NotFoundException, BadRequestException;

    void deleteUser(Integer id) throws NotFoundException;
}