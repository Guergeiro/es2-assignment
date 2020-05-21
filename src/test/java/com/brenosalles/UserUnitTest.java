package com.brenosalles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.brenosalles.users.InvalidUserException;
import com.brenosalles.users.User;
import com.brenosalles.users.UserFactory;

import org.junit.jupiter.api.Test;

public class UserUnitTest {
    @Test
    public void createUserWithNullEmail() {
        assertThrows(InvalidUserException.class, () -> {
            UserFactory.createUser(null, null, "firstName", "lastName", "avatar");
        });
    }

    @Test
    public void createUserWithInvalidEmail() {
        assertThrows(InvalidUserException.class, () -> {
            UserFactory.createUser(null, "email", "firstName", "lastName", "avatar");
        });
    }

    @Test
    public void createUserWithNullFirstName() {
        assertThrows(InvalidUserException.class, () -> {
            UserFactory.createUser(null, "email@email.com", null, "lastName", "avatar");
        });
    }

    @Test
    public void createUserWithLowerFirstName() {
        assertThrows(InvalidUserException.class, () -> {
            String firstName = "";
            for (int i = 0; i < 2; i++) {
                firstName += "a";
            }
            UserFactory.createUser(null, "email@email.com", firstName, "lastName", "avatar");
        });
    }

    @Test
    public void createUserWithHigherFirstName() {
        assertThrows(InvalidUserException.class, () -> {
            String firstName = "";
            for (int i = 0; i < 17; i++) {
                firstName += "a";
            }
            UserFactory.createUser(null, "email@email.com", firstName, "lastName", "avatar");
        });
    }

    @Test
    public void createUserWithNullLastName() {
        assertThrows(InvalidUserException.class, () -> {
            UserFactory.createUser(null, "email@email.com", "firstName", null, "avatar");
        });
    }

    @Test
    public void createUserWithLowerLastName() {
        assertThrows(InvalidUserException.class, () -> {
            String lastName = "";
            for (int i = 0; i < 2; i++) {
                lastName += "a";
            }
            UserFactory.createUser(null, "email@email.com", "firstName", lastName, "avatar");
        });
    }

    @Test
    public void createUserWithHigherLastName() {
        assertThrows(InvalidUserException.class, () -> {
            String lastName = "";
            for (int i = 0; i < 17; i++) {
                lastName += "a";
            }
            UserFactory.createUser(null, "email@email.com", "firstName", lastName, "avatar");
        });
    }

    @Test
    public void createUserWithNullAvatar() {
        assertThrows(InvalidUserException.class, () -> {
            UserFactory.createUser(null, "email@email.com", "firstName", "lastName", null);
        });
    }

    @Test
    public void createUserWithLowerAvatar() {
        assertThrows(InvalidUserException.class, () -> {
            String avatar = "";
            for (int i = 0; i < 3; i++) {
                avatar += "a";
            }
            UserFactory.createUser(null, "email@email.com", "firstName", "lastName", avatar);
        });
    }

    @Test
    public void createUserWithHigherAvatar() {
        assertThrows(InvalidUserException.class, () -> {
            String avatar = "";
            for (int i = 0; i < 65; i++) {
                avatar += "a";
            }
            UserFactory.createUser(null, "email@email.com", "firstName", "lastName", avatar);
        });
    }

    @Test
    public void createUserOk() throws InvalidUserException {
        assertEquals(User.class,
                UserFactory.createUser(null, "email@email.com", "firstName", "lastName", "avatar").getClass());

    }
}