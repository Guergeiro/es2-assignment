package com.brenosalles.reqres.api.implementation;

import java.util.ArrayList;

import com.brenosalles.reqres.api.IReqresUser;
import com.brenosalles.reqres.http.HttpMethods;
import com.brenosalles.reqres.http.Request;
import com.brenosalles.reqres.http.Response;
import com.brenosalles.users.User;
import com.brenosalles.users.UserFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReqresUser implements IReqresUser {

    @Override
    @SuppressWarnings("unchecked")
    public ArrayList<User> readUsers() {
        Response response = Request.makeHttpRequest("https://reqres.in/api/users", HttpMethods.GET, null);
        if (response.getStatusCode() < 400) {
            JSONParser parser = new JSONParser();
            try {
                JSONArray arr = (JSONArray) parser.parse(response.getBody());
                return new ArrayList<User>(arr);
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }

        }
        return null;
    }

    @Override
    public User readUser(Integer id) {
        Response response = Request.makeHttpRequest("https://reqres.in/api/users/" + id, HttpMethods.GET, null);
        if (response.getStatusCode() < 400) {
            JSONParser parser = new JSONParser();
            try {
                JSONObject message = (JSONObject) parser.parse(response.getBody());
                JSONObject obj = (JSONObject) message.get("message");
                return UserFactory.createUser((Integer) obj.get("id"), (String) obj.get("email"),
                        (String) obj.get("first_name"), (String) obj.get("last_name"), (String) obj.get("avatar"));
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }

        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public User createUser(User user) {
        JSONObject obj = new JSONObject();
        obj.put("email", user.getEmail());
        obj.put("first_name", user.getFirstName());
        obj.put("last_name", user.getLastName());
        obj.put("avatar", user.getAvatar());

        Response response = Request.makeHttpRequest("https://reqres.in/api/users", HttpMethods.POST,
                obj.toJSONString());

        if (response.getStatusCode() < 400) {
            JSONParser parser = new JSONParser();
            try {
                JSONObject message = (JSONObject) parser.parse(response.getBody());
                JSONObject obj2 = (JSONObject) message.get("message");
                return UserFactory.createUser((Integer) obj2.get("id"), (String) obj2.get("email"),
                        (String) obj2.get("first_name"), (String) obj2.get("last_name"), (String) obj2.get("avatar"));
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }

        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Boolean updateUser(Integer id, User user) {
        JSONObject obj = new JSONObject();
        obj.put("email", user.getEmail());
        obj.put("first_name", user.getFirstName());
        obj.put("last_name", user.getLastName());
        obj.put("avatar", user.getAvatar());

        Response response = Request.makeHttpRequest("https://reqres.in/api/users/" + id, HttpMethods.PUT,
                obj.toJSONString());
        return response.getStatusCode() < 400;
    }

    @Override
    public Boolean deleteUser(Integer id) {
        Response response = Request.makeHttpRequest("https://reqres.in/api/users/" + id, HttpMethods.DELETE, null);
        return response.getStatusCode() < 400;
    }
}