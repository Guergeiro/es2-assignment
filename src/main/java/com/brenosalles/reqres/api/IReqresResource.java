package com.brenosalles.reqres.api;

import java.util.ArrayList;

import com.brenosalles.resources.InvalidResourceException;
import com.brenosalles.resources.Resource;

public interface IReqresResource {
    ArrayList<Resource> readResources() throws InvalidResourceException;

    Resource readResource(Integer id) throws InvalidResourceException;

    Resource createResource(Resource resource) throws InvalidResourceException;

    Boolean updateResource(Integer id, Resource resource);

    Boolean deleteResource(Integer id);
}