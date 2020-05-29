package com.brenosalles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.brenosalles.reqres.http.HttpMethods;
import com.brenosalles.reqres.http.Request;
import com.brenosalles.reqres.http.Response;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class AuthenticationServiceTest {
    @Test
    @SuppressWarnings("unchecked")
    public void loginOk() throws ParseException {
        JSONObject obj = new JSONObject();
        obj.put("email", "eve.holt@reqres.in");
        obj.put("password", "cityslicka");

        Response response = Request.makeHttpRequest("https://reqres.in/api/login", HttpMethods.POST,
                obj.toJSONString());
        assertEquals(200, response.getStatusCode());
        assertTrue(response.getContentTypes().contains("application/json"));

        JSONParser parser = new JSONParser();
        JSONObject message = (JSONObject) parser.parse(response.getBody());
        assertNotNull(message);

        assertNotNull(message.get("token"));
        assertTrue(((String) message.get("token")).length() > 0);
    }

    @ParameterizedTest
    @ValueSource(strings = { "https://reqres.in/api/login/1", "https://reqres.in/api/login/ola" })
    @SuppressWarnings("unchecked")
    public void loginInvalidUrl(String url) throws ParseException {
        JSONObject obj = new JSONObject();
        obj.put("email", "eve.holt@reqres.in");
        obj.put("password", "cityslicka");

        Response response = Request.makeHttpRequest(url, HttpMethods.POST, obj.toJSONString());
        assertFalse(response.getStatusCode() < 400);
        assertFalse(response.getStatusCode() >= 500);
        assertTrue(response.getContentTypes().contains("application/json"));

        JSONParser parser = new JSONParser();
        JSONObject message = (JSONObject) parser.parse(response.getBody());
        assertNotNull(message);

        assertNotNull(message.get("error"));
        assertTrue(((String) message.get("error")).length() > 0);
    }

    @ParameterizedTest
    @ValueSource(strings = { "{\"invalidJson\":true", "{\"email\":\"breno@breno.com\"}" })
    public void loginInvalidJson(String json) throws ParseException {
        Response response = Request.makeHttpRequest("https://reqres.in/api/login", HttpMethods.POST, json);
        assertFalse(response.getStatusCode() < 400);
        assertFalse(response.getStatusCode() >= 500);
        assertTrue(response.getContentTypes().contains("application/json"));

        JSONParser parser = new JSONParser();
        JSONObject message = (JSONObject) parser.parse(response.getBody());

        assertNotNull(message);

        assertNotNull(message.get("error"));
        assertTrue(((String) message.get("error")).length() > 0);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void loginInvalidCredentials() throws ParseException {
        JSONObject obj = new JSONObject();
        obj.put("email", "breno@breno.com");
        obj.put("password", "cityslicka");
        Response response = Request.makeHttpRequest("https://reqres.in/api/login", HttpMethods.POST,
                obj.toJSONString());
        assertFalse(response.getStatusCode() < 400);
        assertFalse(response.getStatusCode() >= 500);
        assertTrue(response.getContentTypes().contains("application/json"));

        JSONParser parser = new JSONParser();
        JSONObject message = (JSONObject) parser.parse(response.getBody());

        assertNotNull(message);

        assertNotNull(message.get("error"));
        assertTrue(((String) message.get("error")).length() > 0);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void registerOk() throws ParseException {
        JSONObject obj = new JSONObject();
        obj.put("email", "eve.holt@reqres.in");
        obj.put("password", "cityslicka");

        Response response = Request.makeHttpRequest("https://reqres.in/api/register", HttpMethods.POST,
                obj.toJSONString());
        assertEquals(200, response.getStatusCode());
        assertTrue(response.getContentTypes().contains("application/json"));

        JSONParser parser = new JSONParser();
        JSONObject message = (JSONObject) parser.parse(response.getBody());
        assertNotNull(message);

        assertNotNull(message.get("token"));
        assertTrue(((String) message.get("token")).length() > 0);
        assertNotNull(message.get("id"));
        assertTrue(((Long) message.get("id")) > 0);
    }

    @ParameterizedTest
    @ValueSource(strings = { "https://reqres.in/api/register/1", "https://reqres.in/api/register/ola" })
    @SuppressWarnings("unchecked")
    public void registerInvalidUrl(String url) throws ParseException {
        JSONObject obj = new JSONObject();
        obj.put("email", "eve.holt@reqres.in");
        obj.put("password", "cityslicka");

        Response response = Request.makeHttpRequest(url, HttpMethods.POST, obj.toJSONString());
        assertFalse(response.getStatusCode() < 400);
        assertFalse(response.getStatusCode() >= 500);
        assertTrue(response.getContentTypes().contains("application/json"));

        JSONParser parser = new JSONParser();
        JSONObject message = (JSONObject) parser.parse(response.getBody());
        assertNotNull(message);

        assertNotNull(message.get("error"));
        assertTrue(((String) message.get("error")).length() > 0);
    }

    @ParameterizedTest
    @ValueSource(strings = { "{\"invalidJson\":true", "{\"email\":\"breno@breno.com\"}" })
    public void registerInvalidJson(String json) throws ParseException {
        Response response = Request.makeHttpRequest("https://reqres.in/api/register", HttpMethods.POST, json);
        assertFalse(response.getStatusCode() < 400);
        assertFalse(response.getStatusCode() >= 500);
        assertTrue(response.getContentTypes().contains("application/json"));

        JSONParser parser = new JSONParser();
        JSONObject message = (JSONObject) parser.parse(response.getBody());

        assertNotNull(message);

        assertNotNull(message.get("error"));
        assertTrue(((String) message.get("error")).length() > 0);
    }
}