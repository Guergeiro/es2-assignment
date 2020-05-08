package com.brenosalles.users;

public abstract class UserFactory {
    public static User createUser(Integer id, String email, String firstName, String lastName) {
        if (id != null) {
            return new User(id, email, firstName, lastName);
        }
        return new User(email, firstName, lastName);
    }
}