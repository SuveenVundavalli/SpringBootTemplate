package me.suveen.portfolio.utils;

import me.suveen.portfolio.backend.persistence.domain.backend.User;

public class UserUtils {

    /**
     * Non instantiable
     */
    public UserUtils() {
        throw new AssertionError("Non instantiable");
    }

    /**
     * Creates a user with basic attributes set
     * @param username The username
     * @param email The email id
     * @return A user entity
     */
    public static User createBasicUser(String username, String email) {

        User user = new User();
        user.setEmail(email);
        user.setEnabled(true);
        user.setFirstName("Suveen Kumar");
        user.setLastName("Vundavalli");
        user.setPassword("secret");
        user.setUsername(username);
        user.setPhoneNumber("1234567890");

        return user;
    }
}
