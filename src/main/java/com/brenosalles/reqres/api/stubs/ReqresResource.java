package com.brenosalles.reqres.api.stubs;

import java.util.ArrayList;

import com.brenosalles.reqres.api.IReqresResource;
import com.brenosalles.resources.Resource;
import com.brenosalles.resources.ResourceFactory;

public class ReqresResource implements IReqresResource {
    @Override
    public ArrayList<Resource> readResources() {
        ArrayList<Resource> arr = new ArrayList<Resource>();
        for (Integer i = 1; i < 5; i++) {
            arr.add(ResourceFactory.createResource(i, i + "Name", 2000 + i, "#12345" + i, "17-203" + i));
        }
        return arr;
    }

    @Override
    public Resource readResource(Integer id) {
        if (id == 0) {
            return null;
        }
        return ResourceFactory.createResource(id, 1 + "Name", 2001, "#123456", "17-2031");
    }

    @Override
    public Resource createResource(Resource resource) {
        if (resource.getId() != null) {
            return null;
        }
        resource.setId(1);
        return resource;
    }

    @Override
    public Boolean updateResource(Integer id, Resource resource) {
        if (id == 0) {
            return false;
        }
        if (resource.getId() != null) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean deleteResource(Integer id) {
        if (id == 0) {
            return false;
        }
        return true;
    }
}