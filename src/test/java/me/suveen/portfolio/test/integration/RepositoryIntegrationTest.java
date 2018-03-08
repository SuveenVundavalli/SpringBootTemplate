package me.suveen.portfolio.test.integration;

import me.suveen.portfolio.backend.persistence.domain.backend.Role;
import me.suveen.portfolio.backend.persistence.domain.backend.User;
import me.suveen.portfolio.backend.persistence.domain.backend.UserRole;
import me.suveen.portfolio.backend.persistence.repositories.RoleRepository;
import me.suveen.portfolio.backend.persistence.repositories.UserRepository;
import me.suveen.portfolio.enums.RolesEnum;
import me.suveen.portfolio.utils.UsersUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Before
    public void init() {

        Assert.assertNotNull(userRepository);
        Assert.assertNotNull(roleRepository);
    }

    @Test
    public void testCreateNewRole() throws Exception {

        Role userRole = createRole(RolesEnum.USER);
        roleRepository.save(userRole);

        Role retrievedRole = roleRepository.findById(RolesEnum.USER.getId()).get();
        Assert.assertNotNull(retrievedRole);
    }

    @Test
    public void testCreateNewUser() throws Exception {
        User basicUser = UsersUtils.createBasicUser();
        Role basicRole = createRole(RolesEnum.USER);

        UserRole userRole = new UserRole(basicUser, basicRole);

        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(userRole);

        basicUser.getUserRoles().addAll(userRoles); // if set using setter, then previous roles will be deleted

        for (UserRole ur : userRoles) {
            roleRepository.save(ur.getRole());
        }

        basicUser = userRepository.save(basicUser);
        User newlyCreatedUser = userRepository.findById(basicUser.getId()).get();
        Assert.assertNotNull(newlyCreatedUser);
        Assert.assertTrue(newlyCreatedUser.getId() != 0);

        Set<UserRole> newlyCreatedUserRoles = newlyCreatedUser.getUserRoles();
        for(UserRole ur : newlyCreatedUserRoles) {
            Assert.assertNotNull(ur.getRole());
            Assert.assertNotNull(ur.getRole().getId());
        }

    }

    private Role createRole(RolesEnum rolesEnum) {
        return new Role(rolesEnum);
    }
}
