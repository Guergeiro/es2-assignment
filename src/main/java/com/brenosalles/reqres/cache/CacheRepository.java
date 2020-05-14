package com.brenosalles.reqres.cache;

import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.brenosalles.reqres.cache.Exceptions.ResourceNotFound;
import com.brenosalles.reqres.cache.Exceptions.UserNotFound;
import com.brenosalles.resources.Resource;
import com.brenosalles.users.User;

public class CacheRepository {
    // Attributes
    private static CacheRepository instance;
    private ConcurrentHashMap<User, Date> users = new ConcurrentHashMap<User, Date>();
    private ConcurrentHashMap<Resource, Date> resources = new ConcurrentHashMap<Resource, Date>();
    private final Integer CACHE_TIMEOUT = 60000;

    // Constructor
    private CacheRepository() {
        new Thread() {
            public void run() {
                Iterator<Entry<User, Date>> userIterator = users.entrySet().iterator();

                while (userIterator.hasNext()) {
                    Entry<User, Date> pair = userIterator.next();
                    // Remove if older than 1min
                    if (new Date().getTime() - pair.getValue().getTime() > CACHE_TIMEOUT) {
                        userIterator.remove();
                    }
                }

                Iterator<Entry<Resource, Date>> resourceIterator = resources.entrySet().iterator();

                while (resourceIterator.hasNext()) {
                    Entry<Resource, Date> pair = resourceIterator.next();
                    // Remove if older than 1min
                    if (new Date().getTime() - pair.getValue().getTime() > CACHE_TIMEOUT) {
                        resourceIterator.remove();
                    }
                }

                try {
                    Thread.sleep(CACHE_TIMEOUT / 2);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }.start();
    }

    public CacheRepository getInstance() {
        if (instance == null) {
            synchronized (CacheRepository.class) {
                if (instance == null) {
                    instance = new CacheRepository();
                }
            }
        }
        return instance;
    }

    public void addUser(User user) {
        users.put(user, new Date());
    }

    public User getUser(Integer id) throws UserNotFound {
        Iterator<Entry<User, Date>> it = users.entrySet().iterator();

        while (it.hasNext()) {
            Entry<User, Date> pair = it.next();
            if (pair.getKey().getId() == id) {
                return pair.getKey();
            }
        }

        throw new UserNotFound();
    }

    public void addResource(Resource resource) {
        resources.put(resource, new Date());
    }

    public Resource getResource(Integer id) throws ResourceNotFound {
        Iterator<Entry<Resource, Date>> it = resources.entrySet().iterator();

        while (it.hasNext()) {
            Entry<Resource, Date> pair = it.next();
            if (pair.getKey().getId() == id) {
                return pair.getKey();
            }
        }

        throw new ResourceNotFound();
    }
}