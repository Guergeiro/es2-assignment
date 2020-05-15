package com.brenosalles.decorators;

import java.util.ArrayList;

import com.brenosalles.resources.Resource;
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
    public void updateUser(Integer id, User user) {
        component.updateUser(id, user);
    }

    @Override
    public void deleteUser(Integer id) {
        component.deleteUser(id);
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
    public void updateResource(Integer id, Resource resource) {
        component.updateResource(id, resource);
    }

    @Override
    public void deleteResource(Integer id) {
        component.deleteResource(id);
    }
}