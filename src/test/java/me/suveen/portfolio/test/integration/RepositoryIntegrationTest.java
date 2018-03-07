package me.suveen.portfolio.test.integration;

import me.suveen.portfolio.backend.persistence.domain.backend.Role;
import me.suveen.portfolio.backend.persistence.domain.backend.User;
import me.suveen.portfolio.backend.persistence.domain.backend.UserRole;
import me.suveen.portfolio.backend.persistence.repositories.RoleRepository;
import me.suveen.portfolio.backend.persistence.repositories.UserRepository;
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

    private static final int BASIC_ROLE_ID = 1;

    @Before
    public void init() {

        Assert.assertNotNull(userRepository);
        Assert.assertNotNull(roleRepository);
    }

    @Test
    public void testCreateNewRole() throws Exception {

        Role userRole = createBasicRole();
        roleRepository.save(userRole);

        Role retrivedRole = roleRepository.findById(BASIC_ROLE_ID).get();
        Assert.assertNotNull(retrivedRole);
    }

    @Test
    public void testCreateNewUser() throws Exception {
        User basicUser = createBasicUser();
        Role basicRole = createBasicRole();

        UserRole userRole = new UserRole();
        userRole.setUser(basicUser);
        userRole.setRole(basicRole);

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

    private Role createBasicRole() {

        Role role = new Role();
        role.setId(BASIC_ROLE_ID);
        role.setName("ROLE_USER");
        return role;
    }

    private User createBasicUser() {

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
