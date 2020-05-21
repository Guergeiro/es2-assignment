package com.brenosalles.reqres.cache;

import java.util.ArrayList;

import com.brenosalles.reqres.cache.exceptions.ResourceNotFound;
import com.brenosalles.resources.Resource;

public interface IResourcesCache {
    Boolean addResource(Resource resource);

    Boolean addResources(ArrayList<Resource> resources);

    Resource getResource(Integer id) throws ResourceNotFound;

    ArrayList<Resource> getResources();

    Boolean updateResource(Integer id, Resource resource);

    Boolean deleteResource(Integer id);
}