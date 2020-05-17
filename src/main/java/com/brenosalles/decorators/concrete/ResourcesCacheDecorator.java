package com.brenosalles.decorators.concrete;

import java.util.ArrayList;

import com.brenosalles.decorators.Decorator;
import com.brenosalles.decorators.IComponent;
import com.brenosalles.reqres.cache.IResourcesCache;
import com.brenosalles.reqres.cache.exceptions.ResourceNotFound;
import com.brenosalles.resources.Resource;

public class ResourcesCacheDecorator extends Decorator {
    // Attributes
    private IResourcesCache cache;

    // Constructor
    public ResourcesCacheDecorator(IComponent component, IResourcesCache cache) {
        super(component);
        this.cache = cache;
    }

    @Override
    public Resource createResource(Resource resource) {
        Resource r = super.createResource(resource);
        cache.addResource(r);
        return r;
    }

    @Override
    public ArrayList<Resource> readResources() {
        ArrayList<Resource> cacheResources = cache.getResources();
        if (cacheResources.size() != 0) {
            return cacheResources;
        }

        ArrayList<Resource> newResources = super.readResources();
        cache.addResources(newResources);
        return newResources;
    }

    @Override
    public Resource readResource(Integer id) {
        try {
            return cache.getResource(id);
        } catch (ResourceNotFound e) {
            Resource resource = super.readResource(id);
            cache.addResource(resource);
            return resource;
        }
    }

    @Override
    public void updateResource(Integer id, Resource resource) {
        cache.updateResource(id, resource);
        super.updateResource(id, resource);
    }

    @Override
    public void deleteResource(Integer id) {
        cache.deleteResource(id);
        super.deleteResource(id);
    }
}