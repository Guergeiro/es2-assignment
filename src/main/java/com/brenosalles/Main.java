package com.brenosalles;

import java.util.ArrayList;

import com.brenosalles.users.User;
import com.brenosalles.users.UserFactory;

public class Main {
    public static void main(String args[]) {
        User u = UserFactory.createUser(1, "breno@breno", "Breno", "Breno", "Breno");
        User newUser = UserFactory.createUser(2, "saless", "firstName", "lastName", "avatar");

        ArrayList<User> users = new ArrayList<User>();
        users.add(u);
        for (User user : users) {
            if (user.getId() == 1) {
                user = newUser;
            }
        }

        for (User user : users) {
            System.out.println(user.getEmail());
        }
    }
}