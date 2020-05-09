package com.brenosalles.reqres.implementation;

import com.brenosalles.reqres.IReqresUser;
import com.brenosalles.reqres.http.BadRequestException;
import com.brenosalles.reqres.http.HttpMethods;
import com.brenosalles.reqres.http.NotFoundException;
import com.brenosalles.reqres.http.Request;
import com.brenosalles.reqres.http.UnauthorizedException;
import com.brenosalles.users.User;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReqresUser implements IReqresUser {

    @Override
    public JSONArray readUsers() throws ParseException {
        JSONParser parser = new JSONParser();
        String result = null;
        try {
            result = Request.makeHttpRequest("https://reqres.in/api/users", HttpMethods.GET, null);
        } catch (BadRequestException | UnauthorizedException | NotFoundException e) {
            return new JSONArray();
        }

        return (JSONArray) parser.parse(result);
    }

    @Override
    public JSONObject readUser(Integer id) throws ParseException {
        JSONParser parser = new JSONParser();
        String result = null;
        try {
            result = Request.makeHttpRequest("https://reqres.in/api/users/" + id, HttpMethods.GET, null);
        } catch (BadRequestException | UnauthorizedException | NotFoundException e) {
            return (JSONObject) parser.parse(e.getMessage());
        }

        return (JSONObject) parser.parse(result);
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject createUser(User user) throws ParseException {
        JSONObject obj = new JSONObject();
        obj.put("email", user.getEmail());
        obj.put("first_name", user.getFirstName());
        obj.put("last_name", user.getLastName());
        obj.put("avatar", user.getAvatar());

        JSONParser parser = new JSONParser();
        String result = null;
        try {
            result = Request.makeHttpRequest("https://reqres.in/api/users", HttpMethods.POST, obj);
        } catch (BadRequestException | UnauthorizedException | NotFoundException e) {
            return (JSONObject) parser.parse(e.getMessage());
        }
        return (JSONObject) parser.parse(result);
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject updateUser(Integer id, User user) throws ParseException {
        JSONObject obj = new JSONObject();
        obj.put("email", user.getEmail());
        obj.put("first_name", user.getFirstName());
        obj.put("last_name", user.getLastName());
        obj.put("avatar", user.getAvatar());

        JSONParser parser = new JSONParser();
        String result = null;
        try {
            result = Request.makeHttpRequest("https://reqres.in/api/users/" + id, HttpMethods.PUT, obj);
        } catch (BadRequestException | UnauthorizedException | NotFoundException e) {
            return (JSONObject) parser.parse(e.getMessage());
        }
        return (JSONObject) parser.parse(result);
    }

    @Override
    public void deleteUser(Integer id) {
        try {
            Request.makeHttpRequest("https://reqres.in/api/users/" + id, HttpMethods.DELETE, null);
        } catch (BadRequestException | UnauthorizedException | NotFoundException e) {
            e.printStackTrace();
        }
    }

}