package com.brenosalles.reqres;

import com.brenosalles.reqres.http.BadRequestException;
import com.brenosalles.reqres.http.UnauthorizedException;
import com.brenosalles.users.User;

import org.json.simple.JSONObject;

public interface IReqresAuthentication {
    JSONObject register(User user, String password) throws BadRequestException;

    JSONObject login(User user, String password) throws BadRequestException, UnauthorizedException;
}