package com.brenosalles.decorators;

import java.util.ArrayList;

import com.brenosalles.resources.Resource;
import com.brenosalles.tokens.Token;
import com.brenosalles.users.User;

public abstract class Decorator implements IComponent {
    // Attributes
    private IComponent component;

    // Constructor
    public Decorator(IComponent component) {
        this.component = component;
    }

    @Override
    public User createUser(User user) {
        return component.createUser(user);
    }

    @Override
    public ArrayList<User> readUsers() {
        return component.readUsers();
    }

    @Override
    public User readUser(Integer id) {
        return component.readUser(id);
    }

    @Override
    public Boolean updateUser(Integer id, User user) {
        return component.updateUser(id, user);
    }

    @Override
    public Boolean deleteUser(Integer id) {
        return component.deleteUser(id);
    }

    @Override
    public Resource createResource(Resource resource) {
        return component.createResource(resource);
    }

    @Override
    public ArrayList<Resource> readResources() {
        return component.readResources();
    }

    @Override
    public Resource readResource(Integer id) {
        return component.readResource(id);
    }

    @Override
    public Boolean updateResource(Integer id, Resource resource) {
        return component.updateResource(id, resource);
    }

    @Override
    public Boolean deleteResource(Integer id) {
        return component.deleteResource(id);
    }

    @Override
    public Token register(User user, String password) {
        return component.register(user, password);
    }

    @Override
    public Token login(User user, String password) {
        return component.login(user, password);
    }
}