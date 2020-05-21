package com.brenosalles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import com.brenosalles.handlers.IHandler;
import com.brenosalles.handlers.concrete.resources.ResourcesRequestHandler;
import com.brenosalles.reqres.api.IReqresResource;
import com.brenosalles.reqres.api.implementation.ReqresResource;
import com.brenosalles.resources.InvalidResourceException;
import com.brenosalles.resources.Resource;
import com.brenosalles.resources.ResourceFactory;

import org.junit.jupiter.api.Test;

public class ResourceServiceTest {
    @Test
    public void createResourceOk() throws InvalidResourceException {
        IReqresResource apiResource = new ReqresResource();
        IHandler handler = new ResourcesRequestHandler(apiResource);
        Resource resource = ResourceFactory.createResource(null, "Resource", 2000, "#123123", "pantoneValue");
        assertEquals(Resource.class, handler.createResource(resource).getClass());
    }

    @Test
    public void updateResourceOk() throws InvalidResourceException {
        IReqresResource apiResource = new ReqresResource();
        IHandler handler = new ResourcesRequestHandler(apiResource);
        Resource resource = ResourceFactory.createResource(null, "Resource", 2000, "#123123", "pantoneValue");
        assertTrue(handler.updateResource(1, resource));
    }

    @Test
    public void deleteResourceOk() {
        IReqresResource apiResource = new ReqresResource();
        IHandler handler = new ResourcesRequestHandler(apiResource);
        assertTrue(handler.deleteResource(1));
    }

    @Test
    public void getResourceInvalidId() {
        IReqresResource apiResource = new ReqresResource();
        IHandler handler = new ResourcesRequestHandler(apiResource);
        assertNull(handler.readResource(0));
    }

    @Test
    public void getResourceOk() {
        IReqresResource apiResource = new ReqresResource();
        IHandler handler = new ResourcesRequestHandler(apiResource);
        assertEquals(Resource.class, handler.readResource(1).getClass());
    }

    @Test
    public void getResourcesOk() {
        IReqresResource apiResource = new ReqresResource();
        IHandler handler = new ResourcesRequestHandler(apiResource);
        assertEquals((new ArrayList<Resource>()).getClass(), handler.readResources().getClass());
    }
}