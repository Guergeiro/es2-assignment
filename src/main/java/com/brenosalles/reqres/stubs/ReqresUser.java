package com.brenosalles.reqres.stubs;

import com.brenosalles.reqres.IReqresUser;
import com.brenosalles.reqres.http.BadRequestException;
import com.brenosalles.reqres.http.NotFoundException;
import com.brenosalles.users.User;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ReqresUser implements IReqresUser {
    @Override
    @SuppressWarnings("unchecked")
    public JSONArray readUsers() {
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
        return arr;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject readUser(Integer id) throws NotFoundException {
        if (id == 0) {
            throw new NotFoundException();
        }
        JSONObject obj = new JSONObject();
        obj.put("id", id);
        obj.put("email", 1 + "@email.com");
        obj.put("first_name", 1 + "FName");
        obj.put("last_name", 1 + "LName");
        obj.put("avatar", "https://" + 1 + ".com");
        return obj;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject createUser(User user) throws BadRequestException {
        if (user.getId() != null) {
            throw new BadRequestException();
        }
        JSONObject obj = new JSONObject();
        obj.put("id", 1);
        obj.put("email", user.getEmail());
        obj.put("first_name", user.getFirstName());
        obj.put("last_name", user.getLastName());
        obj.put("avatar", user.getAvatar());
        return obj;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject updateUser(Integer id, User user) throws NotFoundException, BadRequestException {
        if (id == 0) {
            throw new NotFoundException();
        }
        if (user.getId() != null) {
            throw new BadRequestException();
        }
        JSONObject obj = new JSONObject();
        obj.put("id", id);
        obj.put("email", user.getEmail());
        obj.put("first_name", user.getFirstName());
        obj.put("last_name", user.getLastName());
        obj.put("avatar", user.getAvatar());
        return obj;
    }

    @Override
    public void deleteUser(Integer id) throws NotFoundException {
        if (id == 0) {
            throw new NotFoundException();
        }
        return;
    }

}