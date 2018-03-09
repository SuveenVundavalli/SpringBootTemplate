package me.suveen.portfolio.test.integration;

import me.suveen.portfolio.backend.persistence.domain.backend.PasswordResetToken;
import me.suveen.portfolio.backend.persistence.domain.backend.User;
import me.suveen.portfolio.backend.persistence.repositories.PasswordResetTokenRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordResetTokenIntegrationTest extends AbstractIntegrationTest {

    @Value("${token.expiration.length.minutes}")
    private int expirationTimeInMinutes;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Rule
    public TestName testName = new TestName();

    @Before
    public void init() {

        Assert.assertFalse(expirationTimeInMinutes == 0);
    }

    @Test
    public void testTokenExpirationLength() throws Exception {

        User user = createUser(testName);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());

        LocalDateTime now = LocalDateTime.now(Clock.systemUTC());
        String token = getToken();

        LocalDateTime expectedTime = now.plusMinutes(expirationTimeInMinutes);

        PasswordResetToken passwordResetToken = createPasswordResetToken(token, user, now);

        LocalDateTime actualTime = passwordResetToken.getExpiryDate();
        Assert.assertNotNull(actualTime);
        Assert.assertEquals(expectedTime, actualTime);
    }

    @Test
    public void testFindTokenByTokenValue() throws Exception {

        User user = createUser(testName);
        String token = getToken();
        LocalDateTime now = LocalDateTime.now(Clock.systemUTC());

        createPasswordResetToken(token, user, now);
        PasswordResetToken retrivedToken = passwordResetTokenRepository.findByToken(token);

        Assert.assertNotNull(retrivedToken);
        Assert.assertNotNull(retrivedToken.getId());
        Assert.assertNotNull(retrivedToken.getUser());
    }

    @Test
    public void testDeleteToken() {

        User user = createUser(testName);
        String token = getToken();
        LocalDateTime now = LocalDateTime.now(Clock.systemUTC());

        PasswordResetToken passwordResetToken = createPasswordResetToken(token, user, now);
        long tokenId = passwordResetToken.getId();
        passwordResetTokenRepository.deleteById(tokenId);
        PasswordResetToken shouldNotExist = null;
        try {
            shouldNotExist = passwordResetTokenRepository.findById(tokenId).get();
        } catch (NoSuchElementException e) {}
        Assert.assertNull(shouldNotExist);
    }

    @Test
    public void testCascadeDeleteFromUserEntity() {

        User user = createUser(testName);
        String token = getToken();
        LocalDateTime now = LocalDateTime.now(Clock.systemUTC());

        PasswordResetToken passwordResetToken = createPasswordResetToken(token, user, now);
        long tokenId = passwordResetToken.getId();

        userRepository.deleteById(user.getId());

        Set<PasswordResetToken> shouldBeEmpty = passwordResetTokenRepository.findAllByUserId(user.getId());
        Assert.assertTrue(shouldBeEmpty.isEmpty());
    }

    @Test
    public void testMultipleTokensAreReturnedWhenQueryingByUserId() throws Exception {
        User user = createUser(testName);
        LocalDateTime now = LocalDateTime.now(Clock.systemUTC());

        String token1 = getToken();
        String token2 = getToken();
        String token3 = getToken();

        Set<PasswordResetToken> tokens = new HashSet<>();
        tokens.add(createPasswordResetToken(token1, user, now));
        tokens.add(createPasswordResetToken(token2, user, now));
        tokens.add(createPasswordResetToken(token3, user, now));

        passwordResetTokenRepository.saveAll(tokens);

        User foundUser = userRepository.findById(user.getId()).get();

        Set<PasswordResetToken> actualTokens = passwordResetTokenRepository.findAllByUserId(foundUser.getId());
        Assert.assertTrue(tokens.size() == actualTokens.size());

        List<String> tokensAsList = tokens.stream().map(prt -> prt.getToken()).collect(Collectors.toList());
        List<String> actualTokensList = actualTokens.stream().map(prt -> prt.getToken()).collect(Collectors.toList());

        Assert.assertEquals(tokensAsList, actualTokensList);

    }

    private PasswordResetToken createPasswordResetToken(String token, User user, LocalDateTime now) {

        PasswordResetToken passwordResetToken = new PasswordResetToken(token, user, now, expirationTimeInMinutes);
        passwordResetTokenRepository.save(passwordResetToken);
        Assert.assertNotNull(passwordResetToken.getId());
        return passwordResetToken;
    }

    private String getToken() {

        return UUID.randomUUID().toString();
    }
}
