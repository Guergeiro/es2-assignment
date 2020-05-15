package com.brenosalles.reqres.api.implementation;

import com.brenosalles.reqres.api.IReqresAuthentication;
import com.brenosalles.reqres.http.HttpMethods;
import com.brenosalles.reqres.http.Request;
import com.brenosalles.reqres.http.Response;
import com.brenosalles.tokens.Token;
import com.brenosalles.tokens.TokenFactory;
import com.brenosalles.users.User;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReqresAuthentication implements IReqresAuthentication {
    @Override
    @SuppressWarnings("unchecked")
    public Token register(User user, String password) {
        JSONObject obj = new JSONObject();
        obj.put("email", user.getEmail());
        obj.put("password", password);

        Response response = Request.makeHttpRequest("https://reqres.in/api/register", HttpMethods.POST,
                obj.toJSONString());

        if (response.getStatusCode() < 400) {
            JSONParser parser = new JSONParser();
            try {
                JSONObject message = (JSONObject) parser.parse(response.getBody());
                return TokenFactory.createToken(user, (String) message.get("token"));
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }

        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Token login(User user, String password) {
        JSONObject obj = new JSONObject();
        obj.put("email", user.getEmail());
        obj.put("password", password);

        Response response = Request.makeHttpRequest("https://reqres.in/api/login", HttpMethods.POST,
                obj.toJSONString());

        if (response.getStatusCode() < 400) {
            JSONParser parser = new JSONParser();
            try {
                JSONObject message = (JSONObject) parser.parse(response.getBody());
                return TokenFactory.createToken(user, (String) message.get("token"));
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }

        }
        return null;
    }
}