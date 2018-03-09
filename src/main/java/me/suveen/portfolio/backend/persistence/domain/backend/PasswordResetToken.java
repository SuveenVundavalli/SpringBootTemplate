package me.suveen.portfolio.backend.persistence.domain.backend;

import me.suveen.portfolio.backend.persistence.converters.LocalDateTimeAttributeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Component
@Scope("prototype")
public class PasswordResetToken implements Serializable {

    /**
     * The application logger
     **/
    private static final Logger LOG = LoggerFactory.getLogger(PasswordResetToken.class);

    public static final int DEFAULT_TOKEN_LENGTH_IN_MINUTES = 120;

    /**
     * Default constructor
     */
    public PasswordResetToken() {

    }

    /**
     * full constructor
     *
     * @param token               The user token, it must not be null
     * @param user                The user for which the token must be created, it must not be null
     * @param creationDateTime    The date time when this request is created, it must not be null
     * @param expirationInMinutes The length in minutes for which this token will be valid. If zero, it will be assigned a default value of 120 (2 hours)
     *
     * @throws IllegalArgumentException If the token, user or creation date time is null
     */
    public PasswordResetToken(String token, User user, LocalDateTime creationDateTime, int expirationInMinutes) {

        if (null == token || null == creationDateTime || null == creationDateTime)
            throw new IllegalArgumentException("token, user, creation date time can't be null!");
        if (expirationInMinutes == 0) {
            LOG.warn("The token expiration minutes is zero. Assuming the default value {}", DEFAULT_TOKEN_LENGTH_IN_MINUTES);
            expirationInMinutes = DEFAULT_TOKEN_LENGTH_IN_MINUTES;
        }
        this.token = token;
        this.user = user;
        expiryDate = creationDateTime.plusMinutes(expirationInMinutes);
    }

    /**
     * The Serial Version UID for Serilizable classes.
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String token;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "expiry_date")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime expiryDate;

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getToken() {

        return token;
    }

    public void setToken(String token) {

        this.token = token;
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {

        this.user = user;
    }

    public LocalDateTime getExpiryDate() {

        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {

        this.expiryDate = expiryDate;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PasswordResetToken that = (PasswordResetToken) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
