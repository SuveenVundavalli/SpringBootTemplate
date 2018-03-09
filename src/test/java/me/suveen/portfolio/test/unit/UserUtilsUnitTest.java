package me.suveen.portfolio.test.unit;

import me.suveen.portfolio.utils.UserUtils;
import me.suveen.portfolio.web.controllers.ForgotMyPasswordController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.UUID;

public class UserUtilsUnitTest {

    private MockHttpServletRequest mockHttpServletRequest;

    @Before
    public void init() {
        mockHttpServletRequest = new MockHttpServletRequest();
    }

    @Test
    public void testPasswordResetEmailUrlConstruction() {
        mockHttpServletRequest.setServerPort(8080); // Default is 80
        String token = UUID.randomUUID().toString();
        long userId = 123456;
        String expectedUrl = "http://localhost:8080"+ ForgotMyPasswordController.CHANGE_PASSWORD_PATH+"?id=" + userId + "&token="+token;
        String actualUrl = UserUtils.createPasswordResetUrl(mockHttpServletRequest, userId, token);
        Assert.assertEquals(expectedUrl, actualUrl);
    }

}
