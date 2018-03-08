package me.suveen.portfolio.test.integration;

import me.suveen.portfolio.backend.persistence.domain.backend.Role;
import me.suveen.portfolio.backend.persistence.domain.backend.User;
import me.suveen.portfolio.backend.persistence.domain.backend.UserRole;
import me.suveen.portfolio.backend.persistence.repositories.RoleRepository;
import me.suveen.portfolio.backend.persistence.repositories.UserRepository;
import me.suveen.portfolio.enums.RolesEnum;
import me.suveen.portfolio.utils.UserUtils;
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
    public void testCreateRole() throws Exception {

        Role userRole = createRole(RolesEnum.USER);
        roleRepository.save(userRole);

        Role retrievedRole = roleRepository.findById(RolesEnum.USER.getId()).get();
        Assert.assertNotNull(retrievedRole);
    }

    @Test
    public void testCreateUser() throws Exception {

        User basicUser = createUser();

        User newlyCreatedUser = userRepository.findById(basicUser.getId()).get();
        Assert.assertNotNull(newlyCreatedUser);
        Assert.assertTrue(newlyCreatedUser.getId() != 0);

        Set<UserRole> newlyCreatedUserRoles = newlyCreatedUser.getUserRoles();
        for (UserRole ur : newlyCreatedUserRoles) {
            Assert.assertNotNull(ur.getRole());
            Assert.assertNotNull(ur.getRole().getId());
        }

    }

    @Test
    public void testDeleteUser() throws Exception {
        User basicUser = createUser();
        userRepository.deleteById(basicUser.getId());
    }

    private Role createRole(RolesEnum rolesEnum) {

        return new Role(rolesEnum);
    }

    private User createUser() {

        User basicUser = UserUtils.createBasicUser();

        Role basicRole = createRole(RolesEnum.USER);
        roleRepository.save(basicRole);

        Set<UserRole> userRoles = new HashSet<>();
        UserRole userRole = new UserRole(basicUser, basicRole);
        userRoles.add(userRole);

        basicUser.getUserRoles().addAll(userRoles);
        basicUser = userRepository.save(basicUser);
        return basicUser;
    }
}
