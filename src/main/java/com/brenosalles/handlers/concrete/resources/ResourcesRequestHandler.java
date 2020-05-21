package com.brenosalles.handlers.concrete.resources;

import java.util.ArrayList;

import com.brenosalles.handlers.AbstractHandler;
import com.brenosalles.reqres.api.IReqresResource;
import com.brenosalles.resources.InvalidResourceException;
import com.brenosalles.resources.Resource;

public class ResourcesRequestHandler extends AbstractHandler {
    // Attributes
    private IReqresResource apiResource;

    // Constructor
    public ResourcesRequestHandler(IReqresResource apiResource) {
        this.apiResource = apiResource;
    }

    @Override
    public Resource createResource(Resource resource) {
        try {
            return apiResource.createResource(resource);
        } catch (InvalidResourceException e) {
            return super.createResource(resource);
        }
    }

    @Override
    public ArrayList<Resource> readResources() {
        try {
            return apiResource.readResources();
        } catch (InvalidResourceException e) {
            return super.readResources();
        }
    }

    @Override
    public Resource readResource(Integer id) {
        try {
            return apiResource.readResource(id);
        } catch (InvalidResourceException e) {
            return super.readResource(id);
        }
    }

    @Override
    public Boolean updateResource(Integer id, Resource resource) {
        if (apiResource.updateResource(id, resource) == false) {
            return super.updateResource(id, resource);
        }
        return true;
    }

    @Override
    public Boolean deleteResource(Integer id) {
        if (apiResource.deleteResource(id) == false) {
            return super.deleteResource(id);
        }
        return true;
    }
}