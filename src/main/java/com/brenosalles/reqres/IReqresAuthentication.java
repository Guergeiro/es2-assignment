package com.brenosalles.reqres;

import com.brenosalles.users.User;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public interface IReqresAuthentication {
    JSONObject register(User user, String password) throws ParseException;

    JSONObject login(User user, String password) throws ParseException;
}