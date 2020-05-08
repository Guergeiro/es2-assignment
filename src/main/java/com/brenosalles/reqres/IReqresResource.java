package com.brenosalles.reqres;

import com.brenosalles.resources.Resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public interface IReqresResource {
    JSONArray readResources() throws ParseException;

    JSONObject readResource(Integer id) throws ParseException;

    JSONObject createResource(Resource resource) throws ParseException;

    JSONObject updateResource(Integer id, Resource resource) throws ParseException;

    void deleteResource(Integer id);
}