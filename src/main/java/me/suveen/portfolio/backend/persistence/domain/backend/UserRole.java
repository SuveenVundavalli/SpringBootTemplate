package me.suveen.portfolio.backend.persistence.domain.backend;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class UserRole implements Serializable {

    public UserRole() {

    }

    /**
     * The Serial Version UID for Serilizable classes.
     */
    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;


    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    public User getUser() {

        return user;
    }

    public void setUser(User user) {

        this.user = user;
    }

    public Role getRole() {

        return role;
    }

    public void setRole(Role role) {

        this.role = role;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(user, userRole.user) &&
                Objects.equals(role, userRole.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(user, role);
    }
}
