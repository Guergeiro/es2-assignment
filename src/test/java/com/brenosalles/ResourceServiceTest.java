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

public class ResourceServiceTest {
    @ParameterizedTest
    @ValueSource(strings = { "https://reqres.in/api/resources", "https://reqres.in/api/resources?page=1",
            "https://reqres.in/api/resources?page=2" })
    public void getResourcesOk(String url) throws ParseException {
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
            JSONObject resource = (JSONObject) object;
            assertNotNull(resource.get("id"));
            assertTrue((Long) resource.get("id") > 0);

            assertNotNull(resource.get("name"));
            assertTrue(((String) resource.get("name")).length() > 0);

            assertNotNull(resource.get("year"));
            assertTrue((Long) resource.get("year") > 0);

            assertNotNull(resource.get("color"));
            assertTrue(((String) resource.get("color")).length() > 0);

            assertNotNull(resource.get("pantone_value"));
            assertTrue(((String) resource.get("pantone_value")).length() > 0);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = { "https://reqres.in/api/resources?page=", "https://reqres.in/api/resources?page=0" })
    public void getResourcesInvalidParam(String url) throws ParseException {
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
    @ValueSource(strings = { "https://reqres.in/api/resources/1", "https://reqres.in/api/resources/2",
            "https://reqres.in/api/resources/3" })
    public void getResourceOk(String url) throws ParseException {
        Response response = Request.makeHttpRequest(url, HttpMethods.GET, null);
        assertEquals(200, response.getStatusCode());
        assertTrue(response.getContentTypes().contains("application/json"));

        JSONParser parser = new JSONParser();
        JSONObject message = (JSONObject) parser.parse(response.getBody());
        assertNotNull(message);

        assertNotNull(message.get("data"));

        JSONObject resource = (JSONObject) message.get("data");

        assertNotNull(resource.get("id"));
        assertTrue((Long) resource.get("id") > 0);

        assertNotNull(resource.get("name"));
        assertTrue(((String) resource.get("name")).length() > 0);

        assertNotNull(resource.get("year"));
        assertTrue((Long) resource.get("year") > 0);

        assertNotNull(resource.get("color"));
        assertTrue(((String) resource.get("color")).length() > 0);

        assertNotNull(resource.get("pantone_value"));
        assertTrue(((String) resource.get("pantone_value")).length() > 0);
    }

    @ParameterizedTest
    @ValueSource(strings = { "https://reqres.in/api/resources/0", "https://reqres.in/api/resources/ola" })
    public void getResourceInvalidId(String url) throws ParseException {
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
    public void createResourceOk() throws ParseException {
        JSONObject obj = new JSONObject();
        obj.put("name", "random name");
        obj.put("year", 2000);
        obj.put("color", "#123123");
        obj.put("pantone_value", "17-2020");

        Response response = Request.makeHttpRequest("https://reqres.in/api/resources", HttpMethods.POST,
                obj.toJSONString());
        assertEquals(201, response.getStatusCode());
        assertTrue(response.getContentTypes().contains("application/json"));

        JSONParser parser = new JSONParser();
        JSONObject resource = (JSONObject) parser.parse(response.getBody());

        assertNotNull(resource);

        assertNotNull(resource.get("id"));
        assertTrue(Integer.parseInt((String) resource.get("id")) > 0);

        assertNotNull(resource.get("name"));
        assertTrue(((String) resource.get("name")).length() > 0);

        assertNotNull(resource.get("year"));
        assertTrue((Long) resource.get("year") > 0);

        assertNotNull(resource.get("color"));
        assertTrue(((String) resource.get("color")).length() > 0);

        assertNotNull(resource.get("pantone_value"));
        assertTrue(((String) resource.get("pantone_value")).length() > 0);
    }

    @ParameterizedTest
    @ValueSource(strings = { "https://reqres.in/api/resources/1", "https://reqres.in/api/resources/ola" })
    @SuppressWarnings("unchecked")
    public void createResourceInvalidUrl(String url) throws ParseException {
        JSONObject obj = new JSONObject();
        obj.put("name", "random name");
        obj.put("year", 2000);
        obj.put("color", "#123123");
        obj.put("pantone_value", "17-2020");

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
    public void createResourceInvalidJson(String json) throws ParseException {
        Response response = Request.makeHttpRequest("https://reqres.in/api/resources", HttpMethods.POST, json);
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
    public void updateResourceOk() throws ParseException {
        JSONObject obj = new JSONObject();
        obj.put("name", "random name");
        obj.put("year", 2000);
        obj.put("color", "#123123");
        obj.put("pantone_value", "17-2020");

        Response response = Request.makeHttpRequest("https://reqres.in/api/resources/1", HttpMethods.POST,
                obj.toJSONString());
        assertEquals(200, response.getStatusCode());
        assertTrue(response.getContentTypes().contains("application/json"));

        JSONParser parser = new JSONParser();
        JSONObject resource = (JSONObject) parser.parse(response.getBody());

        assertNotNull(resource);

        assertNotNull(resource.get("id"));
        assertTrue(Integer.parseInt((String) resource.get("id")) > 0);

        assertNotNull(resource.get("name"));
        assertTrue(((String) resource.get("name")).length() > 0);

        assertNotNull(resource.get("year"));
        assertTrue((Long) resource.get("year") > 0);

        assertNotNull(resource.get("color"));
        assertTrue(((String) resource.get("color")).length() > 0);

        assertNotNull(resource.get("pantone_value"));
        assertTrue(((String) resource.get("pantone_value")).length() > 0);
    }

    @ParameterizedTest
    @ValueSource(strings = { "https://reqres.in/api/resources", "https://reqres.in/api/resources/0",
            "https://reqres.in/api/resources/500" })
    @SuppressWarnings("unchecked")
    public void updateResourceInvalidUrl(String url) throws ParseException {
        JSONObject obj = new JSONObject();
        obj.put("name", "random name");
        obj.put("year", 2000);
        obj.put("color", "#123123");
        obj.put("pantone_value", "17-2020");

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
    public void updateResourceInvalidJson(String json) throws ParseException {
        Response response = Request.makeHttpRequest("https://reqres.in/api/resources/1", HttpMethods.POST, json);
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
    public void deleteResourceOk() {
        Response response = Request.makeHttpRequest("https://reqres.in/api/resources/1", HttpMethods.DELETE, null);
        assertEquals(204, response.getStatusCode());
    }

    @ParameterizedTest
    @ValueSource(strings = { "https://reqres.in/api/resources", "https://reqres.in/api/resources/0",
            "https://reqres.in/api/resources/500" })
    public void deleteResourceInvalidUrl(String url) {
        Response response = Request.makeHttpRequest(url, HttpMethods.DELETE, null);
        assertFalse(response.getStatusCode() < 400);
        assertFalse(response.getStatusCode() >= 500);
    }
}