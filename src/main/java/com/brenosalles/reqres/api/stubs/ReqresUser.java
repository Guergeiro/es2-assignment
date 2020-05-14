package com.brenosalles.reqres.api.stubs;

import com.brenosalles.reqres.api.IReqresUser;
import com.brenosalles.reqres.http.Response;
import com.brenosalles.users.User;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ReqresUser implements IReqresUser {
    @Override
    @SuppressWarnings("unchecked")
    public Response readUsers() {
        JSONArray arr = new JSONArray();
        for (Integer i = 1; i < 5; i++) {
            JSONObject obj = new JSONObject();
            obj.put("id", i);
            obj.put("email", i + "@email.com");
            obj.put("first_name", i + "FName");
            obj.put("last_name", i + "LName");
            obj.put("avatar", "https://" + i + ".com");
            arr.add(obj);
        }
        return new Response(200, arr.toJSONString());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Response readUser(Integer id) {
        JSONObject obj = new JSONObject();
        if (id == 0) {
            obj.put("error", "Resource not found");
            return new Response(404, obj.toJSONString());
        }
        obj.put("id", id);
        obj.put("email", 1 + "@email.com");
        obj.put("first_name", 1 + "FName");
        obj.put("last_name", 1 + "LName");
        obj.put("avatar", "https://" + 1 + ".com");
        return new Response(200, obj.toJSONString());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Response createUser(User user) {
        JSONObject obj = new JSONObject();
        if (user.getId() != null) {
            obj.put("error", "Bad request");
            return new Response(400, obj.toJSONString());
        }
        obj.put("id", 1);
        obj.put("email", user.getEmail());
        obj.put("first_name", user.getFirstName());
        obj.put("last_name", user.getLastName());
        obj.put("avatar", user.getAvatar());
        return new Response(201, obj.toJSONString());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Response updateUser(Integer id, User user) {
        JSONObject obj = new JSONObject();
        if (id == 0) {
            obj.put("error", "Resource not found");
            return new Response(404, obj.toJSONString());
        }
        if (user.getId() != null) {
            obj.put("error", "Bad request");
            return new Response(400, obj.toJSONString());
        }
        obj.put("id", id);
        obj.put("email", user.getEmail());
        obj.put("first_name", user.getFirstName());
        obj.put("last_name", user.getLastName());
        obj.put("avatar", user.getAvatar());
        return new Response(200, obj.toJSONString());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Response deleteUser(Integer id) {
        JSONObject obj = new JSONObject();
        if (id == 0) {
            obj.put("error", "Resource not found");
            return new Response(404, obj.toJSONString());
        }
        return new Response(204, "");
    }

}