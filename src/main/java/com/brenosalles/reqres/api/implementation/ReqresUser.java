package com.brenosalles.reqres.api.implementation;

import java.util.ArrayList;

import com.brenosalles.reqres.api.IReqresUser;
import com.brenosalles.reqres.http.HttpMethods;
import com.brenosalles.reqres.http.Request;
import com.brenosalles.reqres.http.Response;
import com.brenosalles.users.InvalidUserException;
import com.brenosalles.users.User;
import com.brenosalles.users.UserFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReqresUser implements IReqresUser {

    @Override
    public ArrayList<User> readUsers() throws InvalidUserException {
        Response response = Request.makeHttpRequest("https://reqres.in/api/users", HttpMethods.GET, null);
        if (response.getStatusCode() < 400) {
            JSONParser parser = new JSONParser();
            try {
                JSONObject message = (JSONObject) parser.parse(response.getBody());
                JSONArray arr = (JSONArray) message.get("data");
                ArrayList<User> users = new ArrayList<User>();
                for (Object user : arr) {
                    JSONObject obj = (JSONObject) user;
                    users.add(UserFactory.createUser(Math.toIntExact((Long) obj.get("id")), (String) obj.get("email"),
                            (String) obj.get("first_name"), (String) obj.get("last_name"), (String) obj.get("avatar")));
                }
                return users;
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }

        }
        return null;
    }

    @Override
    public User readUser(Integer id) throws InvalidUserException {
        Response response = Request.makeHttpRequest("https://reqres.in/api/users/" + id, HttpMethods.GET, null);
        if (response.getStatusCode() < 400) {
            JSONParser parser = new JSONParser();
            try {
                JSONObject message = (JSONObject) parser.parse(response.getBody());
                JSONObject obj = (JSONObject) message.get("data");
                return UserFactory.createUser(Math.toIntExact((Long) obj.get("id")), (String) obj.get("email"),
                        (String) obj.get("first_name"), (String) obj.get("last_name"), (String) obj.get("avatar"));
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }

        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public User createUser(User user) throws InvalidUserException {
        JSONObject obj = new JSONObject();
        obj.put("email", user.getEmail());
        obj.put("first_name", user.getFirstName());
        obj.put("last_name", user.getLastName());
        obj.put("avatar", user.getAvatar());

        Response response = Request.makeHttpRequest("https://reqres.in/api/users", HttpMethods.POST,
                obj.toJSONString());

        System.out.println(response.getBody());
        if (response.getStatusCode() < 400) {
            JSONParser parser = new JSONParser();
            try {
                JSONObject message = (JSONObject) parser.parse(response.getBody());
                return UserFactory.createUser(Integer.parseInt((String) message.get("id")),
                        (String) message.get("email"), (String) message.get("first_name"),
                        (String) message.get("last_name"), (String) message.get("avatar"));
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