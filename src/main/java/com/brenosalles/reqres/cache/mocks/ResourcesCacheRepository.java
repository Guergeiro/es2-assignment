package com.brenosalles.reqres.cache.mocks;

import java.util.ArrayList;

import com.brenosalles.reqres.cache.IResourcesCache;
import com.brenosalles.reqres.cache.exceptions.ResourceNotFound;
import com.brenosalles.resources.Resource;

public class ResourcesCacheRepository implements IResourcesCache {
    // Attributes
    private ArrayList<Resource> resources = new ArrayList<Resource>();

    @Override
    public void addResource(Resource resource) {
        resources.add(resource);
    }

    @Override
    public void addResources(ArrayList<Resource> resources) {
        this.resources.addAll(resources);
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
    public void updateResource(Integer id, Resource resource) {
        for (Resource r : resources) {
            if (r.getId() == id) {
                r.setColor(resource.getColor());
                r.setName(resource.getName());
                r.setPantoneValue(resource.getPantoneValue());
                r.setYear(resource.getYear());
                return;
            }
        }
    }

    @Override
    public void deleteResource(Integer id) {
        resources.removeIf(resource -> resource.getId() == id);
    }
}