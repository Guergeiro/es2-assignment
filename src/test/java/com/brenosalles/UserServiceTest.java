package com.brenosalles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.brenosalles.reqres.http.HttpMethods;
import com.brenosalles.reqres.http.Request;
import com.brenosalles.reqres.http.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class UserServiceTest {
    @ParameterizedTest
    @ValueSource(strings = { "https://reqres.in/api/users", "https://reqres.in/api/users?page=1",
            "https://reqres.in/api/users?page=2" })
    public void getUsersOk(String url) throws ParseException {
        Response response = Request.makeHttpRequest(url, HttpMethods.GET, null);
        assertEquals(200, response.getStatusCode());
        assertTrue(response.getContentTypes().contains("application/json"));

        JSONParser parser = new JSONParser();
        JSONObject message = (JSONObject) parser.parse(response.getBody());
        assertNotNull(message);

        assertNotNull(message.get("page"));
        assertNotNull(message.get("per_page"));
        assertNotNull(message.get("total"));
        assertNotNull(message.get("total_pages"));
        assertNotNull(message.get("data"));

        assertTrue((Long) message.get("page") > 0);
        assertTrue((Long) message.get("per_page") >= 0);
        assertTrue((Long) message.get("total") >= 0);
        assertTrue((Long) message.get("total_pages") > 0);

        JSONArray arr = (JSONArray) message.get("data");
        assertEquals((Long) message.get("per_page"), arr.size());

        for (Object object : arr) {
            JSONObject user = (JSONObject) object;
            assertNotNull(user.get("id"));
            assertTrue((Long) user.get("id") > 0);

            assertNotNull(user.get("email"));
            assertTrue(((String) user.get("email")).matches("^.*@.*\\..*$"));

            assertNotNull(user.get("first_name"));
            assertTrue(((String) user.get("first_name")).length() > 0);

            assertNotNull(user.get("last_name"));
            assertTrue(((String) user.get("last_name")).length() > 0);

            assertNotNull(user.get("avatar"));

        }
    }

    @ParameterizedTest
    @ValueSource(strings = { "https://reqres.in/api/users?page=", "https://reqres.in/api/users?page=0" })
    public void getUsersInvalidParam(String url) throws ParseException {
        Response response = Request.makeHttpRequest(url, HttpMethods.GET, null);
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
    @ValueSource(strings = { "https://reqres.in/api/users/1", "https://reqres.in/api/users/2",
            "https://reqres.in/api/users/3" })
    public void getUserOk(String url) throws ParseException {
        Response response = Request.makeHttpRequest(url, HttpMethods.GET, null);
        assertEquals(200, response.getStatusCode());
        assertTrue(response.getContentTypes().contains("application/json"));

        JSONParser parser = new JSONParser();
        JSONObject message = (JSONObject) parser.parse(response.getBody());
        assertNotNull(message);

        assertNotNull(message.get("data"));

        JSONObject user = (JSONObject) message.get("data");

        assertNotNull(user.get("id"));
        assertTrue((Long) user.get("id") > 0);

        assertNotNull(user.get("email"));
        assertTrue(((String) user.get("email")).matches("^.*@.*\\..*$"));

        assertNotNull(user.get("first_name"));
        assertTrue(((String) user.get("first_name")).length() > 0);

        assertNotNull(user.get("last_name"));
        assertTrue(((String) user.get("last_name")).length() > 0);

        assertNotNull(user.get("avatar"));

    }

    @ParameterizedTest
    @ValueSource(strings = { "https://reqres.in/api/users/0", "https://reqres.in/api/users/ola" })
    public void getUserInvalidId(String url) throws ParseException {
        Response response = Request.makeHttpRequest(url, HttpMethods.GET, null);
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
    public void createUserOk() throws ParseException {
        JSONObject obj = new JSONObject();
        obj.put("email", "breno@breno.com");
        obj.put("first_name", "Breno");
        obj.put("last_name", "Salles");
        obj.put("avatar", "https://s3.amazonaws.com/uifaces/faces/twitter/follettkyle/128.jpg");

        Response response = Request.makeHttpRequest("https://reqres.in/api/users", HttpMethods.POST,
                obj.toJSONString());
        assertEquals(201, response.getStatusCode());
        assertTrue(response.getContentTypes().contains("application/json"));

        JSONParser parser = new JSONParser();
        JSONObject user = (JSONObject) parser.parse(response.getBody());

        assertNotNull(user);

        assertNotNull(user.get("id"));
        assertTrue(Integer.parseInt((String) user.get("id")) > 0);

        assertNotNull(user.get("email"));
        assertTrue(((String) user.get("email")).matches("^.*@.*\\..*$"));

        assertNotNull(user.get("first_name"));
        assertTrue(((String) user.get("first_name")).length() > 0);

        assertNotNull(user.get("last_name"));
        assertTrue(((String) user.get("last_name")).length() > 0);

        assertNotNull(user.get("avatar"));
    }

    @ParameterizedTest
    @ValueSource(strings = { "https://reqres.in/api/users/1", "https://reqres.in/api/users/ola" })
    @SuppressWarnings("unchecked")
    public void createUserInvalidUrl(String url) throws ParseException {
        JSONObject obj = new JSONObject();
        obj.put("email", "breno@breno.com");
        obj.put("first_name", "Breno");
        obj.put("last_name", "Salles");
        obj.put("avatar", "https://s3.amazonaws.com/uifaces/faces/twitter/follettkyle/128.jpg");

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
    public void createUserInvalidJson(String json) throws ParseException {
        Response response = Request.makeHttpRequest("https://reqres.in/api/users", HttpMethods.POST, json);
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
    public void createUserWithExistingEmail() throws ParseException {
        JSONObject obj = new JSONObject();
        obj.put("email", "michael.lawson@reqres.in");
        obj.put("first_name", "Breno");
        obj.put("last_name", "Salles");
        obj.put("avatar", "https://s3.amazonaws.com/uifaces/faces/twitter/follettkyle/128.jpg");

        Response response = Request.makeHttpRequest("https://reqres.in/api/users", HttpMethods.POST,
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
    public void updateUserOk() throws ParseException {
        JSONObject obj = new JSONObject();
        obj.put("email", "breno@breno.com");
        obj.put("first_name", "Breno");
        obj.put("last_name", "Salles");
        obj.put("avatar", "https://s3.amazonaws.com/uifaces/faces/twitter/follettkyle/128.jpg");

        Response response = Request.makeHttpRequest("https://reqres.in/api/users/1", HttpMethods.POST,
                obj.toJSONString());
        assertEquals(200, response.getStatusCode());
        assertTrue(response.getContentTypes().contains("application/json"));

        JSONParser parser = new JSONParser();
        JSONObject user = (JSONObject) parser.parse(response.getBody());

        assertNotNull(user);

        assertNotNull(user.get("id"));
        assertTrue(Integer.parseInt((String) user.get("id")) > 0);

        assertNotNull(user.get("email"));
        assertTrue(((String) user.get("email")).matches("^.*@.*\\..*$"));

        assertNotNull(user.get("first_name"));
        assertTrue(((String) user.get("first_name")).length() > 0);

        assertNotNull(user.get("last_name"));
        assertTrue(((String) user.get("last_name")).length() > 0);

        assertNotNull(user.get("avatar"));
    }

    @ParameterizedTest
    @ValueSource(strings = { "https://reqres.in/api/users", "https://reqres.in/api/users/0",
            "https://reqres.in/api/users/500" })
    @SuppressWarnings("unchecked")
    public void updateUserInvalidUrl(String url) throws ParseException {
        JSONObject obj = new JSONObject();
        obj.put("email", "breno@breno.com");
        obj.put("first_name", "Breno");
        obj.put("last_name", "Salles");
        obj.put("avatar", "https://s3.amazonaws.com/uifaces/faces/twitter/follettkyle/128.jpg");

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
    public void updateUserInvalidJson(String json) throws ParseException {
        Response response = Request.makeHttpRequest("https://reqres.in/api/users/1", HttpMethods.POST, json);
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
    public void deleteUserOk() {
        Response response = Request.makeHttpRequest("https://reqres.in/api/users/1", HttpMethods.DELETE, null);
        assertEquals(204, response.getStatusCode());
    }

    @ParameterizedTest
    @ValueSource(strings = { "https://reqres.in/api/users", "https://reqres.in/api/users/0",
            "https://reqres.in/api/users/500" })
    public void deleteUserInvalidUrl(String url) {
        Response response = Request.makeHttpRequest(url, HttpMethods.DELETE, null);
        assertFalse(response.getStatusCode() < 400);
        assertFalse(response.getStatusCode() >= 500);
    }
}