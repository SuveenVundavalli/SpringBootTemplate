package me.suveen.portfolio.test.integration;

import me.suveen.portfolio.backend.persistence.domain.backend.Role;
import me.suveen.portfolio.backend.persistence.domain.backend.User;
import me.suveen.portfolio.backend.persistence.domain.backend.UserRole;
import me.suveen.portfolio.backend.persistence.repositories.RoleRepository;
import me.suveen.portfolio.backend.persistence.repositories.UserRepository;
import me.suveen.portfolio.enums.RolesEnum;
import me.suveen.portfolio.utils.UserUtils;
import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractIntegrationTest {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected RoleRepository roleRepository;

    protected Role createRole(RolesEnum rolesEnum) {

        return new Role(rolesEnum);
    }

    protected User createUser(String username, String email) {

        User basicUser = UserUtils.createBasicUser(username, email);

        Role basicRole = createRole(RolesEnum.USER);
        roleRepository.save(basicRole);

        Set<UserRole> userRoles = new HashSet<>();
        UserRole userRole = new UserRole(basicUser, basicRole);
        userRoles.add(userRole);

        basicUser.getUserRoles().addAll(userRoles);
        basicUser = userRepository.save(basicUser);
        return basicUser;
    }

    protected User createUser(TestName testName) {
        return createUser(testName.getMethodName(), testName.getMethodName()+"@gmail.com");
    }

}
