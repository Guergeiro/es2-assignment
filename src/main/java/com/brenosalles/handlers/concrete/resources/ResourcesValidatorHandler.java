package com.brenosalles.handlers.concrete.resources;

import com.brenosalles.handlers.AbstractHandler;
import com.brenosalles.resources.Resource;

public class ResourcesValidatorHandler extends AbstractHandler {
    @Override
    public Resource createResource(Resource resource) {
        if (resource == null) {
            return null;
        }
        return super.createResource(resource);
    }

    @Override
    public Resource readResource(Integer id) {
        if (id == null) {
            return null;
        }
        if (id == 0) {
            return null;
        }
        return super.readResource(id);
    }

    @Override
    public Boolean updateResource(Integer id, Resource resource) {
        if (id == null) {
            return false;
        }
        if (id == 0) {
            return false;
        }
        if (resource == null) {
            return false;
        }
        return super.updateResource(id, resource);
    }

    @Override
    public Boolean deleteResource(Integer id) {
        if (id == null) {
            return false;
        }
        if (id == 0) {
            return false;
        }
        return super.deleteResource(id);
    }
}