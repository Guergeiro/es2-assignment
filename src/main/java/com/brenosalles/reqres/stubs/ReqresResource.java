package com.brenosalles.reqres.stubs;

import com.brenosalles.reqres.IReqresResource;
import com.brenosalles.reqres.http.Response;
import com.brenosalles.resources.Resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ReqresResource implements IReqresResource {
    @Override
    @SuppressWarnings("unchecked")
    public Response readResources() {
        JSONArray arr = new JSONArray();
        for (Integer i = 1; i < 5; i++) {
            JSONObject obj = new JSONObject();
            obj.put("id", i);
            obj.put("name", i + "Name");
            obj.put("year", 2000 + i);
            obj.put("color", "#12345" + i);
            obj.put("pantone_value", "17-203" + i);
            arr.add(obj);
        }
        return new Response(200, arr.toJSONString());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Response readResource(Integer id) {
        JSONObject obj = new JSONObject();
        if (id == 0) {
            obj.put("error", "Resource not found");
            return new Response(400, obj.toJSONString());
        }
        obj.put("id", id);
        obj.put("name", 1 + "Name");
        obj.put("year", 2000 + 1);
        obj.put("color", "#12345" + 1);
        obj.put("pantone_value", "17-203" + 1);
        return new Response(200, obj.toJSONString());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Response createResource(Resource resource) {
        JSONObject obj = new JSONObject();
        if (resource.getId() != null) {
            obj.put("error", "Bad request");
            return new Response(400, obj.toJSONString());
        }
        obj.put("id", 1);
        obj.put("name", resource.getName());
        obj.put("year", resource.getYear());
        obj.put("color", resource.getColor());
        obj.put("pantone_value", resource.getPantoneValue());
        return new Response(201, obj.toJSONString());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Response updateResource(Integer id, Resource resource) {
        JSONObject obj = new JSONObject();
        if (id == 0) {
            obj.put("error", "Resource not found");
            return new Response(404, obj.toJSONString());
        }
        if (resource.getId() != null) {
            obj.put("error", "Bad request");
            return new Response(400, obj.toJSONString());
        }
        obj.put("id", id);
        obj.put("name", resource.getName());
        obj.put("year", resource.getYear());
        obj.put("color", resource.getColor());
        obj.put("pantone_value", resource.getPantoneValue());
        return new Response(200, obj.toJSONString());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Response deleteResource(Integer id) {
        JSONObject obj = new JSONObject();
        if (id == 0) {
            obj.put("error", "Resource not found");
            return new Response(404, obj.toJSONString());
        }
        return new Response(204, "");
    }
}