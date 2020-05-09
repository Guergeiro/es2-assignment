package com.brenosalles.reqres.implementation;

import com.brenosalles.reqres.IReqresResource;
import com.brenosalles.reqres.http.HttpMethods;
import com.brenosalles.reqres.http.Request;
import com.brenosalles.reqres.http.Response;
import com.brenosalles.resources.Resource;

import org.json.simple.JSONObject;

public class ReqresResource implements IReqresResource {

    @Override
    public Response readResources() {
        return Request.makeHttpRequest("https://reqres.in/api/resources", HttpMethods.GET, null);
    }

    @Override
    public Response readResource(Integer id) {
        return Request.makeHttpRequest("https://reqres.in/api/resources/" + id, HttpMethods.GET, null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Response createResource(Resource resource) {
        JSONObject obj = new JSONObject();
        obj.put("name", resource.getName());
        obj.put("year", resource.getYear());
        obj.put("color", resource.getColor());
        obj.put("pantone_value", resource.getPantoneValue());

        return Request.makeHttpRequest("https://reqres.in/api/resources", HttpMethods.POST, obj.toJSONString());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Response updateResource(Integer id, Resource resource) {
        JSONObject obj = new JSONObject();
        obj.put("name", resource.getName());
        obj.put("year", resource.getYear());
        obj.put("color", resource.getColor());
        obj.put("pantone_value", resource.getPantoneValue());
        return Request.makeHttpRequest("https://reqres.in/api/resources/" + id, HttpMethods.PUT, obj.toJSONString());
    }

    @Override
    public Response deleteResource(Integer id) {
        return Request.makeHttpRequest("https://reqres.in/api/resources/" + id, HttpMethods.DELETE, null);
    }
}