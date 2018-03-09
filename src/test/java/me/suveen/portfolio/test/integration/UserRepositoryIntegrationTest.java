package me.suveen.portfolio.test.integration;

import me.suveen.portfolio.backend.persistence.domain.backend.Role;
import me.suveen.portfolio.backend.persistence.domain.backend.User;
import me.suveen.portfolio.backend.persistence.domain.backend.UserRole;
import me.suveen.portfolio.enums.RolesEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryIntegrationTest extends AbstractIntegrationTest{


    @Rule
    public TestName testName = new TestName();

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

        String username = testName.getMethodName();
        String email = testName.getMethodName() + "@gmail.com";
        User basicUser = createUser(username, email);

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

        String username = testName.getMethodName();
        String email = testName.getMethodName() + "@gmail.com";
        User basicUser = createUser(username, email);
        userRepository.deleteById(basicUser.getId());
    }
    @Test
    public void testGetUserByEmail() throws Exception {
        User user = createUser(testName);

        User newlyFoundUser = userRepository.findByEmail(user.getEmail());
        Assert.assertNotNull(newlyFoundUser);
        Assert.assertNotNull(newlyFoundUser.getId());
        Assert.assertEquals(user, newlyFoundUser);
    }

    @Test
    public void testUpdateUserPassword() throws Exception {
        User user = createUser(testName);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());

        String newPassword = UUID.randomUUID().toString();

        userRepository.updateUserPassword(user.getId(), newPassword);

        user = userRepository.findById(user.getId()).get();
        Assert.assertEquals(newPassword, user.getPassword());

    }
}
