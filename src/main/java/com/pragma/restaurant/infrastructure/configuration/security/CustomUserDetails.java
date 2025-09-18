package com.pragma.restaurant.infrastructure.configuration.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class CustomUserDetails extends User {
    private Long id;
    private Long idOwner;
    public CustomUserDetails(Long id, Long idOwner, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.idOwner = idOwner;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
