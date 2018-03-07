package me.suveen.portfolio.backend.persistence.domain.backend;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Role implements Serializable {

    public Role() {

    }

    /**
     * The Serial Version UID for Serilizable classes.
     */
    private static final long serialVersionUID = 1L;


    @Id
    private int id;

    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<UserRole> userRoles = new HashSet<>();

    public Set<UserRole> getUserRoles() {

        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {

        this.userRoles = userRoles;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
