package com.brenosalles.reqres.cache;

import java.util.ArrayList;

import com.brenosalles.reqres.cache.exceptions.ResourceNotFound;
import com.brenosalles.resources.Resource;

public interface IResourcesCache {
    void addResource(Resource resource);

    void addResources(ArrayList<Resource> resources);

    Resource getResource(Integer id) throws ResourceNotFound;

    ArrayList<Resource> getResources();

    void updateResource(Integer id, Resource resource);

    void deleteResource(Integer id);
}