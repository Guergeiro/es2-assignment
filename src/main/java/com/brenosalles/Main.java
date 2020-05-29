package com.brenosalles;

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
import com.brenosalles.users.InvalidUserException;

public class Main {
    public static void main(String[] args) throws InvalidUserException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException, InvalidResourceException {
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

        System.out.println(resources.contains(originalResource));
        for (Resource resource : resources) {
            System.out.println(resource.getId());
        }

    }
}