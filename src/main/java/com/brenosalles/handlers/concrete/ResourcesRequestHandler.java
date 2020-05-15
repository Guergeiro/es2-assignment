package com.brenosalles.handlers.concrete;

import java.util.ArrayList;

import com.brenosalles.handlers.AbstractHandler;
import com.brenosalles.reqres.api.IReqresResource;
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
        Resource res = apiResource.createResource(resource);
        return res != null ? res : super.createResource(resource);
    }

    @Override
    public ArrayList<Resource> readResources() {
        ArrayList<Resource> resources = apiResource.readResources();
        if (resources.size() != 0) {
            return resources;
        }
        return super.readResources();
    }

    @Override
    public Resource readResource(Integer id) {
        Resource resource = apiResource.readResource(id);

        return resource != null ? resource : super.readResource(id);
    }

    @Override
    public void updateResource(Integer id, Resource resource) {
        if (apiResource.updateResource(id, resource) == false) {
            super.updateResource(id, resource);
        }
    }

    @Override
    public void deleteResource(Integer id) {
        if (apiResource.deleteResource(id) == false) {
            super.deleteResource(id);
        }
    }
}