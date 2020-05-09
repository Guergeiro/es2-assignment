package com.brenosalles.users;

public abstract class UserFactory {
    public static User createUser(Integer id, String email, String firstName, String lastName, String avatar) {
        if (id != null) {
            return new User(id, email, firstName, lastName, avatar);
        }
        return new User(email, firstName, lastName, avatar);
    }
}