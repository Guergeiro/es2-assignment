package com.brenosalles.decorators;

import java.util.ArrayList;

import com.brenosalles.resources.Resource;
import com.brenosalles.tokens.Token;
import com.brenosalles.users.User;

public interface IComponent {

    User createUser(User user);

    ArrayList<User> readUsers();

    User readUser(Integer id);

    Boolean updateUser(Integer id, User user);

    Boolean deleteUser(Integer id);

    Resource createResource(Resource resource);

    ArrayList<Resource> readResources();

    Resource readResource(Integer id);

    Boolean updateResource(Integer id, Resource resource);

    Boolean deleteResource(Integer id);

    Token register(User user, String password);

    Token login(User user, String password);
}