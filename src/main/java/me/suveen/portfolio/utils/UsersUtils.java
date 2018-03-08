package me.suveen.portfolio.utils;

import me.suveen.portfolio.backend.persistence.domain.backend.User;

public class UsersUtils {

    /**
     * Non instantiable
     */
    public UsersUtils() {
        throw new AssertionError("Non instantiable");
    }

    public static User createBasicUser() {

        User user = new User();
        user.setEmail("suveenkumar.vundavalli@gmail.com");
        user.setEnabled(true);
        user.setFirstName("Suveen Kumar");
        user.setLastName("Vundavalli");
        user.setPassword("secret");
        user.setUsername("suveenkumarchowdary");
        user.setPhoneNumber("8686242020");

        return user;
    }
}
