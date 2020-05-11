package com.brenosalles.reqres.api.stubs;

import com.brenosalles.reqres.api.IReqresAuthentication;
import com.brenosalles.reqres.http.Response;
import com.brenosalles.users.User;

import org.json.simple.JSONObject;

public class ReqresAuthentication implements IReqresAuthentication {
    @Override
    @SuppressWarnings("unchecked")
    public Response register(User user, String password) {
        JSONObject obj = new JSONObject();
        if (user == null) {
            obj.put("error", "Missing email or username");
            return new Response(400, obj.toJSONString());
        }
        if (user.getEmail() == null) {
            obj.put("error", "Missing email or username");
            return new Response(400, obj.toJSONString());
        }
        if (password == null) {
            obj.put("error", "Missing password");
            return new Response(400, obj.toJSONString());
        }
        obj.put("id", 4);
        obj.put("token", "QpwL5tke4Pnpja7X4");
        return new Response(200, obj.toJSONString());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Response login(User user, String password) {
        JSONObject obj = new JSONObject();
        if (user == null) {
            obj.put("error", "Missing email or username");
            return new Response(400, obj.toJSONString());
        }
        if (user.getEmail() == null) {
            obj.put("error", "Missing email or username");
            return new Response(400, obj.toJSONString());
        }
        if (password == null) {
            obj.put("error", "Missing password");
            return new Response(400, obj.toJSONString());
        }
        obj.put("token", "QpwL5tke4Pnpja7X4");
        return new Response(200, obj.toJSONString());
    }

}