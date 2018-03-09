package me.suveen.portfolio.test.integration;

import me.suveen.portfolio.backend.persistence.domain.backend.Role;
import me.suveen.portfolio.backend.persistence.domain.backend.User;
import me.suveen.portfolio.backend.persistence.domain.backend.UserRole;
import me.suveen.portfolio.backend.persistence.repositories.UserRepository;
import me.suveen.portfolio.backend.service.UserService;
import me.suveen.portfolio.enums.RolesEnum;
import me.suveen.portfolio.utils.UserUtils;
import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class AbstractServiceIntegrationTest {


    @Autowired
    protected UserService userService;

    @Autowired
    protected UserRepository userRepository;

    protected User createUser(TestName testName) {

        String username = testName.getMethodName();
        String email = testName.getMethodName()+"@gmail.com";

        Set<UserRole> userRoles = new HashSet<>();
        User basicUser = UserUtils.createBasicUser(username, email);
        userRoles.add(new UserRole(basicUser, new Role(RolesEnum.USER)));
        return userService.createUser(basicUser, userRoles);
    }
}
