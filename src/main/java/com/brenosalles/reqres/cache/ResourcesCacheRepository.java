package com.brenosalles.reqres.cache;

import java.util.ArrayList;

import com.brenosalles.reqres.cache.exceptions.ResourceNotFound;
import com.brenosalles.resources.Resource;

public class ResourcesCacheRepository {
    // Attributes
    private ArrayList<Resource> resources = new ArrayList<Resource>();

    public void addResource(Resource resource) {
        resources.add(resource);
    }

    public void addResources(ArrayList<Resource> resources) {
        this.resources.addAll(resources);
    }

    public Resource getResource(Integer id) throws ResourceNotFound {
        for (Resource resource : resources) {
            if (resource.getId() == id) {
                return resource;
            }
        }
        throw new ResourceNotFound();
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }

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

    public void deleteResource(Integer id) {
        resources.removeIf(resource -> resource.getId() == id);
    }
}