package com.brenosalles.handlers;

import java.util.ArrayList;

import com.brenosalles.resources.Resource;
import com.brenosalles.tokens.Token;
import com.brenosalles.users.User;

public abstract class AbstractHandler implements IHandler {
    private IHandler nextHandler;

    @Override
    public IHandler setNext(IHandler handler) {
        this.nextHandler = handler;
        return handler;
    }

    @Override
    public User createUser(User user) {
        if (nextHandler != null) {
            return nextHandler.createUser(user);
        }
        return null;
    }

    @Override
    public ArrayList<User> readUsers() {
        if (nextHandler != null) {
            return nextHandler.readUsers();
        }

        return null;
    }

    @Override
    public User readUser(Integer id) {
        if (nextHandler != null) {
            return nextHandler.readUser(id);
        }

        return null;
    }

    @Override
    public Boolean updateUser(Integer id, User user) {
        if (nextHandler != null) {
            return nextHandler.updateUser(id, user);
        }
        return false;
    }

    @Override
    public Boolean deleteUser(Integer id) {
        if (nextHandler != null) {
            return nextHandler.deleteUser(id);
        }
        return false;
    }

    @Override
    public Resource createResource(Resource resource) {
        if (nextHandler != null) {
            return nextHandler.createResource(resource);
        }
        return null;
    }

    @Override
    public ArrayList<Resource> readResources() {
        if (nextHandler != null) {
            return nextHandler.readResources();
        }
        return null;
    }

    @Override
    public Resource readResource(Integer id) {
        if (nextHandler != null) {
            return nextHandler.readResource(id);
        }
        return null;
    }

    @Override
    public Boolean updateResource(Integer id, Resource resource) {
        if (nextHandler != null) {
            return nextHandler.updateResource(id, resource);
        }
        return false;
    }

    @Override
    public Boolean deleteResource(Integer id) {
        if (nextHandler != null) {
            return nextHandler.deleteResource(id);
        }
        return false;
    }

    @Override
    public Token register(User user, String password) {
        if (nextHandler != null) {
            return nextHandler.register(user, password);
        }
        return null;
    }

    @Override
    public Token login(User user, String password) {
        if (nextHandler != null) {
            return nextHandler.login(user, password);
        }
        return null;
    }
}