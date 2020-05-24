package com.brenosalles.reqres.cache.implementation;

import java.util.ArrayList;

import com.brenosalles.reqres.cache.IResourcesCache;
import com.brenosalles.reqres.cache.exceptions.ResourceNotFound;
import com.brenosalles.resources.Resource;

public class ResourcesCacheRepository implements IResourcesCache {
    // Attributes
    private ArrayList<Resource> resources = new ArrayList<Resource>();

    @Override
    public Boolean addResource(Resource resource) {
        return resources.add(resource);
    }

    @Override
    public Boolean addResources(ArrayList<Resource> resources) {
        return this.resources.addAll(resources);
    }

    @Override
    public Resource getResource(Integer id) throws ResourceNotFound {
        for (Resource resource : resources) {
            if (resource.getId() == id) {
                return resource;
            }
        }
        throw new ResourceNotFound();
    }

    @Override
    public ArrayList<Resource> getResources() {
        return resources;
    }

    @Override
    public Boolean updateResource(Integer id, Resource resource) {
        for (Resource r : resources) {
            if (r.getId() == id) {
                r.setColor(resource.getColor());
                r.setName(resource.getName());
                r.setPantoneValue(resource.getPantoneValue());
                r.setYear(resource.getYear());
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean deleteResource(Integer id) {
        return resources.removeIf(resource -> resource.getId() == id);
    }
}