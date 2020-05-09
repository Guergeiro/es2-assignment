package com.brenosalles.reqres.implementation;

import com.brenosalles.reqres.IReqresUser;
import com.brenosalles.reqres.http.HttpMethods;
import com.brenosalles.reqres.http.Request;
import com.brenosalles.reqres.http.Response;
import com.brenosalles.users.User;

import org.json.simple.JSONObject;

public class ReqresUser implements IReqresUser {

    @Override
    public Response readUsers() {
        return Request.makeHttpRequest("https://reqres.in/api/users", HttpMethods.GET, null);
    }

    @Override
    public Response readUser(Integer id) {
        return Request.makeHttpRequest("https://reqres.in/api/users/" + id, HttpMethods.GET, null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Response createUser(User user) {
        JSONObject obj = new JSONObject();
        obj.put("email", user.getEmail());
        obj.put("first_name", user.getFirstName());
        obj.put("last_name", user.getLastName());
        obj.put("avatar", user.getAvatar());

        return Request.makeHttpRequest("https://reqres.in/api/users", HttpMethods.POST, obj.toJSONString());

    }

    @Override
    @SuppressWarnings("unchecked")
    public Response updateUser(Integer id, User user) {
        JSONObject obj = new JSONObject();
        obj.put("email", user.getEmail());
        obj.put("first_name", user.getFirstName());
        obj.put("last_name", user.getLastName());
        obj.put("avatar", user.getAvatar());

        return Request.makeHttpRequest("https://reqres.in/api/users/" + id, HttpMethods.PUT, obj.toJSONString());
    }

    @Override
    public Response deleteUser(Integer id) {
        return Request.makeHttpRequest("https://reqres.in/api/users/" + id, HttpMethods.DELETE, null);
    }
}