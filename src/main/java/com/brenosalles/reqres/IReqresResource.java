package com.brenosalles.reqres;

import com.brenosalles.resources.Resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface IReqresResource {
    JSONArray readResources();

    JSONObject readResource(Integer id);

    JSONObject createResource(Resource resource);

    JSONObject updateResource(Integer id, Resource resource);

    void deleteResource(Integer id);
}