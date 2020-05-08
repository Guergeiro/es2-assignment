package com.brenosalles.reqres.stubs;

import com.brenosalles.reqres.IReqresAuthentication;
import com.brenosalles.reqres.exceptions.BadRequestException;
import com.brenosalles.reqres.exceptions.UnauthorizedException;
import com.brenosalles.users.User;

import org.json.simple.JSONObject;

public class ReqresAuthentication implements IReqresAuthentication {
    @Override
    @SuppressWarnings("unchecked")
    public JSONObject register(User user, String password) throws BadRequestException {
        if (user == null) {
            throw new BadRequestException("Missing email or username");
        }
        if (user.getEmail() == null) {
            throw new BadRequestException("Missing email or username");
        }
        if (password == null) {
            throw new BadRequestException("Missing password");
        }
        JSONObject obj = new JSONObject();
        obj.put("id", 4);
        obj.put("token", "QpwL5tke4Pnpja7X4");
        return obj;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject login(User user, String password) throws BadRequestException, UnauthorizedException {
        if (user == null) {
            throw new BadRequestException("Missing email or username");
        }
        if (user.getEmail() == null) {
            throw new BadRequestException("Missing email or username");
        }
        if (password == null) {
            throw new BadRequestException("Missing password");
        }
        JSONObject obj = new JSONObject();
        obj.put("token", "QpwL5tke4Pnpja7X4");
        return obj;
    }

}