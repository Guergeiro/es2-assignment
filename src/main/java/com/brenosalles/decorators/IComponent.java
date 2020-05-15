package com.brenosalles.decorators;

import java.util.ArrayList;

import com.brenosalles.resources.Resource;
import com.brenosalles.users.User;

public interface IComponent {

    User createUser(User user);

    ArrayList<User> readUsers();

    User readUser(Integer id);

    void updateUser(Integer id, User user);

    void deleteUser(Integer id);

    Resource createResource(Resource resource);

    ArrayList<Resource> readResources();

    Resource readResource(Integer id);

    void updateResource(Integer id, Resource resource);

    void deleteResource(Integer id);
}