package com.brenosalles.reqres.implementation;

import com.brenosalles.reqres.IReqresResource;
import com.brenosalles.reqres.http.BadRequestException;
import com.brenosalles.reqres.http.HttpMethods;
import com.brenosalles.reqres.http.NotFoundException;
import com.brenosalles.reqres.http.Request;
import com.brenosalles.reqres.http.UnauthorizedException;
import com.brenosalles.resources.Resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReqresResource implements IReqresResource {

    @Override
    public JSONArray readResources() throws ParseException {
        JSONParser parser = new JSONParser();
        String result = null;
        try {
            result = Request.makeHttpRequest("https://reqres.in/api/resources", HttpMethods.GET, null);
        } catch (BadRequestException | UnauthorizedException | NotFoundException e) {
            return new JSONArray();
        }

        return (JSONArray) parser.parse(result);
    }

    @Override
    public JSONObject readResource(Integer id) throws ParseException {
        JSONParser parser = new JSONParser();
        String result = null;
        try {
            result = Request.makeHttpRequest("https://reqres.in/api/resources/" + id, HttpMethods.GET, null);
        } catch (BadRequestException | UnauthorizedException | NotFoundException e) {
            return (JSONObject) parser.parse(e.getMessage());
        }

        return (JSONObject) parser.parse(result);
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject createResource(Resource resource) throws ParseException {
        JSONObject obj = new JSONObject();
        obj.put("name", resource.getName());
        obj.put("year", resource.getYear());
        obj.put("color", resource.getColor());
        obj.put("pantone_value", resource.getPantoneValue());

        JSONParser parser = new JSONParser();
        String result = null;
        try {
            result = Request.makeHttpRequest("https://reqres.in/api/resources", HttpMethods.POST, obj);
        } catch (BadRequestException | UnauthorizedException | NotFoundException e) {
            return (JSONObject) parser.parse(e.getMessage());
        }
        return (JSONObject) parser.parse(result);
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject updateResource(Integer id, Resource resource) throws ParseException {
        JSONObject obj = new JSONObject();
        obj.put("name", resource.getName());
        obj.put("year", resource.getYear());
        obj.put("color", resource.getColor());
        obj.put("pantone_value", resource.getPantoneValue());

        JSONParser parser = new JSONParser();
        String result = null;
        try {
            result = Request.makeHttpRequest("https://reqres.in/api/resources/" + id, HttpMethods.PUT, obj);
        } catch (BadRequestException | UnauthorizedException | NotFoundException e) {
            return (JSONObject) parser.parse(e.getMessage());
        }
        return (JSONObject) parser.parse(result);
    }

    @Override
    public void deleteResource(Integer id) {
        try {
            Request.makeHttpRequest("https://reqres.in/api/resources/" + id, HttpMethods.DELETE, null);
        } catch (BadRequestException | UnauthorizedException | NotFoundException e) {
            e.printStackTrace();
        }
    }

}