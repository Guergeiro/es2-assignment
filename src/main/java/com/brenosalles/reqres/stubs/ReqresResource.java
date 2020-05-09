package com.brenosalles.reqres.stubs;

import com.brenosalles.reqres.IReqresResource;
import com.brenosalles.resources.Resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ReqresResource implements IReqresResource {
    @Override
    @SuppressWarnings("unchecked")
    public JSONArray readResources() {
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
        return arr;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject readResource(Integer id) {
        JSONObject obj = new JSONObject();
        if (id == 0) {
            obj.put("error", "Resource not found");
            return obj;
        }
        obj.put("id", id);
        obj.put("name", 1 + "Name");
        obj.put("year", 2000 + 1);
        obj.put("color", "#12345" + 1);
        obj.put("pantone_value", "17-203" + 1);
        return obj;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject createResource(Resource resource) {
        JSONObject obj = new JSONObject();
        if (resource.getId() != null) {
            obj.put("error", "Bad request");
            return obj;
        }
        obj.put("id", 1);
        obj.put("name", resource.getName());
        obj.put("year", resource.getYear());
        obj.put("color", resource.getColor());
        obj.put("pantone_value", resource.getPantoneValue());
        return obj;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject updateResource(Integer id, Resource resource) {
        JSONObject obj = new JSONObject();
        if (id == 0) {
            obj.put("error", "Resource not found");
            return obj;
        }
        if (resource.getId() != null) {
            obj.put("error", "Bad request");
            return obj;
        }
        obj.put("id", id);
        obj.put("name", resource.getName());
        obj.put("year", resource.getYear());
        obj.put("color", resource.getColor());
        obj.put("pantone_value", resource.getPantoneValue());
        return obj;
    }

    @Override
    public void deleteResource(Integer id) {
        return;
    }
}