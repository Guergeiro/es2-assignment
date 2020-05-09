package com.brenosalles.reqres.implementation;

import com.brenosalles.reqres.IReqresAuthentication;
import com.brenosalles.reqres.http.BadRequestException;
import com.brenosalles.reqres.http.HttpMethods;
import com.brenosalles.reqres.http.NotFoundException;
import com.brenosalles.reqres.http.Request;
import com.brenosalles.reqres.http.UnauthorizedException;
import com.brenosalles.users.User;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReqresAuthentication implements IReqresAuthentication {
    @Override
    @SuppressWarnings("unchecked")
    public JSONObject register(User user, String password) throws ParseException {
        JSONObject obj = new JSONObject();
        obj.put("email", user.getEmail());
        obj.put("password", password);

        JSONParser parser = new JSONParser();
        String result = null;
        try {
            result = Request.makeHttpRequest("https://reqres.in/api/register", HttpMethods.POST, obj);
        } catch (BadRequestException | UnauthorizedException | NotFoundException e) {
            return (JSONObject) parser.parse(e.getMessage());
        }
        return (JSONObject) parser.parse(result);
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject login(User user, String password) throws ParseException {
        JSONObject obj = new JSONObject();
        obj.put("email", user.getEmail());
        obj.put("password", password);

        JSONParser parser = new JSONParser();
        String result = null;
        try {
            result = Request.makeHttpRequest("https://reqres.in/api/register", HttpMethods.POST, obj);
        } catch (BadRequestException | UnauthorizedException | NotFoundException e) {
            return (JSONObject) parser.parse(e.getMessage());
        }
        return (JSONObject) parser.parse(result);
    }
}