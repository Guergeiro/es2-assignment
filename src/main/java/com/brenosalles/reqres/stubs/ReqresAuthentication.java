package com.brenosalles.reqres.stubs;

import com.brenosalles.reqres.IReqresAuthentication;
import com.brenosalles.users.User;

import org.json.simple.JSONObject;

public class ReqresAuthentication implements IReqresAuthentication {
    @Override
    @SuppressWarnings("unchecked")
    public JSONObject register(User user, String password) {
        JSONObject obj = new JSONObject();
        if (user == null) {
            obj.put("error", "Missing email or username");
            return obj;
        }
        if (user.getEmail() == null) {
            obj.put("error", "Missing email or username");
            return obj;
        }
        if (password == null) {
            obj.put("error", "Missing password");
            return obj;
        }
        obj.put("id", 4);
        obj.put("token", "QpwL5tke4Pnpja7X4");
        return obj;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject login(User user, String password) {
        JSONObject obj = new JSONObject();
        if (user == null) {
            obj.put("error", "Missing email or username");
            return obj;
        }
        if (user.getEmail() == null) {
            obj.put("error", "Missing email or username");
        }
        if (password == null) {
            obj.put("error", "Missing password");
        }
        obj.put("token", "QpwL5tke4Pnpja7X4");
        return obj;
    }

}