package com.brenosalles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import com.brenosalles.handlers.IHandler;
import com.brenosalles.handlers.concrete.resources.ResourcesRequestHandler;
import com.brenosalles.handlers.concrete.resources.ResourcesValidatorHandler;
import com.brenosalles.reqres.api.IReqresResource;
import com.brenosalles.reqres.api.stubs.ReqresResource;
import com.brenosalles.resources.InvalidResourceException;
import com.brenosalles.resources.Resource;
import com.brenosalles.resources.ResourceFactory;

import org.junit.jupiter.api.Test;

public class ResourceUnitTest {
    @Test
    public void createResourceWithNullName() {
        assertThrows(InvalidResourceException.class, () -> {
            ResourceFactory.createResource(null, null, 2000, "#123123", "Some Color");
        });
    }

    @Test
    public void createResourceWithLowerName() {
        assertThrows(InvalidResourceException.class, () -> {
            String name = "";
            for (int i = 0; i < 3; i++) {
                name += "a";
            }
            ResourceFactory.createResource(null, name, 2000, "#123123", "Some Color");
        });
    }

    @Test
    public void createResourceWithHigherName() {
        assertThrows(InvalidResourceException.class, () -> {
            String name = "";
            for (int i = 0; i < 17; i++) {
                name += "a";
            }
            ResourceFactory.createResource(null, name, 2000, "#123123", "Some Color");
        });
    }

    @Test
    public void createResourceWithNullYear() {
        assertThrows(InvalidResourceException.class, () -> {
            ResourceFactory.createResource(null, "Resource", null, "#123123", "Some Color");
        });
    }

    @Test
    public void createResourceWithNullColor() {
        assertThrows(InvalidResourceException.class, () -> {
            ResourceFactory.createResource(null, "Resource", 2000, null, "Some Color");
        });
    }

    @Test
    public void createResourceWithInvalidColor() {
        assertThrows(InvalidResourceException.class, () -> {
            ResourceFactory.createResource(null, "Resource", 2000, "red", "Some Color");
        });
    }

    @Test
    public void createResourceWithNullPantoneColor() {
        assertThrows(InvalidResourceException.class, () -> {
            ResourceFactory.createResource(null, "Resource", 2000, "#123123", null);
        });
    }

    @Test
    public void createResourceOk() throws InvalidResourceException {
        assertEquals(Resource.class,
                ResourceFactory.createResource(null, "Resource", 2000, "#123123", "Some Color").getClass());

    }

    @Test
    public void readResourcesOk() {
        IReqresResource apiAuthentication = new ReqresResource();
        IHandler handler1 = new ResourcesValidatorHandler();
        IHandler handler2 = new ResourcesRequestHandler(apiAuthentication);

        handler1.setNext(handler2);

        assertEquals((new ArrayList<Resource>()).getClass(), handler1.readResources().getClass());
    }

    @Test
    public void readResourceWithNullId() {
        IReqresResource apiAuthentication = new ReqresResource();
        IHandler handler1 = new ResourcesValidatorHandler();
        IHandler handler2 = new ResourcesRequestHandler(apiAuthentication);

        handler1.setNext(handler2);

        assertNull(handler1.readResource(null));
    }

    @Test
    public void readResourceWithInvalidId() {
        IReqresResource apiAuthentication = new ReqresResource();
        IHandler handler1 = new ResourcesValidatorHandler();
        IHandler handler2 = new ResourcesRequestHandler(apiAuthentication);

        handler1.setNext(handler2);

        assertNull(handler1.readResource(0));
    }

    @Test
    public void readResourceOk() {
        IReqresResource apiAuthentication = new ReqresResource();
        IHandler handler1 = new ResourcesValidatorHandler();
        IHandler handler2 = new ResourcesRequestHandler(apiAuthentication);

        handler1.setNext(handler2);

        assertEquals(Resource.class, handler1.readResource(1).getClass());
    }

    @Test
    public void updateWithNullId() throws InvalidResourceException {
        IReqresResource apiAuthentication = new ReqresResource();
        IHandler handler1 = new ResourcesValidatorHandler();
        IHandler handler2 = new ResourcesRequestHandler(apiAuthentication);

        handler1.setNext(handler2);

        Resource resource = ResourceFactory.createResource(null, "Resource", 2000, "#123123", "Some Color");

        assertFalse(handler1.updateResource(null, resource));
    }

    @Test
    public void updateWithInvalidId() throws InvalidResourceException {
        IReqresResource apiAuthentication = new ReqresResource();
        IHandler handler1 = new ResourcesValidatorHandler();
        IHandler handler2 = new ResourcesRequestHandler(apiAuthentication);

        handler1.setNext(handler2);

        Resource resource = ResourceFactory.createResource(null, "Resource", 2000, "#123123", "Some Color");
        assertFalse(handler1.updateResource(0, resource));
    }

    @Test
    public void updateWithNullResource() throws InvalidResourceException {
        IReqresResource apiAuthentication = new ReqresResource();
        IHandler handler1 = new ResourcesValidatorHandler();
        IHandler handler2 = new ResourcesRequestHandler(apiAuthentication);

        handler1.setNext(handler2);

        assertFalse(handler1.updateResource(1, null));
    }

    @Test
    public void updateResourceOk() throws InvalidResourceException {
        IReqresResource apiAuthentication = new ReqresResource();
        IHandler handler1 = new ResourcesValidatorHandler();
        IHandler handler2 = new ResourcesRequestHandler(apiAuthentication);

        handler1.setNext(handler2);

        Resource resource = ResourceFactory.createResource(null, "Resource", 2000, "#123123", "Some Color");
        assertTrue(handler1.updateResource(1, resource));
    }

    @Test
    public void deleteWithNullId() throws InvalidResourceException {
        IReqresResource apiAuthentication = new ReqresResource();
        IHandler handler1 = new ResourcesValidatorHandler();
        IHandler handler2 = new ResourcesRequestHandler(apiAuthentication);

        handler1.setNext(handler2);

        assertFalse(handler1.deleteResource(null));
    }

    @Test
    public void deleteWithInvalidId() throws InvalidResourceException {
        IReqresResource apiAuthentication = new ReqresResource();
        IHandler handler1 = new ResourcesValidatorHandler();
        IHandler handler2 = new ResourcesRequestHandler(apiAuthentication);

        handler1.setNext(handler2);

        assertFalse(handler1.deleteResource(0));
    }

    @Test
    public void deleteResourceOk() throws InvalidResourceException {
        IReqresResource apiAuthentication = new ReqresResource();
        IHandler handler1 = new ResourcesValidatorHandler();
        IHandler handler2 = new ResourcesRequestHandler(apiAuthentication);

        handler1.setNext(handler2);

        assertTrue(handler1.deleteResource(1));
    }
}