package com.brenosalles.users;

public abstract class UserFactory {
    public static User createUser(Integer id, String email, String firstName, String lastName, String avatar)
            throws InvalidUserException {

        if (validateEmail(email) == false) {
            throw new InvalidUserException();
        }

        if (validateNames(firstName) == false) {
            throw new InvalidUserException();
        }
        if (validateNames(lastName) == false) {
            throw new InvalidUserException();
        }
        if (validateAvatar(avatar) == false) {
            throw new InvalidUserException();
        }

        if (id != null) {
            return new User(id, email, firstName, lastName, avatar);
        }
        return new User(email, firstName, lastName, avatar);
    }

    private static Boolean validateEmail(String email) {
        if (email == null) {
            return false;
        }
        System.out.println(email.matches("^.*@.*\\..*$"));
        return email.matches("^.*@.*\\..*$");
    }

    private static Boolean validateNames(String name) {
        if (name == null) {
            return false;
        }

        if (name.length() < 3) {
            return false;
        }

        if (name.length() > 16) {
            return false;
        }
        return true;
    }

    private static Boolean validateAvatar(String avatar) {
        if (avatar == null) {
            return false;
        }
        if (avatar.length() < 4) {
            return false;
        }
        if (avatar.length() > 64) {
            return false;
        }
        return true;
    }
}