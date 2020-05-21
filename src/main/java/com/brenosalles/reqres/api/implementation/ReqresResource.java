package com.brenosalles.reqres.api.implementation;

import java.util.ArrayList;

import com.brenosalles.reqres.api.IReqresResource;
import com.brenosalles.reqres.http.HttpMethods;
import com.brenosalles.reqres.http.Request;
import com.brenosalles.reqres.http.Response;
import com.brenosalles.resources.InvalidResourceException;
import com.brenosalles.resources.Resource;
import com.brenosalles.resources.ResourceFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReqresResource implements IReqresResource {

    @Override
    @SuppressWarnings("unchecked")
    public ArrayList<Resource> readResources() {
        Response response = Request.makeHttpRequest("https://reqres.in/api/resources", HttpMethods.GET, null);
        if (response.getStatusCode() < 400) {
            JSONParser parser = new JSONParser();
            try {
                JSONObject message = (JSONObject) parser.parse(response.getBody());
                JSONArray arr = (JSONArray) message.get("data");
                return new ArrayList<Resource>(arr);
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }

        }
        return null;
    }

    @Override
    public Resource readResource(Integer id) throws InvalidResourceException {
        Response response = Request.makeHttpRequest("https://reqres.in/api/resources/" + id, HttpMethods.GET, null);
        if (response.getStatusCode() < 400) {
            JSONParser parser = new JSONParser();
            try {
                JSONObject message = (JSONObject) parser.parse(response.getBody());
                JSONObject obj = (JSONObject) message.get("data");
                return ResourceFactory.createResource((Integer) obj.get("id"), (String) obj.get("name"),
                        (Integer) obj.get("year"), (String) obj.get("color"), (String) obj.get("pantone_value"));
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }

        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Resource createResource(Resource resource) throws InvalidResourceException {
        JSONObject obj = new JSONObject();
        obj.put("name", resource.getName());
        obj.put("year", resource.getYear());
        obj.put("color", resource.getColor());
        obj.put("pantone_value", resource.getPantoneValue());

        Response response = Request.makeHttpRequest("https://reqres.in/api/resources", HttpMethods.POST,
                obj.toJSONString());

        if (response.getStatusCode() < 400) {
            JSONParser parser = new JSONParser();
            try {
                JSONObject message = (JSONObject) parser.parse(response.getBody());
                JSONObject obj2 = (JSONObject) message.get("message");
                return ResourceFactory.createResource((Integer) obj2.get("id"), (String) obj2.get("name"),
                        (Integer) obj2.get("year"), (String) obj2.get("color"), (String) obj2.get("pantone_value"));
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }

        }

        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Boolean updateResource(Integer id, Resource resource) {
        JSONObject obj = new JSONObject();
        obj.put("name", resource.getName());
        obj.put("year", resource.getYear());
        obj.put("color", resource.getColor());
        obj.put("pantone_value", resource.getPantoneValue());

        Response response = Request.makeHttpRequest("https://reqres.in/api/resources/" + id, HttpMethods.PUT,
                obj.toJSONString());

        return response.getStatusCode() < 400;
    }

    @Override
    public Boolean deleteResource(Integer id) {
        Response response = Request.makeHttpRequest("https://reqres.in/api/resources/" + id, HttpMethods.DELETE, null);
        return response.getStatusCode() < 400;
    }
}