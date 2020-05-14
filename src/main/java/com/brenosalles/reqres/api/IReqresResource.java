package com.brenosalles.reqres.api;

import java.util.ArrayList;

import com.brenosalles.resources.Resource;

public interface IReqresResource {
    ArrayList<Resource> readResources();

    Resource readResource(Integer id);

    Resource createResource(Resource resource);

    Boolean updateResource(Integer id, Resource resource);

    Boolean deleteResource(Integer id);
}