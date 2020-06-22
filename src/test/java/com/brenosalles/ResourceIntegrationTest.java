package com.brenosalles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.brenosalles.decorators.IComponent;
import com.brenosalles.decorators.concrete.ResourcesCacheDecorator;
import com.brenosalles.handlers.IHandler;
import com.brenosalles.handlers.concrete.resources.ResourcesRequestHandler;
import com.brenosalles.handlers.concrete.resources.ResourcesValidatorHandler;
import com.brenosalles.reqres.api.IReqresResource;
import com.brenosalles.reqres.api.implementation.ReqresResource;
import com.brenosalles.reqres.cache.implementation.ResourcesCacheRepository;
import com.brenosalles.resources.InvalidResourceException;
import com.brenosalles.resources.Resource;
import com.brenosalles.resources.ResourceFactory;

import org.junit.jupiter.api.Test;

public class ResourceIntegrationTest {
    @Test
    public void readResourcesOk() {
        IReqresResource apiResource = new ReqresResource();
        IHandler handler1 = new ResourcesValidatorHandler();
        IHandler handler2 = new ResourcesRequestHandler(apiResource);

        handler1.setNext(handler2);

        assertEquals((new ArrayList<Resource>()).getClass(), handler1.readResources().getClass());
    }

    @Test
    public void readResourceWithNullId() {
        IReqresResource apiResource = new ReqresResource();
        IHandler handler1 = new ResourcesValidatorHandler();
        IHandler handler2 = new ResourcesRequestHandler(apiResource);

        handler1.setNext(handler2);

        assertNull(handler1.readResource(null));
    }

    @Test
    public void readResourceWithInvalidId() {
        IReqresResource apiResource = new ReqresResource();
        IHandler handler1 = new ResourcesValidatorHandler();
        IHandler handler2 = new ResourcesRequestHandler(apiResource);

        handler1.setNext(handler2);

        assertNull(handler1.readResource(0));
    }

    @Test
    public void readResourceOk() {
        IReqresResource apiResource = new ReqresResource();
        IHandler handler1 = new ResourcesValidatorHandler();
        IHandler handler2 = new ResourcesRequestHandler(apiResource);

        handler1.setNext(handler2);

        assertEquals(Resource.class, handler1.readResource(1).getClass());
    }

    @Test
    public void updateWithNullId() throws InvalidResourceException {
        IReqresResource apiResource = new ReqresResource();
        IHandler handler1 = new ResourcesValidatorHandler();
        IHandler handler2 = new ResourcesRequestHandler(apiResource);

        handler1.setNext(handler2);

        Resource resource = ResourceFactory.createResource(null, "Resource", 2000, "#123123", "Some Color");

        assertFalse(handler1.updateResource(null, resource));
    }

    @Test
    public void updateWithInvalidId() throws InvalidResourceException {
        IReqresResource apiResource = new ReqresResource();
        IHandler handler1 = new ResourcesValidatorHandler();
        IHandler handler2 = new ResourcesRequestHandler(apiResource);

        handler1.setNext(handler2);

        Resource resource = ResourceFactory.createResource(null, "Resource", 2000, "#123123", "Some Color");
        assertFalse(handler1.updateResource(0, resource));
    }

    @Test
    public void updateWithNullResource() throws InvalidResourceException {
        IReqresResource apiResource = new ReqresResource();
        IHandler handler1 = new ResourcesValidatorHandler();
        IHandler handler2 = new ResourcesRequestHandler(apiResource);

        handler1.setNext(handler2);

        assertFalse(handler1.updateResource(1, null));
    }

    @Test
    public void updateResourceOk() throws InvalidResourceException {
        IReqresResource apiResource = new ReqresResource();
        IHandler handler1 = new ResourcesValidatorHandler();
        IHandler handler2 = new ResourcesRequestHandler(apiResource);

        handler1.setNext(handler2);

        Resource resource = ResourceFactory.createResource(null, "Resource", 2000, "#123123", "Some Color");
        assertTrue(handler1.updateResource(1, resource));
    }

    @Test
    public void deleteWithNullId() throws InvalidResourceException {
        IReqresResource apiResource = new ReqresResource();
        IHandler handler1 = new ResourcesValidatorHandler();
        IHandler handler2 = new ResourcesRequestHandler(apiResource);

        handler1.setNext(handler2);

        assertFalse(handler1.deleteResource(null));
    }

    @Test
    public void deleteWithInvalidId() throws InvalidResourceException {
        IReqresResource apiResource = new ReqresResource();
        IHandler handler1 = new ResourcesValidatorHandler();
        IHandler handler2 = new ResourcesRequestHandler(apiResource);

        handler1.setNext(handler2);

        assertFalse(handler1.deleteResource(0));
    }

    @Test
    public void deleteResourceOk() throws InvalidResourceException {
        IReqresResource apiResource = new ReqresResource();
        IHandler handler1 = new ResourcesValidatorHandler();
        IHandler handler2 = new ResourcesRequestHandler(apiResource);

        handler1.setNext(handler2);

        assertTrue(handler1.deleteResource(1));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void readResourceInCacheInvalidId() throws InvalidResourceException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        IReqresResource apiResource = new ReqresResource();
        IHandler handler1 = new ResourcesValidatorHandler();
        IHandler handler2 = new ResourcesRequestHandler(apiResource);

        handler1.setNext(handler2);

        IComponent finalImplementation = new ResourcesCacheDecorator(handler1, new ResourcesCacheRepository());

        // Access private cache
        Field f1 = finalImplementation.getClass().getDeclaredField("cache");
        f1.setAccessible(true);
        ResourcesCacheRepository cache = (ResourcesCacheRepository) f1.get(finalImplementation);

        // Access private cache ArrayList
        Field f2 = cache.getClass().getDeclaredField("resources");
        f2.setAccessible(true);
        ArrayList<Resource> resources = (ArrayList<Resource>) f2.get(cache);

        Resource originalResource = ResourceFactory.createResource(1, "name", 2000, "#123123", "pantoneValue");
        resources.add(originalResource);

        assertNull(finalImplementation.readResource(0));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void readResourceInCacheOk() throws InvalidResourceException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        IReqresResource apiResource = new ReqresResource();
        IHandler handler1 = new ResourcesValidatorHandler();
        IHandler handler2 = new ResourcesRequestHandler(apiResource);

        handler1.setNext(handler2);

        IComponent finalImplementation = new ResourcesCacheDecorator(handler1, new ResourcesCacheRepository());

        // Access private cache
        Field f1 = finalImplementation.getClass().getDeclaredField("cache");
        f1.setAccessible(true);
        ResourcesCacheRepository cache = (ResourcesCacheRepository) f1.get(finalImplementation);

        // Access private cache ArrayList
        Field f2 = cache.getClass().getDeclaredField("resources");
        f2.setAccessible(true);
        ArrayList<Resource> resources = (ArrayList<Resource>) f2.get(cache);

        Resource originalResource = ResourceFactory.createResource(1, "name", 2000, "#123123", "pantoneValue");
        resources.add(originalResource);

        assertEquals(originalResource, finalImplementation.readResource(1));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void createResourceInCacheOk() throws InvalidResourceException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        IReqresResource apiResource = new ReqresResource();
        IHandler handler1 = new ResourcesValidatorHandler();
        IHandler handler2 = new ResourcesRequestHandler(apiResource);

        handler1.setNext(handler2);

        IComponent finalImplementation = new ResourcesCacheDecorator(handler1, new ResourcesCacheRepository());

        // Access private cache
        Field f1 = finalImplementation.getClass().getDeclaredField("cache");
        f1.setAccessible(true);
        ResourcesCacheRepository cache = (ResourcesCacheRepository) f1.get(finalImplementation);

        // Access private cache ArrayList
        Field f2 = cache.getClass().getDeclaredField("resources");
        f2.setAccessible(true);
        ArrayList<Resource> resources = (ArrayList<Resource>) f2.get(cache);
        Integer originalSize = resources.size();

        Resource originalResource = ResourceFactory.createResource(null, "name", 2000, "#123123", "pantoneValue");
        originalResource.setId(finalImplementation.createResource(originalResource).getId());

        assertTrue(resources.contains(originalResource));
        assertEquals(originalSize + 1, finalImplementation.readResources().size());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void updateResourceInCacheInvalidId() throws InvalidResourceException, NoSuchFieldException,
            SecurityException, IllegalArgumentException, IllegalAccessException {
        IReqresResource apiResource = new ReqresResource();
        IHandler handler1 = new ResourcesValidatorHandler();
        IHandler handler2 = new ResourcesRequestHandler(apiResource);

        handler1.setNext(handler2);

        IComponent finalImplementation = new ResourcesCacheDecorator(handler1, new ResourcesCacheRepository());

        // Access private cache
        Field f1 = finalImplementation.getClass().getDeclaredField("cache");
        f1.setAccessible(true);
        ResourcesCacheRepository cache = (ResourcesCacheRepository) f1.get(finalImplementation);

        // Access private cache ArrayList
        Field f2 = cache.getClass().getDeclaredField("resources");
        f2.setAccessible(true);
        ArrayList<Resource> resources = (ArrayList<Resource>) f2.get(cache);

        // Create a resource for update
        Resource originalResource = ResourceFactory.createResource(1, "name", 2000, "#123123", "pantoneValue");
        resources.add(originalResource);

        Resource newResource = ResourceFactory.createResource(1, "name", 2000, "#321123", "pantoneValue");
        assertFalse(finalImplementation.updateResource(0, newResource));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void updateResourceInCacheOk() throws InvalidResourceException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        IReqresResource apiResource = new ReqresResource();
        IHandler handler1 = new ResourcesValidatorHandler();
        IHandler handler2 = new ResourcesRequestHandler(apiResource);

        handler1.setNext(handler2);

        IComponent finalImplementation = new ResourcesCacheDecorator(handler1, new ResourcesCacheRepository());

        // Access private cache
        Field f1 = finalImplementation.getClass().getDeclaredField("cache");
        f1.setAccessible(true);
        ResourcesCacheRepository cache = (ResourcesCacheRepository) f1.get(finalImplementation);

        // Access private cache ArrayList
        Field f2 = cache.getClass().getDeclaredField("resources");
        f2.setAccessible(true);
        ArrayList<Resource> resources = (ArrayList<Resource>) f2.get(cache);

        // Create a resource for update
        Resource originalResource = ResourceFactory.createResource(1, "name", 2000, "#123123", "pantoneValue");
        resources.add(originalResource);

        Resource newResource = ResourceFactory.createResource(null, "name", 2000, "#321123", "pantoneValue");
        assertTrue(finalImplementation.updateResource(originalResource.getId(), newResource));
        assertTrue(resources.contains(originalResource));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void deleteResourceInCacheInvalidId() throws InvalidResourceException, NoSuchFieldException,
            SecurityException, IllegalArgumentException, IllegalAccessException {
        IReqresResource apiResource = new ReqresResource();
        IHandler handler1 = new ResourcesValidatorHandler();
        IHandler handler2 = new ResourcesRequestHandler(apiResource);

        handler1.setNext(handler2);

        IComponent finalImplementation = new ResourcesCacheDecorator(handler1, new ResourcesCacheRepository());

        // Access private cache
        Field f1 = finalImplementation.getClass().getDeclaredField("cache");
        f1.setAccessible(true);
        ResourcesCacheRepository cache = (ResourcesCacheRepository) f1.get(finalImplementation);

        // Access private cache ArrayList
        Field f2 = cache.getClass().getDeclaredField("resources");
        f2.setAccessible(true);
        ArrayList<Resource> resources = (ArrayList<Resource>) f2.get(cache);

        // Create a resource for delete
        Resource originalResource = ResourceFactory.createResource(1, "name", 2000, "#123123", "pantoneValue");
        resources.add(originalResource);

        assertFalse(finalImplementation.deleteResource(0));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void deleteResourceInCacheOk() throws InvalidResourceException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        IReqresResource apiResource = new ReqresResource();
        IHandler handler1 = new ResourcesValidatorHandler();
        IHandler handler2 = new ResourcesRequestHandler(apiResource);

        handler1.setNext(handler2);

        IComponent finalImplementation = new ResourcesCacheDecorator(handler1, new ResourcesCacheRepository());

        // Access private cache
        Field f1 = finalImplementation.getClass().getDeclaredField("cache");
        f1.setAccessible(true);
        ResourcesCacheRepository cache = (ResourcesCacheRepository) f1.get(finalImplementation);

        // Access private cache ArrayList
        Field f2 = cache.getClass().getDeclaredField("resources");
        f2.setAccessible(true);
        ArrayList<Resource> resources = (ArrayList<Resource>) f2.get(cache);

        // Create a resource for update
        Resource originalResource = ResourceFactory.createResource(1, "name", 2000, "#123123", "pantoneValue");
        resources.add(originalResource);

        assertTrue(finalImplementation.deleteResource(originalResource.getId()));
        assertFalse(resources.contains(originalResource));
    }
}