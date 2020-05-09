package com.brenosalles.reqres.implementation;

import com.brenosalles.reqres.IReqresAuthentication;
import com.brenosalles.reqres.http.HttpMethods;
import com.brenosalles.reqres.http.Request;
import com.brenosalles.reqres.http.Response;
import com.brenosalles.users.User;

import org.json.simple.JSONObject;

public class ReqresAuthentication implements IReqresAuthentication {
    @Override
    @SuppressWarnings("unchecked")
    public Response register(User user, String password) {
        JSONObject obj = new JSONObject();
        obj.put("email", user.getEmail());
        obj.put("password", password);

        return Request.makeHttpRequest("https://reqres.in/api/register", HttpMethods.POST, obj.toJSONString());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Response login(User user, String password) {
        JSONObject obj = new JSONObject();
        obj.put("email", user.getEmail());
        obj.put("password", password);

        return Request.makeHttpRequest("https://reqres.in/api/register", HttpMethods.POST, obj.toJSONString());
    }
}