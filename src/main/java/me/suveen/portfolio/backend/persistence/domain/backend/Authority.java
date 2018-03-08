package me.suveen.portfolio.backend.persistence.domain.backend;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

    public final String authority;

    public Authority(String authority) {

        this.authority = authority;
    }

    @Override
    public String getAuthority() {

        return authority;
    }
}
