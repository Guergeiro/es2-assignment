package com.brenosalles.reqres;

import com.brenosalles.users.User;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public interface IReqresUser {
    JSONArray readUsers() throws ParseException;

    JSONObject readUser(Integer id) throws ParseException;

    JSONObject createUser(User user) throws ParseException;

    JSONObject updateUser(Integer id, User user) throws ParseException;

    void deleteUser(Integer id);
}