package com.brenosalles.reqres;

import com.brenosalles.reqres.exceptions.BadRequestException;
import com.brenosalles.reqres.exceptions.NotFoundException;
import com.brenosalles.resources.Resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface IReqresResource {
    JSONArray readResources();

    JSONObject readResource(Integer id) throws NotFoundException;

    JSONObject createResource(Resource resource) throws BadRequestException;

    JSONObject updateResource(Integer id, Resource resource) throws NotFoundException, BadRequestException;

    void deleteResource(Integer id) throws NotFoundException;
}