package me.suveen.portfolio.test.integration;

import me.suveen.portfolio.backend.persistence.domain.backend.User;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntegrationTest extends AbstractServiceIntegrationTest {

    /**
     * The application logger
     **/
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceIntegrationTest.class);

    @Rule
    public TestName testName = new TestName();

    @Test
    public void testCreateNewUser() throws Exception {

        User user = createUser(testName);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
    }

    @Test
    public void testUpdateUserPasswordService() throws Exception {

        User user = createUser(testName);
        String oldPassword = user.getPassword();
        String newPassword = UUID.randomUUID().toString();
        userService.updateUserPassword(user.getId(), newPassword);
        user = userRepository.findById(user.getId()).get();
        Assert.assertNotEquals(oldPassword, user.getPassword());

    }

}
