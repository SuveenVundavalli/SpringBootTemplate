package me.suveen.portfolio.backend.service;

import me.suveen.portfolio.backend.persistence.domain.backend.PasswordResetToken;
import me.suveen.portfolio.backend.persistence.domain.backend.User;
import me.suveen.portfolio.backend.persistence.repositories.PasswordResetTokenRepository;
import me.suveen.portfolio.backend.persistence.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class PasswordResetTokenService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Value("${token.expiration.length.minutes}")
    private int tokenExpirationInMinutes;

    /**
     * The application logger
     **/
    private static final Logger LOG = LoggerFactory.getLogger(PasswordResetTokenService.class);

    /**
     * Creates a password reset token for an email
     * @param email email of the user
     * @return PasswordResetToken if exisit
     */
    @Transactional
    public PasswordResetToken createPasswordResetTokenForEmail(String email) {

        PasswordResetToken passwordResetToken = null;
        User user = userRepository.findByEmail(email);

        if (null != user) {
            String token = UUID.randomUUID().toString();
            LocalDateTime now = LocalDateTime.now(Clock.systemUTC());
            passwordResetToken = new PasswordResetToken(token, user, now, tokenExpirationInMinutes);

            passwordResetToken = passwordResetTokenRepository.save(passwordResetToken);
            LOG.debug("Successfully created token {} for user {}", passwordResetToken.getToken(), user.getUsername());
        } else {
            LOG.warn("We couldn't find user with email {}", user.getEmail());
        }
        return passwordResetToken;
    }

    /**
     * Retrives the password reset token for the given token id
     *
     * @param token the token to be returned
     * @return A password reset token if one was found or null if none was found.
     */
    public PasswordResetToken findByToken(String token) {

        return passwordResetTokenRepository.findByToken(token);
    }


}
